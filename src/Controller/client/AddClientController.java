package Controller.client;

/**
 * Created by Bernd on 31-10-2016.
 */

import Controller.handlers.TableViewListener;
import Controller.handlers.TableViewSelectHandler;
import DAO.AddressDAO;
import DAO.ClientDAO;
import DAO.DocumentDAO;
import Model.Document;
import Model.TableViewItem;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import contentloader.ContentLoader;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class AddClientController extends ContentLoader implements Initializable, TableViewListener {

    @FXML private JFXTextField firstNameTextField;
    @FXML private JFXTextField lastNameTextField;
    @FXML private JFXTextField birthDateTextfield;
    @FXML private JFXTextField adresTextField;
    @FXML private JFXTextField zipCodeTextField;
    @FXML private JFXTextField cityTextField;
    @FXML private JFXTextField studyTextField;
    @FXML private JFXTextField emailTextfield;
    @FXML private JFXTextField phoneTextField;
    @FXML private JFXTextField tagsTextField;
    @FXML private JFXTextArea noteTextField;

    @FXML private JFXButton fileAddButton;
    @FXML private JFXButton deleteFileButton;
    @FXML private TableView<TableViewItem> tableView;
    @FXML private TableColumn<?, ?> checkBoxColumn;
    @FXML private TableColumn<Document, String> documentIDColumn;
    @FXML private TableColumn<Document, String> fileNameColumn;

    @FXML private JFXButton cancelButton;
    @FXML private JFXButton submitButton;

    private int selectedDocumentID;
    private ObservableList<TableViewItem> documentData;
    private ArrayList<Integer> selectedRows;
    private JFXCheckBox selectAllCheckBox;

    private ClientDAO clientDAO;
    private AddressDAO addressDAO;
    private ResourceBundle resources;

    private DocumentDAO documentDAO;
    private Document document = new Document();

    public AddClientController() throws IOException {
        this.document = document;
    }


    @FXML
    void handleAddFileButton(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.pdf"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"),
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.aac"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        String date = sdf.format(new Date());
         if(selectedFile != null){
            document.setFile(selectedFile);
            document.setDocumentName(String.valueOf(selectedFile));
            document.setDate(date);
            document.setOwnerID(123);
             try {
                 documentDAO.addDocument(document);
             } catch (SQLException e) {
                 e.printStackTrace();
             }
             System.out.println("Document opgeslagen!");
         }

    }

    private void showTable() {

        TableViewSelectHandler tableViewSelectHandler = new TableViewSelectHandler(tableView, this);
        tableViewSelectHandler.createCheckBoxColumn();
        tableViewSelectHandler.createSelectAllCheckBox();

        documentIDColumn.setCellValueFactory(new PropertyValueFactory<>("documentID"));
        fileNameColumn.setCellValueFactory(new PropertyValueFactory<>("documentName"));


        //TODO werkt nog niet
        tableView.setItems(documentData);


        System.out.println(documentIDColumn.getCellValueFactory().toString());
        System.out.println(documentData);
        tableView.setPlaceholder(new Label("Er is geen data beschikbaar"));


    }

    @FXML
    void handleCancelButton(MouseEvent event) {
        addContent(resources.getString("STUDENTS"));

    }

    @FXML
    void handleComfirmButton(MouseEvent event) {

    }

    @FXML
    void handleDeleteFileButton(MouseEvent event) throws SQLException {
        documentDAO.deleteDocument(document.getDocumentID());
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
        try {
            this.clientDAO = new ClientDAO();
            this.addressDAO = new AddressDAO();
            this.documentDAO = new DocumentDAO();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }



    }
}
