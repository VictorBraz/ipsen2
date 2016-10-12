package DAO;

import Model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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


    public StudentDAO(){

    }

    public StudentDAO(Student student, Connection conn) {
        this.student = student;
        this.conn = conn;
    }
    // prepareert alle statements
    public void prepareStatements(){
        try{
            insertStudent = conn.prepareStatement("insert into Student(firstName,lastName,birthDate,study,email,phoneNumber) VALUES(?,?,?,?,?,?);");
            editStudent = conn.prepareStatement("UPDATE Student SET firstName = ? , lastName = ? , birthDate = ? , study = ?, email = ?, phoneNumber = ? WHERE studentId = ?");
            getStudentList = conn.prepareStatement("SELECT * FROM Student;");
        } catch (Exception ex){
            System.out.println(ex);
        }
    }

    //voeg waarden toe aan prepared statement
    public void insertStudent(){
        try{
            insertStudent.setString(1, student.getFirstName());
            insertStudent.setString(2, student.getLastName());
            insertStudent.setString(3, student.getBirthDate());
            insertStudent.setString(4, student.getStudy());
            insertStudent.setString(5, student.getEmailAdress());
            insertStudent.setString(6, student.getPhoneNumber());
            insertStudent.executeUpdate();
        } catch (Exception ex){
            System.out.println(ex);
        }
    }

    public void getStudentList() {
        try{
            ResultSet resultSet = getStudentList.executeQuery();
            students = new ArrayList<Student>();
            while (resultSet.next()){
                Student stu = new Student(  resultSet.getInt("studentID"),
                                            resultSet.getString("firstName"),
                                            resultSet.getString("lastName"),
                                            resultSet.getString("birthDate"),
                                            resultSet.getString("study"),
                                            resultSet.getString("email"),
                                            resultSet.getString("phoneNumber"));
                students.add(stu);
            }
        } catch (Exception ex){
            System.out.println(ex);
        }
    }

    // voeg waarden toe aan prepared statement
    public void editStudent(){
        try {
            editStudent.setString(1, student.getFirstName());
            editStudent.setString(2, student.getLastName());
            editStudent.setString(3, student.getBirthDate());
            editStudent.setString(4, student.getStudy());
            editStudent.setString(5, student.getEmailAdress());
            editStudent.setString(6, student.getPhoneNumber());
            editStudent.setInt(7, student.getStudentId());
            editStudent.executeUpdate();
        } catch (Exception ex){
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
