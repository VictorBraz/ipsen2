package Controller.Student;

import Controller.handlers.TableViewListener;
import DAO.AddressDAO;
import DAO.StudentDAO;
import Model.Student;
import Model.TableViewItem;
import contentloader.ContentLoader;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentController extends ContentLoader implements Initializable, TableViewListener{

    @FXML private TableView<TableViewItem> tableView;
    @FXML private TableColumn checkBoxColumn;
    @FXML private TableColumn firstNameColumn;
    @FXML private TableColumn lastNameColumn;
    @FXML private TableColumn birthDateColumn;
    @FXML private TableColumn adresColumn;
    @FXML private TableColumn zipCodeColumn;
    @FXML private TableColumn cityColum;
    @FXML private TableColumn emailColumn;
    @FXML private TableColumn studyColum;
    @FXML private TableColumn phoneNumberColumn;
    @FXML private TableColumn tagColumn;

    private int selectedStudentID;
    private ObservableList<TableViewItem> studentData;
    private ArrayList<Integer> selectedRows;
    private CheckBox selectAllCheckBox;

    private StudentDAO studentDAO;
    private AddressDAO addressDAO;
    private ResourceBundle resources;

    public StudentController(){
        try{
            this.studentDAO = new StudentDAO();
            this.studentDAO.prepareStatements();
        }catch (Exception e){
            e.printStackTrace();
        }
    }




    @FXML
    void handleAddButton(MouseEvent event) {
        addContent(new AddStudentController(), resources.getString("EDIT_STUDENT_DIALOG"));

    }

    @FXML
    void handleDeleteButton(MouseEvent event) {

    }

    @FXML
    void handleZoominButton(MouseEvent event) {

    }







    public void deleteStudent(Student student){
        studentDAO.deleteStudent(student);
    }

    public ArrayList<Student> selectAllStudents(){
        return studentDAO.selectAllStudents();
    }

    public void addStudent(){
        Student student = new Student(/*fields*/);
        //Get input from fields en set student
        //student.setFirstName();
        //student.setLastName();
        //student.setBirthDate();
        //student.setAddress();
        //student.setEmailAddress();
        //student.setStudy();
        //student.setPhoneNumber();
        student.setAddress(addressDAO.addAddress(student.getAddress()));

        studentDAO.addStudent(student);
    }

    public void updateStudent(Student student){
        //Get input from fields en set student
        //student.setFirstName();
        //student.setLastName();
        //student.setBirthDate();
        //student.setAddress();
        //student.setEmailAddress();
        //student.setStudy();
        //student.setPhoneNumber();
        studentDAO.updateStudent(student);
    }

    private void close(){
        studentDAO.close();
    }



    @Override
    public void setSelectedRows(ArrayList selectedRows) {

    }

    @Override
    public void setSelectedItem(int selectedItemId) {

    }

    @Override
    public void openEditMenu() {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        setMainFrameTitle(resources.getString("STUDENT_TITLE"));
    }
}