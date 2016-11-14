package controller.company;

import controller.handlers.TableViewListener;
import controller.handlers.TableViewSelectHandler;
import DAO.AddressDAO;
import DAO.CompanyDAO;
import DAO.DocumentDAO;
import DAO.NoteDAO;
import model.*;
import com.jfoenix.controls.JFXButton;
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

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Victor on 3-11-2016.
 */
public class CompanyEditController extends ContentLoader implements Initializable, TableViewListener {

    @FXML private Label bedrijfLabel = new Label();
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
    @FXML private JFXButton openFileButton;
    @FXML private Pane editButton;


    @FXML private TableView<TableViewItem> tableView;
    @FXML private TableColumn checkBoxColumn;
    @FXML private TableColumn fileIDColumn;
    @FXML private TableColumn fileNameColumn;

    @FXML private JFXButton cancelButton;
    @FXML private JFXButton submitButton;

    private ResourceBundle resources;
    private Company currentCompany;
    private Address currentAddress;
    private Note currentNote;
    private int id;
    private ArrayList<Integer> selectedRows;

    private CompanyDAO companyDAO;
    private NoteDAO noteDAO;
    private AddressDAO addressDAO;
    private DocumentDAO documentDAO;

    private ObservableList<TableViewItem> documentData;
    private ArrayList<Document> currentDocuments = new ArrayList<>();


    private void fillFields(){
        System.out.println("selected company id:" + id);
        currentCompany = companyDAO.selectCompany(id);
        Address address = currentCompany.getCompanyAddress();

        companyNameTextField.setText(currentCompany.getCompanyName());
        contactPersonTextField.setText(currentCompany.getContactPerson());
        addressNameTextField.setText(currentCompany.getCompanyAddress().getAddress());
        zipCodeTextfield.setText(currentCompany.getCompanyAddress().getZipCode());
        cityTextField.setText(currentCompany.getCompanyAddress().getCity());
        phoneNumberTextField.setText(currentCompany.getPhoneNumber());
        emailTextField.setText(currentCompany.getEmailAddress());
        tagsTextField.setText(currentCompany.getTag());

        try {
            currentNote = noteDAO.selectNote(currentCompany.getId());
            currentDocuments = documentDAO.selectAllDocuments(currentCompany.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        noteTextField.setText(currentNote.getText());
        System.out.print("currentdocuments: " + currentDocuments.size());
        System.out.print("currentcompanyid: " + currentCompany.getId());
//        System.out.print("currentdocuments: " + currentDocuments.size());
        documentData = FXCollections.observableArrayList(currentDocuments);
        showTable();
    }

    private void editable(boolean editBoolean){

        companyNameTextField.setEditable(editBoolean);
        contactPersonTextField.setEditable(editBoolean);
        addressNameTextField.setEditable(editBoolean);
        zipCodeTextfield.setEditable(editBoolean);
        cityTextField.setEditable(editBoolean);
        phoneNumberTextField.setEditable(editBoolean);
        emailTextField.setEditable(editBoolean);
        tagsTextField.setEditable(editBoolean);
        fileAddButton.setDisable(!editBoolean);
        fileAddButton.setVisible(editBoolean);
        deleteFileButton.setDisable(editBoolean);
        deleteFileButton.setVisible(!editBoolean);
        cancelButton.setDisable(!editBoolean);
        cancelButton.setVisible(editBoolean);
        submitButton.setDisable(!editBoolean);
        submitButton.setVisible(editBoolean);
        editButton.setVisible(!editBoolean);
        editButton.setDisable(editBoolean);
        openFileButton.setVisible(editBoolean);
        openFileButton.setVisible(!editBoolean);
    }

    private void updateCompany(){

        System.out.println(currentCompany.getCompanyAddress());
        currentAddress = currentCompany.getCompanyAddress();

        currentAddress.setAddress(addressNameTextField.getText());
        currentAddress.setZipCode(zipCodeTextfield.getText());
        currentAddress.setCity(cityTextField.getText());

        currentCompany.setCompanyName(companyNameTextField.getText());
        currentCompany.setContactPerson(contactPersonTextField.getText());
        currentCompany.setCompanyAddress(currentAddress);
        currentCompany.setPhoneNumber(phoneNumberTextField.getText());
        currentCompany.setEmailAddress(emailTextField.getText());
        currentCompany.setTag(tagsTextField.getText());

        try{
            currentNote = noteDAO.selectNote(currentCompany.getId());
            currentNote.setText(noteTextField.getText());
            noteDAO.update(currentNote);
            addressDAO.updateAddress(currentAddress);
            companyDAO.updateCompany(currentCompany);
        }catch (Exception e){
            e.printStackTrace();

        }
    }


    @FXML
    void handleAddFileButton(MouseEvent event) throws Exception{
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
            document.setOwnerID(currentCompany.getId());
            documentDAO.addDocument(document);
        }
        currentDocuments = documentDAO.selectAllDocuments(currentCompany.getId());
        documentData = FXCollections.observableArrayList(currentDocuments);
        showTable();
    }

    @FXML
    void handleDeleteFileButton(MouseEvent event) throws IOException, SQLException {
        System.out.println(selectedRows);
        if (selectedRows.size() != 0) {
            selectedRows.forEach(row -> {
                System.out.println(row);
                try {
                    documentDAO.deleteDocument(row);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            ArrayList <CompanyEditController> controller= new ArrayList<>();
            controller.add(new CompanyEditController());
            controller.get(0).setSelectedItem(currentCompany.getId());
            addContent(controller.get(0),resources.getString("NEW_COMPANY_DIALOG"));
            controller.remove(true);
            currentDocuments = documentDAO.selectAllDocuments(currentCompany.getId());
            documentData = FXCollections.observableArrayList(currentDocuments);
            showTable();
        }
    }

    @FXML
    void handleCancelButton(MouseEvent event){
        fillFields();
        editable(false);
        bedrijfLabel.setText("Bedrijf Informatie");
        addContent(resources.getString("COMPANIES"));
    }

    @FXML
    void handleComfirmButton(MouseEvent event){
        updateCompany();
        fillFields();
        editable(false);
    }

    @FXML
    void handleEditButton(MouseEvent event){
        editable(true);
        bedrijfLabel.setText("Bedrijf Bewerken");
    }

    @FXML
    void handleOpenFileButton(MouseEvent event) throws IOException {
        if (selectedRows.size() != 0) {

            selectedRows.forEach(row -> {
                System.out.println(row);
                try {
                    File file = documentDAO.selectDocument(row).getFile();
                    System.out.println("file: " + file.toString());
                    Desktop.getDesktop().open(documentDAO.selectDocument(row).getFile());
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }



    private void showTable() {
        TableViewSelectHandler tableViewSelectHandler = new TableViewSelectHandler(tableView, this);
        tableViewSelectHandler.createCheckBoxColumn();
        tableViewSelectHandler.createSelectAllCheckBox();
        fileNameColumn.setCellValueFactory(new PropertyValueFactory<>("documentName"));
        fileIDColumn.setCellValueFactory(new PropertyValueFactory<Document,Integer>("id"));
        tableView.setItems(documentData);
        System.out.println(documentData);
        tableView.setPlaceholder(new Label("Er is geen data beschikbaar"));
    }

    @Override
    public void setSelectedRows(ArrayList selectedRows){
        this.selectedRows = selectedRows;
        System.out.println("rows selected:" + selectedRows);
    }

    @Override
    public void setSelectedItem(int selectedItemId){
        this.id = selectedItemId;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.companyDAO = new CompanyDAO();
            this.addressDAO = new AddressDAO();
            this.documentDAO = new DocumentDAO();
            this.noteDAO = new NoteDAO();
            selectedRows = new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.resources = resources;
        fillFields();
        bedrijfLabel.setText("Bedrijf Informatie");
        editable(false);

    }
}
