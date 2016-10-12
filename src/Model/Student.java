package Model;

import DAO.StudentDAO;

import java.util.ArrayList;

/**
 * Created by Roel on 12-10-2016.
 */
public class Student extends StudentDAO{
    private int studentId;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String study;
    private String emailAdress;
    private String phoneNumber;
    private ArrayList data;

    public Student(int studentId, String firstName, String lastName, String birthDate, String study, String emailAdress, String phoneNumber) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.study = study;
        this.emailAdress = emailAdress;
        this.phoneNumber = phoneNumber;

    }

    public void addStudent(ArrayList data ){

    }
    //disableStudent(int studentId)

    //find(int studentId)
    //editStudent(ArrayList data, int studentId)


    public int getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getStudy() {
        return study;
    }

    public void setStudy(String study) {
        this.study = study;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList getData() {
        return data;
    }

    public void setData(ArrayList data) {
        this.data = data;
    }
}
