package DAO;

import Model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Roel on 12-10-2016.
 */
public class StudentDAO extends DAO {
    private PreparedStatement deleteStudentQuery;
    private PreparedStatement selectAllStudentsQuery;

    public StudentDAO() throws IllegalAccessException, InstantiationException, SQLException {
        super();
    }

    public void prepareStatements(){
        try{
            deleteStudentQuery = conn.prepareStatement("DELETE FROM Student WHERE studentID=?");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Student addStudent(Student student) {
        try {
            student = addStudentQuery(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public Student addStudentQuery(Student student) throws SQLException{
        String sql = "insert into Student(firstname,lastname,birthdate,study,email,phoneNumber, studentaddressid, tag) VALUES(?,?,?,?,?,?,?,?);";
        PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        statement.setString(1, student.getFirstName());
        statement.setString(2, student.getLastName());
        statement.setString(3, student.getBirthDate());
        statement.setString(4, student.getStudy());
        statement.setString(5, student.getEmailAddress());
        statement.setString(6, student.getPhoneNumber());
        statement.setInt(7, student.getAddress().getAddressID());
        statement.setString(8, student.getTag());

        int rowInserted = statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();

        if(rs.next()){
            int id = rs.getInt(1);
            student.setStudentID(id);
        }
        if (rowInserted > 0) {
            System.out.println("A new Student was inserted succesfully!");
        }
        statement.close();
        return student;
    }

    public void updateStudent(Student student) throws  SQLException{
        try{
            updateStudentQuery(student);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void updateStudentQuery(Student student) throws SQLException{
        String sql = "UPDATE Student SET firstName=?, lastName=?, birthdate=?, study=?, email=?, phoneNumber=?,userAddressID=?, tag=? WHERE studentID=?";
        PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        statement.setString(1, student.getFirstName());
        statement.setString(2, student.getLastName());
        statement.setString(3, student.getBirthDate());
        statement.setString(4, student.getStudy());
        statement.setString(5, student.getEmailAddress());
        statement.setString(6, student.getPhoneNumber());
        statement.setInt(7, student.getAddress().getAddressID());
        statement.setInt(8,student.getStudentID());
        statement.setString(9, student.getTag());
        statement.executeUpdate();
        statement.close();
    }

    public void deleteStudent(int studentID){
        try {
            deleteStudentQuery(studentID);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void deleteStudentQuery(int studentID)throws SQLException{
        String sql = "DELETE FROM Student WHERE studentID=?";
        String sql2 = "DELETE FROM Address WHERE addressID=?";
        //sql 3 note
        //sql 4 documents
        PreparedStatement statement = conn.prepareStatement(sql);
        PreparedStatement statement2 = conn.prepareStatement(sql2);

        statement2.setInt(1,student.getAddress().getAddressID());
        statement.setInt(1,studentID);
        statement2.executeUpdate();
        statement.executeUpdate();
        statement2.close();
        statement.close();
    }

    public ArrayList <Student> selectAllStudents() {
        ArrayList<Student> students = selectAllStudentsQuery();

        return students;
    }

    private ArrayList<Student> selectAllStudentsQuery(){
        String sql ="SELECT * FROM Student";
        ArrayList<Student> students = new ArrayList<Student>();
        try {
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Student student = new Student();
                student.setStudentID(result.getInt(1));
                student.setFirstName(result.getString(3));
                student.setLastName(result.getString(4));
                student.setBirthDate(result.getString(5));
                student.setStudy(result.getString(6));
                student.setEmailAddress(result.getString(7));
                student.setPhoneNumber(result.getString(8));
                student.setTag(result.getString(9));
                students.add(student);
            }
        }catch (SQLException esql){
            esql.printStackTrace();
        }
        return students;
    }

    public void close(){
        try{
            deleteStudentQuery.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
