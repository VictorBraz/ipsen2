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
            selectAllStudentsQuery = conn.prepareStatement("SELECT * FROM Student");
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
        String sql = "insert into Student(firstname,lastname,birthdate,study,email,phoneNumber, studentaddressid) VALUES(?,?,?,?,?,?,?);";
        PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        statement.setString(1, student.getFirstName());
        statement.setString(2, student.getLastName());
        statement.setString(3, student.getBirthDate());
        statement.setString(4, student.getStudy());
        statement.setString(5, student.getEmailAddress());
        statement.setString(6, student.getPhoneNumber());
        statement.setInt(7, student.getAddress().getAddressID());

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
        String sql = "UPDATE Student SET firstName=?, lastName=?, birthdate=?, study=?, email=?, phoneNumber=?,userAddressID=? WHERE studentID=?";
        PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        statement.setString(1, student.getFirstName());
        statement.setString(2, student.getLastName());
        statement.setString(3, student.getBirthDate());
        statement.setString(4, student.getStudy());
        statement.setString(5, student.getEmailAddress());
        statement.setString(6, student.getPhoneNumber());
        statement.setInt(7, student.getAddress().getAddressID());
        statement.setInt(8,student.getStudentID());
        statement.executeUpdate();
        statement.close();
    }

    public void deleteStudent(Student student){
        try {
            deleteStudentQuery(student);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteStudentQuery(Student student)throws SQLException{
        String sql = "DELETE FROM Student WHERE studentID=?";
        String sql2 = "DELETE FROM Address WHERE addressID=?";
        //sql 3 note
        //sql 4 documents
        PreparedStatement statement = conn.prepareStatement(sql);
        PreparedStatement statement2 = conn.prepareStatement(sql2);

        statement2.setInt(1,student.getAddress().getAddressID());
        statement.setInt(1,student.getStudentID());
        statement2.executeUpdate();
        statement.executeUpdate();
        statement2.close();
        statement.close();
    }

    public ArrayList<Student> selectAllStudents(){
        ArrayList<Student> students = new ArrayList<Student>();
        try {
            ResultSet result = selectAllStudentsQuery.executeQuery();
            while (result.next()) {
                Student student = new Student();
                student.setFirstName(result.getString(2));
                student.setLastName(result.getString(3));
                student.setBirthDate(result.getString(4));
                student.setStudy(result.getString(5));
                student.setEmailAddress(result.getString(6));
                student.setPhoneNumber(result.getString(7));
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    public void close(){
        try{
            deleteStudentQuery.close();
            selectAllStudentsQuery.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
