package Controller.Student;

import Controller.handlers.TableViewListener;
import Controller.handlers.TableViewSelectHandler;
import DAO.AddressDAO;
import DAO.DocumentDAO;
import DAO.NoteDAO;
import DAO.StudentDAO;
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
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Roel on 3-11-2016.
 */
public class EditStudentController extends ContentLoader implements Initializable, TableViewListener {

    @FXML private Label medewerkerLabel;
    @FXML private JFXTextField firstNameTextField;
    @FXML private JFXTextField lastNameTextField;
    @FXML private JFXTextField birthDateTextfield;
    @FXML private JFXTextField adresTextField;
    @FXML private JFXTextField zipCodeTextField;
    @FXML private JFXTextField cityTextField;
    @FXML private JFXTextField studyTextField;
    @FXML private JFXTextField emailTextfield;
    @FXML private JFXTextField phoneTextField;
    @FXML private JFXTextArea noteTextField;
    @FXML private JFXTextField tagsTextField;
    @FXML private JFXButton fileAddButton;
    @FXML private JFXButton deleteFileButton;

    @FXML private TableView<TableViewItem> tableView;
    @FXML private TableColumn checkBoxColumn;
    @FXML private TableColumn documentIDColumn;
    @FXML private TableColumn fileNameColumn;

    @FXML private JFXButton cancelButton;
    @FXML private JFXButton submitButton;
    @FXML private JFXButton editButton;


    private ResourceBundle resources;
    private StudentDAO studentDAO;
    private AddressDAO addressDAO;
    private DocumentDAO documentDAO;
    private NoteDAO noteDAO;

    private Student currentStudent;
    private Note currentNote;
    private int id;

    private ObservableList<TableViewItem> documentData;
    private ArrayList<Document> currentDocuments = new ArrayList<Document>();

    private void fillFields(){
        currentStudent = studentDAO.selectStudent(id);
        firstNameTextField.setText(currentStudent.getFirstName());
        lastNameTextField.setText(currentStudent.getLastName());
        birthDateTextfield.setText(currentStudent.getBirthDate());
        adresTextField.setText(currentStudent.getAddress().getAddress());
        zipCodeTextField.setText(currentStudent.getAddress().getZipCode());
        cityTextField.setText(currentStudent.getAddress().getCity());
        studyTextField.setText(currentStudent.getStudy());
        emailTextfield.setText(currentStudent.getEmailAddress());
        tagsTextField.setText(currentStudent.getTag());
        phoneTextField.setText(currentStudent.getPhoneNumber());

        try {
            currentNote = noteDAO.selectNote(currentStudent.getId());
            currentDocuments = documentDAO.selectAllDocuments(currentStudent.getId());
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
        adresTextField.setEditable(editBoolean);
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

    private void updateStudent(){
        // werkt nog niet
        Address address = currentStudent.getAddress();
        address.setAddress(adresTextField.getText());
        address.setZipCode(zipCodeTextField.getText());
        address.setCity(cityTextField.getText());
        currentStudent.setFirstName(firstNameTextField.getText());
        currentStudent.setLastName(lastNameTextField.getText());
        currentStudent.setBirthDate(birthDateTextfield.getText());
        currentStudent.setEmailAddress(emailTextfield.getText());
        currentStudent.setPhoneNumber(phoneTextField.getText());
        currentStudent.setStudy(studyTextField.getText());
        currentStudent.setAddress(address);
        currentStudent.setTag(tagsTextField.getText());


        try {
            currentNote = noteDAO.selectNote(currentStudent.getId());
            currentNote.setText(noteTextField.getText());
            noteDAO.update(currentNote);
            studentDAO.updateStudent(currentStudent);
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

    @FXML
    void handleCancelButton(MouseEvent event){
        fillFields();
        editable(false);
        medewerkerLabel.setText("Medewerker Bekijken");
    }

    @FXML
    void handleEditButton(MouseEvent event) throws SQLException{
        editable(true);
        medewerkerLabel.setText("Medewerker Aanpassen");

    }
    @FXML
    void handleComfirmButton(MouseEvent event) throws IOException, SQLException {
        updateStudent();
        fillFields();
        editable(false);
    }

    @FXML
    void handleDeleteFileButton(MouseEvent event){

    }

    @Override
    public void setSelectedRows(ArrayList selectedRows) {

    }

    @Override
    public void setSelectedItem(int selectedItemId) {
        id = selectedItemId;
    }

    @Override
    public void openEditMenu() {

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            this.studentDAO = new StudentDAO();
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
        medewerkerLabel.setText("Medewerker Bekijken");
        editable(false);
    }
}