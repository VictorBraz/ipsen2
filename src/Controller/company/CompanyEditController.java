package Controller.company;

import Controller.handlers.TableViewListener;
import Controller.handlers.TableViewSelectHandler;
import DAO.AddressDAO;
import DAO.CompanyDAO;
import DAO.NoteDAO;
import Model.*;
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
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
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
    @FXML private JFXButton editButton;


    @FXML private TableView<TableViewItem> tableView;
    @FXML private TableColumn checkBoxColumn;
    @FXML private TableColumn documentIDColumn;
    @FXML private TableColumn fileNameColumn;

    @FXML private JFXButton cancelButton;
    @FXML private JFXButton submitButton;

    private ResourceBundle resources;
    private Company currentCompany;
    private Address currentAddress;
    private Note note;
    private int id;
    private ArrayList<Integer> selectedRows;

    private CompanyDAO companyDAO;
    private NoteDAO noteDAO;
    private AddressDAO addressDAO;

    private ObservableList<TableViewItem> documentData;
    private ArrayList<Document> documents = new ArrayList<>();


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

        try{
            note = noteDAO.selectNote(currentCompany.getId());

        }catch (Exception e){
            e.printStackTrace();
        }
        noteTextField.setText(note.getText());
        documentData = FXCollections.observableArrayList(documents);
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
        deleteFileButton.setDisable(!editBoolean);
        deleteFileButton.setVisible(editBoolean);
        cancelButton.setDisable(!editBoolean);
        cancelButton.setVisible(editBoolean);
        submitButton.setDisable(!editBoolean);
        submitButton.setVisible(editBoolean);
        editButton.setVisible(!editBoolean);
        editButton.setDisable(editBoolean);
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
            note = noteDAO.selectNote(currentCompany.getId());
            note.setText(noteTextField.getText());
            noteDAO.update(note);
            addressDAO = new AddressDAO();
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
            documents.add(document);
        }
        documentData = FXCollections.observableArrayList(documents);
        showTable();
    }

    @FXML
    void handleDeleteFileButton(MouseEvent event){

    }

    @FXML
    void handleCancelButton(MouseEvent event){
        fillFields();
        editable(false);
        bedrijfLabel.setText("Bedrijf Bekijken");
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
        bedrijfLabel.setText("Bedrijfsgegevens Aanpassen");
    }

    @Override
    public void openEditMenu() {

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
        this.resources = resources;
        try {
            this.companyDAO = new CompanyDAO();
            this.noteDAO = new NoteDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }
        fillFields();
        bedrijfLabel.setText("Bedrijfsgegevens Bekijken");
        editable(false);

    }
}
