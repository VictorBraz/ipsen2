package Controller.Student;

import Controller.handlers.TableViewListener;
import DAO.AddressDAO;
import DAO.DocumentDAO;
import DAO.NoteDAO;

import DAO.StudentDAO;
import Model.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import contentloader.ContentLoader;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
 * Created by Bernd on 1-11-2016.
 */
public class AddStudentController extends ContentLoader implements Initializable, TableViewListener {


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

    private int selectedDocumentID;
    private ObservableList<TableViewItem> documentData;
    private ArrayList<Integer> selectedRows;
    private JFXCheckBox selectAllCheckBox;

    private StudentDAO studentDAO;
    private AddressDAO addressDAO;
    private NoteDAO noteDAO;
    private ResourceBundle resources;

    private DocumentDAO documentDAO;
    private Document document = new Document();

    public AddStudentController() throws IOException {
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
            document.setDocumentName(selectedFile.getName());
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

    @FXML
    void handleCancelButton(MouseEvent event) {
        addContent(resources.getString("STUDENTS"));
    }

    private void addStudent() {
        Address address = new Address();
        Student student = new Student();
        Note note = new Note();
        address.setAddress(adresTextField.getText());
        address.setZipCode(zipCodeTextField.getText());
        address.setCity(cityTextField.getText());
        student.setAddress(addressDAO.addAddress(address));
        student.setFirstName(firstNameTextField.getText());
        student.setLastName(lastNameTextField.getText());
        student.setBirthDate(birthDateTextfield.getText());
        student.setEmailAddress(emailTextfield.getText());
        student.setPhoneNumber(phoneTextField.getText());
        student.setStudy(studyTextField.getText());
        student.setStudentID(studentDAO.addStudent(student).getStudentID());
        System.out.println(student.getStudentID());
        note.setOwnerID(student.getStudentID());
        note.setText(noteTextField.getText());
        note.setNoteID(noteDAO.addNote(note).getNoteID());
        // note, documents en tags nog toevoegen.
        // relatie nog volledig doen.

    }

    @FXML
    void handleComfirmButton(MouseEvent event) {
        addStudent();
        addContent(resources.getString("STUDENTS"));
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
    public void openEditMenu() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        try {
            this.studentDAO = new StudentDAO();
            this.addressDAO = new AddressDAO();
            this.documentDAO = new DocumentDAO();
            this.noteDAO = new NoteDAO();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
