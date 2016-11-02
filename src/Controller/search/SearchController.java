package Controller.search;

import Controller.handlers.TableViewListener;
import DAO.ClientDAO;
import DAO.CompanyDAO;
import Model.TableViewItem;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import contentloader.ContentLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    @FXML private TableColumn<?, ?> studentTagsColumn;
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

    @FXML
    void handleCancelButton(MouseEvent event) {
        addContent(resources.getString("MAINMENU"));


    }

    @FXML
    void handleComfirmButton(MouseEvent event) {

    }

    @FXML
    void handleSearchButton(MouseEvent event) {

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
        setMainFrameTitle(resources.getString("SEARCH_TITLE"));
            try {
            this.companyDAO = new CompanyDAO();
            this.clientDAO = new ClientDAO();
            this.studentDAO = new StudentDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
