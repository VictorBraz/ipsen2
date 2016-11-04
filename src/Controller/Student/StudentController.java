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

        tableView.setItems(studentData);
    }

    @FXML

    void handleAddButton(MouseEvent event) {
        AddStudentController addStudentController = new AddStudentController();
        addContent(addStudentController, resources.getString("NEW_STUDENT_DIALOG"));
    }

    @FXML
    void handleDeleteButton(MouseEvent event) {

    }

    @FXML
    void handleZoominButton(MouseEvent event) {
        EditStudentController editStudentController = new EditStudentController();
        editStudentController.setSelectedItem(1);
        addContent(editStudentController, resources.getString("NEW_STUDENT_DIALOG"));

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