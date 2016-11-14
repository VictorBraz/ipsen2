package controller.company;

import controller.handlers.TableViewListener;
import controller.handlers.TableViewSelectHandler;
import DAO.AddressDAO;
import DAO.CompanyDAO;
import DAO.DocumentDAO;
import DAO.NoteDAO;
import model.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import contentloader.ContentLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class AddCompanyController extends ContentLoader implements Initializable, TableViewListener {

    @FXML private JFXTextField companyNameTextField;
    @FXML private JFXTextField addressNameTextField;
    @FXML private JFXTextField zipCodeTextfield;
    @FXML private JFXTextField cityTextField;
    @FXML private JFXTextField contactPersonTextField;
    @FXML private JFXTextField phoneNumberTextField;
    @FXML private JFXTextField emailTextField;
    @FXML private JFXTextArea noteTextField;
    @FXML private JFXTextField tagsTextField;
    @FXML private JFXButton fileAddButton;
    @FXML private JFXButton deleteFileButton;
    @FXML private Pane editButton;
    @FXML private JFXButton openFileButton;

    @FXML private TableView<TableViewItem> tableView;
    @FXML private TableColumn checkBoxColumn;
    @FXML private TableColumn documentIDColumn;
    @FXML private TableColumn fileNameColumn;

    @FXML private JFXButton cancelButton;
    @FXML private JFXButton submitButton;
    @FXML private JFXButton handleEditButton;

    private int selectedDocumentID;
    private ObservableList<TableViewItem> documentData;
    private ArrayList<Integer> selectedRows;
    private JFXCheckBox selectAllCheckBox;

    private CompanyDAO companyDAO;
    private AddressDAO addressDAO;
    private ResourceBundle resources;
    private DocumentDAO documentDAO;
    private NoteDAO noteDAO;
    private ArrayList<Document> documents = new ArrayList<Document>();

    @FXML
    void handleAddFileButton(MouseEvent event) throws IOException {
        Document document = new Document();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        String date = sdf.format(new Date());

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.pdf"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"),
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.aac"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if(selectedFile != null){
            document.setFile(selectedFile);
            document.setDocumentName(selectedFile.getName());
            document.setDate(date);
            documents.add(document);
        }
        documentData = FXCollections.observableArrayList(documents);
        showTable();
    }

    private void showTable() {

        TableViewSelectHandler tableViewSelectHandler = new TableViewSelectHandler(tableView, this);
        tableViewSelectHandler.createCheckBoxColumn();
        tableViewSelectHandler.createSelectAllCheckBox();

        fileNameColumn.setCellValueFactory(new PropertyValueFactory<>("documentName"));
        tableView.setItems(documentData);
        System.out.println(documentData);
        tableView.setPlaceholder(new Label("Er is geen data beschikbaar"));
    }

    @FXML
    void handleCancelButton(MouseEvent event) {
        addContent(resources.getString("COMPANIES"));

    }

    @FXML
    void handleOpenFileButton(MouseEvent event) throws IOException {}


    @FXML
    void handleComfirmButton(MouseEvent event) throws IOException {

        Company company = new Company();
        Address address = new Address();
        Document document = new Document();
        Note note = new Note();

        try {
            companyDAO = new CompanyDAO();
            AddressDAO addressDAO = new AddressDAO();
            company.setCompanyName(companyNameTextField.getText());
            company.setContactPerson(contactPersonTextField.getText());
            company.setPhoneNumber(phoneNumberTextField.getText());
            company.setEmailAddress(emailTextField.getText());

            address.setAddress(addressNameTextField.getText());
            address.setCity(cityTextField.getText());
            address.setZipCode(zipCodeTextfield.getText());
            addressDAO.addAddress(address);
            company.setCompanyAddress(address);
            company.setTag(tagsTextField.getText());
            company.setId(companyDAO.addCompany(company).getId());
            System.out.println(company.getId());
            note.setOwnerID(company.getId());
            note.setText(noteTextField.getText());
            note.setNoteID(noteDAO.addNote(note).getNoteID());
            System.out.println(company.getId());


            for(int i =0; i < documents.size(); i++) {
                documents.get(i).setOwnerID(company.getId());
                documentDAO.addDocument(documents.get(i));
            }
            documents.clear();

            documents.clear();
            addContent(resources.getString("COMPANIES"));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void handleDeleteFileButton(MouseEvent event) {
        documents.clear();
        documentData = FXCollections.observableArrayList(documents);
        showTable();
    }

    @FXML
    void handleEditButton(MouseEvent event){

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
        editButton.setVisible(false);
        editButton.setDisable(true);
        openFileButton.setVisible(false);
        openFileButton.setDisable(true);
        try {
            this.companyDAO = new CompanyDAO();
            this.addressDAO = new AddressDAO();
            this.documentDAO = new DocumentDAO();
            this.noteDAO = new NoteDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}