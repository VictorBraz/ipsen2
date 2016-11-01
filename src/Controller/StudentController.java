package Controller;

import DAO.AddressDAO;
import DAO.StudentDAO;
import Model.Address;
import Model.Student;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.ResourceBundle;

public class StudentController{

    private StudentDAO studentDAO;
    private AddressDAO addressDAO;
    private Student currentStudent;
    private Boolean newStudent;

    public StudentController(){
        try{
            this.studentDAO = new StudentDAO();
            this.studentDAO.prepareStatements();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //FXML StudentView
    @FXML private Button editBtn;
    @FXML private Button saveBtn;
    @FXML private Button cancelBtn;
    @FXML private Button documentsBtn;
    @FXML private Button tagsBtn;
    @FXML private Button notesBtn;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField birthDateTextField;
    @FXML private TextField phoneNumberTextField;
    @FXML private TextField emailAddressTextField;
    @FXML private TextField addressTextField;
    @FXML private TextField cityTextField;
    @FXML private TextField zipCodeTextField;
    @FXML private TextField studyTextField;

    //FXML StudentListView
    @FXML private Button homeBtn;
    @FXML private Button addBtn;
    @FXML private TableView tableView;
    @FXML private TableColumn firstNameColumn;
    @FXML private TableColumn lastNameColumn;
    @FXML private TableColumn emailAddressColumn;

    public void loadStudentList(){
        ObservableList<Student> students = selectAllStudents();
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("lastName"));
        emailAddressColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("emailAddress"));
        tableView.setItems(students);
    }

    private Student setStudentInfo(Student currentStudent){
        currentStudent.setFirstName(firstNameTextField.getText());
        currentStudent.setLastName(lastNameTextField.getText());
        currentStudent.setBirthDate(birthDateTextField.getText());
        currentStudent.setEmailAddress(emailAddressTextField.getText());
        currentStudent.setStudy(studyTextField.getText());
        currentStudent.setPhoneNumber(phoneNumberTextField.getText());
        return currentStudent;
    }

    private Address setAddressInfo(Address address){
        address.setAddress(addressTextField.getText());
        address.setZipCode(zipCodeTextField.getText());
        address.setCity(cityTextField.getText());
        return address;
    }

    private void editTextFields(Boolean keuze){
        firstNameTextField.setEditable(keuze);
        lastNameTextField.setEditable(keuze);
        birthDateTextField.setEditable(keuze);
        phoneNumberTextField.setEditable(keuze);
        emailAddressTextField.setEditable(keuze);
        addressTextField.setEditable(keuze);
        cityTextField.setEditable(keuze);
        zipCodeTextField.setEditable(keuze);
        studyTextField.setEditable(keuze);
        saveBtn.setVisible(keuze);
        saveBtn.setDisable(!keuze);
        cancelBtn.setVisible(keuze);
        cancelBtn.setDisable(!keuze);
    }

    private void loadCurrentStudent(){
        firstNameTextField.setText(currentStudent.getFirstName());
        lastNameTextField.setText(currentStudent.getLastName());
        birthDateTextField.setText(currentStudent.getBirthDate());
        phoneNumberTextField.setText(currentStudent.getPhoneNumber());
        emailAddressTextField.setText(currentStudent.getEmailAddress());
        studyTextField.setText(currentStudent.getStudy());
        addressTextField.setText(currentStudent.getAddress().getAddress());
        cityTextField.setText(currentStudent.getAddress().getCity());
        zipCodeTextField.setText(currentStudent.getAddress().getZipCode());
    }

    public void deleteStudent(Student student){
        studentDAO.deleteStudent(student);
    }

    public ObservableList<Student> selectAllStudents(){
        ArrayList<Student> students = studentDAO.selectAllStudents();
        return FXCollections.observableArrayList(students);
    }

    private void addStudent(){
        Address address = new Address();
        address = setAddressInfo(address);
        currentStudent.setAddress(addressDAO.addAddress(address));
        currentStudent = setStudentInfo(currentStudent);

        studentDAO.addStudent(currentStudent);
        newStudent = false;
    }

    private void updateStudent(){
        Address address = new Address();
        address.setAddressID(currentStudent.getAddress().getAddressID());
        address = setAddressInfo(address);
        currentStudent = setStudentInfo(currentStudent);
        currentStudent.setAddress(address);

        addressDAO.updateAddress(address.getAddressID(), address);
        studentDAO.updateStudent(currentStudent);
    }

    private void close(){
        studentDAO.close();
    }

    // studentView set on Action
    @FXML
    public void cmdEdit() {
        editTextFields(true);
    }

    @FXML
    public void cmdCancel(){
        if (newStudent){
            //go to studentListView
            newStudent = false;
        }else{
            loadCurrentStudent();
            editTextFields(false);
        }
    }

    @FXML
    public void cmdSave(){
        if (firstNameTextField.getText().trim() == "" || lastNameTextField.getText().trim() == "" ||
            birthDateTextField.getText().trim() == "" || emailAddressTextField.getText().trim() == "" ||
            studyTextField.getText().trim() == "" || phoneNumberTextField.getText().trim() == "" ||
            addressTextField.getText().trim() == "" || cityTextField.getText().trim() == ""||
            zipCodeTextField.getText().trim() == ""){
            //melding
        }else {
            if(newStudent){
                addStudent();
                editBtn.setVisible(true);
                editBtn.setDisable(false);
            } else {
                updateStudent();
            }
            editTextFields(false);
        }
    }

    @FXML
    public void cmdStudentList(){
        // go to student list view
    }

    // studentListView Set on actions
    @FXML
    public void cmdHome(){
        // go to home;
    }

    @FXML
    public void cmdAdd(){
        newStudent = true;
        //open new Student view en geef
        currentStudent = new Student();
        //mee
        editBtn.setDisable(true);
        editBtn.setVisible(false);
    }

}