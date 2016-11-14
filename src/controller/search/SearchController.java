package controller.search;

import DAO.ClientDAO;
import DAO.CompanyDAO;
import DAO.StudentDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import contentloader.ContentLoader;
import controller.client.EditClientController;
import controller.company.CompanyEditController;
import controller.handlers.TableViewListener;
import controller.handlers.TableViewSelectHandler;
import controller.student.EditStudentController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Client;
import model.Company;
import model.Student;
import model.TableViewItem;

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
    private int selectedItemID;

    private ArrayList<Integer> selectedRows;
    private CompanyDAO companyDAO;
    private StudentDAO studentDAO;
    private ClientDAO clientDAO;

    private ResourceBundle resources;

    private ObservableList<TableViewItem> companies;
    private ObservableList<TableViewItem> students ;
    private ObservableList<TableViewItem> clients ;

    /**
     * Handle cancel button.
     *
     * @param event the event
     */
    @FXML
    void handleCancelButton(MouseEvent event) {
        addContent(resources.getString("MAINMENU"));
    }

    /**
     * Handle comfirm button.
     *
     * @param event the event
     */
    @FXML
    void handleComfirmButton(MouseEvent event) {
        if (selectedItemID < 1000000) {
            handleStudent();
        } else if (selectedItemID < 2000000) {
            handleCompany();
        } else {
            handleClient();
        }

    }

    /**
     * Handle search button.
     *
     * @param event the event
     */
    @FXML
    void handleSearchButton(MouseEvent event) {
        studentTableView.getItems().clear();
        clientTableView.getItems().clear();
        companyTableView.getItems().clear();
        showCompanyTagTable();
        showStudentTagTable();
        showClientTagTable();
    }

    /**
     * Show company tag table.
     */
    public void showCompanyTagTable(){
        TableViewSelectHandler tableViewSelectHandler = new TableViewSelectHandler(companyTableView, this);
        tableViewSelectHandler.createCheckBoxColumn();
        tableViewSelectHandler.createSelectAllCheckBox();

        for (Company company : companyDAO.getCompanies()){
            if(company.getTag().toLowerCase().contains(searchTextField.getText().toLowerCase())){
                companies.add(company);
            }
        }
        companyNameColumn.setCellValueFactory(new PropertyValueFactory<Company, String>("companyName"));
        companyTagsColumn.setCellValueFactory(new PropertyValueFactory<Company, String>("tag"));
        companyTableView.setItems(companies);
    }

    /**
     * Show student tag table.
     */
    public void showStudentTagTable(){
        TableViewSelectHandler tableViewSelectHandler = new TableViewSelectHandler(studentTableView, this);
        tableViewSelectHandler.createCheckBoxColumn();
        tableViewSelectHandler.createSelectAllCheckBox();

        for (Student student : studentDAO.selectAllStudents()){
            if(student.getTag().toLowerCase().contains(searchTextField.getText().toLowerCase())){
                students.add(student);
            }
        }
        studentLastnameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
        studentTagsColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("tag"));
        studentTableView.setItems(students);
    }

    /**
     * Show client tag table.
     */
    public void showClientTagTable(){

        TableViewSelectHandler tableViewSelectHandler = new TableViewSelectHandler(clientTableView, this);
        tableViewSelectHandler.createCheckBoxColumn();
        tableViewSelectHandler.createSelectAllCheckBox();

        for (Client client : clientDAO.selectAllClients()){
            if(client.getTag().toLowerCase().contains(searchTextField.getText().toLowerCase())){
                clients.add(client);
            }
        }
        clientLastnameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
        clientTagsColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("tag"));
        clientTableView.setItems(clients);
    }

    @Override
    public void setSelectedRows(ArrayList selectedRows) {
        this.selectedRows = selectedRows;
    }

    @Override
    public void setSelectedItem(int selectedItemId) {
        this.selectedItemID = selectedItemId;
    }

    private void handleStudent() {
        if(selectedRows.size() != 0) {
            ArrayList <EditStudentController> controller = new ArrayList<>();
            controller.add(new EditStudentController());
            controller.get(0).setSelectedItem(selectedItemID);
            addContent(controller.get(0), resources.getString("NEW_STUDENT_DIALOG"));
            controller.remove(true);
        }
    }

    private void handleClient() {
        if (selectedRows.size() != 0) {
            ArrayList<EditClientController> controller = new ArrayList<>();
            controller.add(new EditClientController());
            controller.get(0).setSelectedItem(selectedItemID);
            addContent(controller.get(0), resources.getString("NEW_CLIENT_DIALOG"));
            controller.remove(true);
        }
    }

    private void handleCompany() {
        if (selectedRows.size() != 0) {
            ArrayList<CompanyEditController> controller = new ArrayList<>();
            controller.add(new CompanyEditController());
            controller.get(0).setSelectedItem(selectedItemID);
            addContent(controller.get(0), resources.getString("NEW_COMPANY_DIALOG"));
            controller.remove(true);
        }
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
