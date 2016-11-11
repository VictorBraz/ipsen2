package Controller.project;

import Controller.handlers.TableViewListener;
import DAO.ClientDAO;
import DAO.CompanyDAO;
import DAO.ProjectDAO;
import DAO.StudentDAO;
import Model.Project;
import Model.TableViewItem;
import com.jfoenix.controls.JFXCheckBox;
import contentloader.ContentLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Bernd on 11-11-2016.
 */
public class ProjectController extends ContentLoader implements Initializable, TableViewListener {

    @FXML private TableView<TableViewItem> tableView;
    @FXML private TableColumn checkBoxColumn;
    @FXML private TableColumn projectNameColumn;
    @FXML private TableColumn studentNameColumn;
    @FXML private TableColumn clientNamesColumn;
    @FXML private TableColumn companyColum;
    @FXML private TableColumn tagsColumn;

    public int selectedProjectID;
    private ObservableList<TableViewItem> projectData;
    private ArrayList<Integer> selectedRows;
    private ArrayList<Project> projectdata;
    private JFXCheckBox selectAllCheckBox;

    private ClientDAO clientDAO;
    private StudentDAO studentDAO;
    private CompanyDAO companyDAO;
    private ProjectDAO projectDAO;

    private ResourceBundle resources;

    @FXML
    void handleAddButton(MouseEvent event) {

    }

    @FXML
    void handleDeleteButton(MouseEvent event) {
        if (selectedRows.size() != 0) {
            selectedRows.forEach(row -> clientDAO.deleteClient(row));
            //clientData = FXCollections.observableArrayList(clientDAO.selectAllClients());
            System.out.println(selectedRows.toString());
            addContent(resources.getString("PROJECTS"));
        }
    }

    @FXML
    void handleZoominButton(MouseEvent event) {


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
        setMainFrameTitle(resources.getString("PROJECT_TITLE"));


            try {
                projectDAO = new ProjectDAO();
                clientDAO = new ClientDAO();
                studentDAO = new StudentDAO();
                companyDAO = new CompanyDAO();
            } catch (Exception e) {
                e.printStackTrace();
            }

            selectedRows = new ArrayList<>();


        projectData = FXCollections.observableArrayList(clientDAO.selectAllClients());
        //showTable();


    }


}

