package Controller.search;

import Controller.handlers.TableViewListener;
import Controller.handlers.TableViewSelectHandler;
import DAO.ClientDAO;
import DAO.CompanyDAO;
import DAO.StudentDAO;
import Model.Client;
import Model.Company;
import Model.Student;
import Model.TableViewItem;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import contentloader.ContentLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Bernd on 2-11-2016.
 */

public class SearchController extends ContentLoader implements Initializable, TableViewListener {

    @FXML private JFXTextField searchTextField;
    @FXML private TableView<TableViewItem> studentTableView;
    @FXML private TableColumn checkBoxStudentColumn;
    @FXML private TableColumn studentLastnameColumn;
    @FXML private TableColumn studentTagsColumn;
    @FXML private JFXButton cancelButton;
    @FXML private JFXButton submitButton;
    @FXML private TableView<TableViewItem> clientTableView;
    @FXML private TableColumn checkBoxClientColumn;
    @FXML private TableColumn clientLastnameColumn;
    @FXML private TableColumn clientTagsColumn;
    @FXML private TableView<TableViewItem> companyTableView;
    @FXML private TableColumn checkBoxCompanyColumn;
    @FXML private TableColumn companyNameColumn;
    @FXML private TableColumn companyTagsColumn;

    private int selectedClientID;
    private int selectedStudentID;
    private int selectedCompanyID;

    private ArrayList<Integer> selectedRows;
    private CompanyDAO companyDAO;
    private StudentDAO studentDAO;
    private ClientDAO clientDAO;

    private ResourceBundle resources;

    private ObservableList<TableViewItem> companies;
    private ObservableList<TableViewItem> students ;
    private ObservableList<TableViewItem> clients ;

    @FXML
    void handleCancelButton(MouseEvent event) {
        addContent(resources.getString("MAINMENU"));


    }

    @FXML
    void handleComfirmButton(MouseEvent event) {

    }

    @FXML
    void handleSearchButton(MouseEvent event) {

        studentTableView.getItems().clear();
        clientTableView.getItems().clear();
        companyTableView.getItems().clear();
        showCompanyTagTable();
        showStudentTagTable();
        showClientTagTable();

    }

    @Override
    public void setSelectedRows(ArrayList selectedRows) {
        this.selectedRows = selectedRows;
    }

    @Override
    public void setSelectedItem(int selectedItemId) {
        this.selectedCompanyID = selectedItemId;
    }


    public void showCompanyTagTable(){

        TableViewSelectHandler tableViewSelectHandler = new TableViewSelectHandler(companyTableView, this);
        tableViewSelectHandler.createCheckBoxColumn();
        tableViewSelectHandler.createSelectAllCheckBox();

        for (Company company : companyDAO.getCompanies()){
            if(company.getTag().contains(searchTextField.getText())){
                companies.add(company);
            }
        }
        companyNameColumn.setCellValueFactory(new PropertyValueFactory<Company, String>("companyName"));
        companyTagsColumn.setCellValueFactory(new PropertyValueFactory<Company, String>("tag"));
        companyTableView.setItems(companies);

    }

    public void showStudentTagTable(){

        TableViewSelectHandler tableViewSelectHandler = new TableViewSelectHandler(studentTableView, this);
        tableViewSelectHandler.createCheckBoxColumn();
        tableViewSelectHandler.createSelectAllCheckBox();

        for (Student student : studentDAO.selectAllStudents()){
            if(student.getTag().contains(searchTextField.getText())){
                students.add(student);
            }
        }
        studentLastnameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
        studentTagsColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("tag"));
        studentTableView.setItems(students);
    }

    public void showClientTagTable(){

        TableViewSelectHandler tableViewSelectHandler = new TableViewSelectHandler(clientTableView, this);
        tableViewSelectHandler.createCheckBoxColumn();
        tableViewSelectHandler.createSelectAllCheckBox();

        for (Client client : clientDAO.selectAllClients()){
            if(client.getTag().contains(searchTextField.getText())){
                clients.add(client);
            }
        }
        clientLastnameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
        clientTagsColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("tag"));
        clientTableView.setItems(clients);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        setMainFrameTitle(resources.getString("SEARCH_TITLE"));
            try {
            this.companyDAO = new CompanyDAO();
            this.clientDAO = new ClientDAO();
            this.studentDAO = new StudentDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }
        companies = FXCollections.observableArrayList();
        students = FXCollections.observableArrayList();
        clients = FXCollections.observableArrayList();
    }
}
