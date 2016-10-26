package DAO;

import Model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Roel on 12-10-2016.
 */
public class StudentDAO {
    private Student student;
    private Connection conn;
    private ArrayList <Student> students;
    private PreparedStatement insertStudent;
    private PreparedStatement editStudent;
    private PreparedStatement getStudentList;
    private PreparedStatement getStudent;


    public StudentDAO(){

    }

    public StudentDAO(Student student, Connection conn) {
        this.student = student;
        this.conn = conn;
    }
    // prepareert alle statements
    public void prepareStatements(){
        try{
            insertStudent = conn.prepareStatement("insert into Student(firstname,lastname,birthdate,study,email,phonenumber, useraddressid) VALUES(?,?,?,?,?,?,?);");
            editStudent = conn.prepareStatement("UPDATE Student SET firstName = ? , lastName = ? , birthDate = ? , study = ?, email = ?, phoneNumber = ? WHERE studentId = ?");
            getStudentList = conn.prepareStatement("SELECT * FROM Student;");
            //getStudent = conn.prepareStatement();
        } catch (Exception ex){
            System.out.println(ex);
        }
    }


    public Student find(int studentID){
        return student;
    }

    public void update(int studentID, HashMap data){}


    public void create(HashMap data){
        try{
            insertStudent.setString(1, String.valueOf(data.get("firstName")));
            insertStudent.setString(2, String.valueOf(data.get("lastName")));
            insertStudent.setString(3, String.valueOf(data.get("birthDate")));
            insertStudent.setString(4, String.valueOf(data.get("study")));
            insertStudent.setString(5, String.valueOf(data.get("emailAddress")));
            insertStudent.setString(6, String.valueOf(data.get("phoneNumber")));
            insertStudent.setInt(7, Integer.valueOf((Integer) data.get("addressID")));
            insertStudent.executeUpdate();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }


    //close prepared statements
    public void close(){
        try{
            getStudentList.close();
            editStudent.close();
            insertStudent.close();
        } catch (Exception ex){
            System.out.println();
        }
    }
}
