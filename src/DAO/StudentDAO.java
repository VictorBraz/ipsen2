package DAO;

import Model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Roel on 12-10-2016.
 */
public class StudentDAO extends DAO {
    private PreparedStatement addStudentQuery;
    private PreparedStatement updateStudentQuery;
    private PreparedStatement deleteStudentQuery;
    private PreparedStatement selectAllStudentsQuery;

    public StudentDAO() throws IllegalAccessException, InstantiationException, SQLException {
        super();
    }

    public void prepareStatements(){
        try{
            addStudentQuery = conn.prepareStatement("insert into Student(firstname,lastname,birthdate,study,email,phoneNumber,userAddressID,tag) VALUES(?,?,?,?,?,?,?,?);");
            updateStudentQuery = conn.prepareStatement("UPDATE Student SET firstName=?, lastName=?, birthdate=?, study=?, email=?, phoneNumber=?,userAddressID=?, tag=? WHERE studentID=?");
            deleteStudentQuery = conn.prepareStatement("DELETE FROM Student WHERE studentID=?");
            selectAllStudentsQuery = conn.prepareStatement("SELECT * FROM Student");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addStudent(Student student){
        try{
            addStudentQuery.setString(1, student.getFirstName());
            addStudentQuery.setString(2, student.getLastName());
            addStudentQuery.setString(3, student.getBirthDate());
            addStudentQuery.setString(4, student.getStudy());
            addStudentQuery.setString(5, student.getEmailAddress());
            addStudentQuery.setString(6, student.getPhoneNumber());
            addStudentQuery.setInt(7, student.getAddress().getAddressID());
            addStudentQuery.setString(8, student.getTag());
            addStudentQuery.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student){
        try {
            updateStudentQuery.setString(1, student.getFirstName());
            updateStudentQuery.setString(2, student.getLastName());
            updateStudentQuery.setString(3, student.getBirthDate());
            updateStudentQuery.setString(4, student.getStudy());
            updateStudentQuery.setString(5, student.getEmailAddress());
            updateStudentQuery.setString(6, student.getPhoneNumber());
            updateStudentQuery.setInt(7, student.getAddress().getAddressID());
            updateStudentQuery.setInt(8,student.getStudentID());
            updateStudentQuery.setString(9, student.getTag());
            updateStudentQuery.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteStudent(Student student){
        try {
            deleteStudentQuery.setInt(1,student.getStudentID());
            deleteStudentQuery.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
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
                student.setTag(result.getString(8));
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    public void close(){
        try{
            addStudentQuery.close();
            updateStudentQuery.close();
            deleteStudentQuery.close();
            selectAllStudentsQuery.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
