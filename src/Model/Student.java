package Model;

import DAO.StudentDAO;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Roel on 12-10-2016.
 */
public class Student{
    private int studentID;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String study;
    private String emailAddress;
    private String phoneNumber;
    private Address address;
    private Note note;
    private StudentDAO studentDAO;

    public Student(){

    }

    public Student(int studentID){
        this.studentID = studentID;
        this.firstName = null;
        this.lastName = null;
        this.birthDate = null;
        this.study = null;
        this.emailAddress = null;
        this.phoneNumber = null;
        this.address = null;
        this.note = null;
    }

    public Student(int studentID, String firstName, String lastName, String birthDate, String study, String emailAddress, String phoneNumber, int adressID, int noteID) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.study = study;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.address = new Address(adressID);
        this.note = new Note(noteID);
    }

    public void addStudent(HashMap data ){
        //nog niet correct
        int addressID = this.address.create(data);
        int noteID = this.note.create(data);


        data.put("addressID", addressID);
        data.put("noteID", noteID);
        this.studentDAO.create(data);
    }


    public void editStudent(int studentID, HashMap data){
        Student student = this.studentDAO.find(studentID);
        int addressID = student.getAddress().getAddressID();

        data.put("addressID", addressID);
        new Address().update(addressID, data);
        this.studentDAO.update(studentID, data);
    }

    //disableStudent(int studentId)

    //find(int studentId)


    public Address getAddress() {return address;}

    public void setAddress(Address address) {this.address = address;}

    public Note getNote() {return note;}

    public void setNote(Note note) {this.note = note;}

    public int getStudentID() {
        return studentID;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAdress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
