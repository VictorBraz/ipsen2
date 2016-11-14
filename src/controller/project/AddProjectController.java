package controller.project;

import controller.handlers.TableViewListener;
import DAO.*;
import model.Document;
import model.TableViewItem;
import com.jfoenix.controls.*;
import contentloader.ContentLoader;
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
 * Created by Bernd on 12-11-2016.
 */
public class AddProjectController extends ContentLoader implements Initializable, TableViewListener {

    @FXML private JFXTextField projectNameTextField;
    @FXML private JFXTextArea noteTextField;
    @FXML private JFXTextField tagsTextField;
    @FXML private JFXButton fileAddButton;
    @FXML private JFXButton deleteFileButton;

    @FXML private TableView<?> tableView;
    @FXML private TableColumn<?, ?> checkBoxColumn;
    @FXML private TableColumn<?, ?> fileNameColumn;
    @FXML private JFXButton cancelButton;
    @FXML private JFXButton submitButton;
    @FXML private JFXComboBox<?> studentComboBox;
    @FXML private JFXComboBox<?> companyComboBox;
    @FXML private JFXComboBox<?> clientComboBox;
    @FXML private JFXListView<?> clientProjectList;
    @FXML private JFXListView<?> companyProjectList;

    private int selectedDocumentID;
    private ObservableList<TableViewItem> documentData;
    private ArrayList<Integer> selectedRows;
    private JFXCheckBox selectAllCheckBox;

    private CompanyDAO companyDAO;
    private ProjectDAO projectDAO;
    private StudentDAO studentDAO;
    private ClientDAO clientDAO;
    private ResourceBundle resources;
    private DocumentDAO documentDAO;
    private ArrayList<Document> documents = new ArrayList<Document>();


    @FXML
    void handleAddFileButton(MouseEvent event) {

    }

    @FXML
    void handleCancelButton(MouseEvent event) {

    }

    @FXML
    void handleComfirmButton(MouseEvent event) {

    }

    @FXML
    void handleDeleteFileButton(MouseEvent event) {

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
        try {
            this.projectDAO = new ProjectDAO();
            this.companyDAO = new CompanyDAO();
            this.studentDAO = new StudentDAO();
            this.clientDAO = new ClientDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


