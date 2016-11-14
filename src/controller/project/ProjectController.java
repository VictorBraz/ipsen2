package controller.project;

import DAO.ClientDAO;
import DAO.CompanyDAO;
import DAO.ProjectDAO;
import DAO.StudentDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import contentloader.ContentLoader;
import controller.handlers.TableViewListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Project;
import model.TableViewItem;

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
    @FXML private Pane functionAlert;
    @FXML private JFXButton confirmButton;


    /**
     * The Selected project id.
     */
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

    /**
     * Handle add button.
     *
     * @param event the event
     */
    @FXML
    void handleAddButton(MouseEvent event) {

    }

    /**
     * Handle delete button.
     *
     * @param event the event
     */
    @FXML
    void handleDeleteButton(MouseEvent event) {

    }

    /**
     * Handle zoomin button.
     *
     * @param event the event
     */
    @FXML
    void handleZoominButton(MouseEvent event) {

    }

    /**
     * Handle comfirm button.
     *
     * @param event the event
     */
    @FXML
    void handleComfirmButton(MouseEvent event) {
        addContent(resources.getString("MAINMENU"));

    }

    @Override
    public void setSelectedRows(ArrayList selectedRows) {

    }

    @Override
    public void setSelectedItem(int selectedItemId) {

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

