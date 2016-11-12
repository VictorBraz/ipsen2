package Controller.client;

import Controller.handlers.TableViewListener;
import Controller.handlers.TableViewSelectHandler;
import DAO.AddressDAO;
import DAO.ClientDAO;
import DAO.DocumentDAO;
import DAO.NoteDAO;
import Model.*;
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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Bernd on 2-11-2016.
 */
public class EditClientController extends ContentLoader implements Initializable, TableViewListener {

    @FXML private Label clientLabel;
    @FXML private JFXTextField firstNameTextField;
    @FXML private JFXTextField lastNameTextField;
    @FXML private JFXTextField birthDateTextfield;
    @FXML private JFXTextField addressTextField;



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
    @FXML private Pane editButton;

    private int selectedDocumentID;
    private ObservableList<TableViewItem> documentData;
    private ArrayList<Integer> selectedRows;
    private JFXCheckBox selectAllCheckBox;

    private ClientDAO clientDAO;
    private AddressDAO addressDAO;
    private NoteDAO noteDAO;
    private DocumentDAO documentDAO;

    private Client currentClient;
    private Note currentNote;
    private int id;

    private ResourceBundle resources;

    private ArrayList<Document> currentDocuments = new ArrayList<Document>();

    @FXML
    void handleCancelButton(MouseEvent event){
        fillFields();
        editable(false);
        clientLabel.setText("Cliënt Informatie");
    }

    @FXML
    void handleEditButton(MouseEvent event) throws SQLException{
        editable(true);
        clientLabel.setText("Cliënt Aanpassen");

    }
    @FXML
    void handleComfirmButton(MouseEvent event) throws IOException, SQLException {
        updateClient();
        fillFields();
        editable(false);
    }

    @FXML
    void handleDeleteFileButton(MouseEvent event){

    }

    private void fillFields(){
        currentClient = clientDAO.selectClient(id);

        firstNameTextField.setText(currentClient.getFirstName());
        lastNameTextField.setText(currentClient.getLastName());
        birthDateTextfield.setText(currentClient.getBirthDate());

        zipCodeTextField.setText(currentClient.getAddress().getZipCode());
        cityTextField.setText(currentClient.getAddress().getCity());
        studyTextField.setText(currentClient.getStudy());
        emailTextfield.setText(currentClient.getEmailAddress());
        tagsTextField.setText(currentClient.getTag());
        phoneTextField.setText(currentClient.getPhoneNumber());
        addressTextField.setText(currentClient.getAddress().getAddress());

        try {
            currentNote = noteDAO.selectNote(currentClient.getId());
            currentDocuments = documentDAO.selectAllDocuments(currentClient.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        noteTextField.setText(currentNote.getText());
        documentData = FXCollections.observableArrayList(currentDocuments);

    }
    private void editable(boolean editBoolean){
        firstNameTextField.setEditable(editBoolean);
        lastNameTextField.setEditable(editBoolean);
        birthDateTextfield.setEditable(editBoolean);
        addressTextField.setEditable(editBoolean);
        zipCodeTextField.setEditable(editBoolean);
        cityTextField.setEditable(editBoolean);
        studyTextField.setEditable(editBoolean);
        emailTextfield.setEditable(editBoolean);
        phoneTextField.setEditable(editBoolean);
        noteTextField.setEditable(editBoolean);
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

    private void updateClient(){
        // werkt nog niet
        Address address = currentClient.getAddress();
        address.setAddress(addressTextField.getText());
        address.setZipCode(zipCodeTextField.getText());
        address.setCity(cityTextField.getText());
        currentClient.setFirstName(firstNameTextField.getText());
        currentClient.setLastName(lastNameTextField.getText());
        currentClient.setBirthDate(birthDateTextfield.getText());
        currentClient.setEmailAddress(emailTextfield.getText());
        currentClient.setPhoneNumber(phoneTextField.getText());
        currentClient.setStudy(studyTextField.getText());
        currentClient.setAddress(address);
        currentClient.setTag(tagsTextField.getText());


        try {
            currentNote = noteDAO.selectNote(currentClient.getId());
            currentNote.setText(noteTextField.getText());
            noteDAO.update(currentNote);
            clientDAO.updateClient(currentClient);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void handleAddFileButton(MouseEvent event) throws IOException {
        Document document = new Document();

        FileChooser fileChooser = new FileChooser();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        String date = sdf.format(new Date());

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
            //documents.add(document);
        }
        //documentData = FXCollections.observableArrayList(documents);
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


    @Override
    public void setSelectedRows(ArrayList selectedRows) {

    }

    @Override
    public void setSelectedItem(int selectedItemId) {
        id = selectedItemId;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            this.clientDAO = new ClientDAO();
            this.addressDAO = new AddressDAO();
            this.documentDAO = new DocumentDAO();
            this.noteDAO = new NoteDAO();

        }catch (IllegalAccessException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (InstantiationException e){
            e.printStackTrace();
        }
        this.resources = resources;
        fillFields();
        editable(false);
        this.clientLabel.setText("Client Informatie");

    }
}
