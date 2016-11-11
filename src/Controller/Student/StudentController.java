package Controller.Student;

import Controller.handlers.TableViewListener;
import Controller.handlers.TableViewSelectHandler;
import DAO.AddressDAO;
import DAO.StudentDAO;
import Model.Student;
import Model.TableViewItem;
import com.jfoenix.controls.JFXCheckBox;
import contentloader.ContentLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentController extends ContentLoader implements Initializable, TableViewListener{

    @FXML private TableView<TableViewItem> tableView;
    @FXML private TableColumn checkBoxColumn;
    @FXML private TableColumn firstNameColumn;
    @FXML private TableColumn lastNameColumn;
    @FXML private TableColumn birthDateColumn;
    @FXML private TableColumn emailColumn;
    @FXML private TableColumn studyColum;
    @FXML private TableColumn phoneNumberColumn;
    @FXML private TableColumn tagColumn;
    @FXML private TableColumn studentIDColumn;

    private int selectedStudentID;
    private ObservableList<TableViewItem> studentData;
    private ArrayList<Integer> selectedRows;
    private JFXCheckBox selectAllCheckBox;

    private StudentDAO studentDAO;
    private AddressDAO addressDAO;
    private ResourceBundle resources;

    private void showTable(){
        TableViewSelectHandler tableViewSelectHandler = new TableViewSelectHandler(tableView, this);
        tableViewSelectHandler.createCheckBoxColumn();
        tableViewSelectHandler.createSelectAllCheckBox();

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("lastName"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("birthDate"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("emailAddress"));
        studyColum.setCellValueFactory(new PropertyValueFactory<Student, String>("study"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("phoneNumber"));
        tagColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("tag"));
        studentIDColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("studentID"));

        tableView.setItems(studentData);
    }

    @FXML

    void handleAddButton(MouseEvent event) {
        addContent(new AddStudentController(), resources.getString("NEW_STUDENT_DIALOG"));
    }

    @FXML
    void handleDeleteButton(MouseEvent event) {
        if (selectedRows.size() != 0){
            selectedRows.forEach(row -> studentDAO.deleteStudent(row));
            addContent(resources.getString("STUDENTS"));
        }
//        if(this.selectedStudentID != 0){
//            deleteStudent();
//        }
    }

    @FXML
    void handleOpenFileButton(MouseEvent event){

    }

    @FXML
    void handleZoominButton(MouseEvent event){
        ArrayList <EditStudentController> controller = new ArrayList<>();
        controller.add(new EditStudentController());
        controller.get(0).setSelectedItem(selectedStudentID);
        addContent(controller.get(0), resources.getString("NEW_STUDENT_DIALOG"));
        controller.remove(true);
    }


    @Override
    public void setSelectedRows(ArrayList selectedRows) {
        this.selectedRows = selectedRows;

    }

    @Override
    public void setSelectedItem(int selectedItemId) {
        this.selectedStudentID = selectedItemId;
    }

    @Override
    public void openEditMenu() {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        setMainFrameTitle(resources.getString("STUDENT_TITLE"));

        try{
            studentDAO = new StudentDAO();
            selectedRows = new ArrayList<>();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        studentData = FXCollections.observableArrayList(studentDAO.selectAllStudents());
        showTable();
    }
}