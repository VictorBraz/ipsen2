package Model;

import DAO.StudentDAO;

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

    /**
     * Instantiates a new Student.
     */
    public Student(){

    }

    public Student(int studentID, String firstName, String lastName, String birthDate, String study,
                   String emailAddress, String phoneNumber) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.study = study;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Instantiates a new Student.
     *
     * @param studentID the student id
     */
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

    /**
     * Instantiates a new Student.
     *
     * @param studentID
     * @param firstName
     * @param lastName
     * @param birthDate
     * @param study
     * @param emailAddress
     * @param phoneNumber
     * @param addressID
     * @param noteID
     */
    public Student(int studentID, String firstName, String lastName, String birthDate, String study,
                   String emailAddress, String phoneNumber, int addressID, int noteID) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.study = study;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.address = new Address(addressID);
        this.note = new Note(noteID);

    }

    /**
     * Gets student address
     *
     * @return the address
     */
    public Address getAddress() {return address;}

    /**
     * Sets student address
     *
     * @param address the address
     */
    public void setAddress(Address address) {this.address = address;}

    /**
     * Gets student note
     *
     * @return the Note
     */
    public Note getNote() {return note;}

    /**
     * Sets student note
     *
     * @param note the note
     */
    public void setNote(Note note) {this.note = note;}

    /**
     * Gets student id
     *
     * @return the student ID
     */
    public int getStudentID() {
        return studentID;
    }

    /**
     * Gets first name
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets birth date
     *
     * @return the birth date
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Sets birth date
     *
     * @param birthDate the birth date
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Gets study
     *
     * @return the study
     */
    public String getStudy() {
        return study;
    }

    /**
     * Sets study
     *
     * @param study the study
     */
    public void setStudy(String study) {
        this.study = study;
    }

    /**
     * Gets email address
     *
     * @return the email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets email address
     *
     * @param emailAddress the email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     *
     * @return
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
