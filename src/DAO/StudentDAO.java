package DAO;

import Model.Address;
import Model.Student;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Roel on 12-10-2016.
 */
public class StudentDAO extends DAO {
    private PreparedStatement deleteStudentQuery;
    private PreparedStatement selectAllStudentsQuery;

    public StudentDAO() throws IllegalAccessException, InstantiationException, SQLException {
        super();
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
            student.setId(id);
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
        String sql = "UPDATE Student SET firstName=?, lastName=?, birthdate=?, study=?, email=?, phoneNumber=?,AddressID=?, tag=? WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setString(1, student.getFirstName());
        statement.setString(2, student.getLastName());
        statement.setString(3, student.getBirthDate());
        statement.setString(4, student.getStudy());
        statement.setString(5, student.getEmailAddress());
        statement.setString(6, student.getPhoneNumber());
        statement.setInt(7, student.getAddress().getAddressID());

        statement.setString(8, student.getTag());
        statement.setInt(9,student.getId());
        int rowInserted = statement.executeUpdate();

        if (rowInserted > 0){
            System.out.println("The Student Has Been updated succesfully!");
        }

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
//        String sql2 = "DELETE FROM Address WHERE addressID=?";
        //sql 3 note
        //sql 4 documents
        PreparedStatement statement = conn.prepareStatement(sql);
//        PreparedStatement statement2 = conn.prepareStatement(sql2);

<<<<<<< HEAD
        statement2.setInt(1,student.getAddress().getAddressID());
        statement.setInt(1,student.getId());
        statement2.executeUpdate();
=======
//        statement2.setInt(1,student.getAddress().getAddressID());
        statement.setInt(1,studentID);
//        statement2.executeUpdate();
>>>>>>> 9c55ccb647b12bd30b73b7377cebd9718385480b
        statement.executeUpdate();
//        statement2.close();
        statement.close();
    }

    public ArrayList <Student> selectAllStudents() {

        ArrayList<Student> students = null;
        try {
            students = selectAllStudentsQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    private ArrayList<Student> selectAllStudentsQuery() throws SQLException{
        String sql ="SELECT * FROM Student";
        ArrayList<Student> students = new ArrayList<Student>();
<<<<<<< HEAD

        PreparedStatement statement = conn.prepareStatement(sql);

        ResultSet result = statement.executeQuery();
        while (result.next()) {
            Student student = new Student();
            student.setId(result.getInt(1));
            student.setFirstName(result.getString(3));
            student.setLastName(result.getString(4));
            student.setBirthDate(result.getString(5));
            student.setStudy(result.getString(6));
            student.setEmailAddress(result.getString(7));
            student.setPhoneNumber(result.getString(8));
            student.setTag(result.getString(9));
            students.add(student);
=======
        try {
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Student student = new Student();
                student.setId(result.getInt(1));
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
>>>>>>> 9c55ccb647b12bd30b73b7377cebd9718385480b
        }

        statement.close();
        return students;
    }
    public Student selectStudent(int id) {
        Student student = null;
        try {
            student = selectStudentQuery(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    private Student selectStudentQuery(int id) throws SQLException {
        Student student = new Student();
        Address address = new Address();

        String sql1 = "SELECT * FROM Student WHERE id=?";
        String sql2 = "SELECT * FROM Address WHERE addressid=?";
        PreparedStatement statement1 = conn.prepareStatement(sql1);
        PreparedStatement statement2 = conn.prepareStatement(sql2);

        statement1.setInt(1,id);
        ResultSet resultSet1 = statement1.executeQuery();
        while (resultSet1.next()){
            statement2.setInt(1,resultSet1.getInt(2));
            ResultSet resultSet2 = statement2.executeQuery();
            student.setId(resultSet1.getInt(1));
            student.setFirstName(resultSet1.getString(3));
            student.setLastName(resultSet1.getString(4));
            student.setBirthDate(resultSet1.getString(5));
            student.setStudy(resultSet1.getString(6));
            student.setEmailAddress(resultSet1.getString(7));
            student.setPhoneNumber(resultSet1.getString(8));
            student.setTag(resultSet1.getString(9));
            while (resultSet2.next()){
                address.setAddressID(resultSet2.getInt(1));
                address.setAddress(resultSet2.getString(2));
                address.setZipCode(resultSet2.getString(3));
                address.setCity(resultSet2.getString(4));
            }
            student.setAddress(address);
        }

        statement1.close();
        statement2.close();
        return student;
    }

}
