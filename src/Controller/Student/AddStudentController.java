package Controller.Student;

import Controller.handlers.TableViewListener;
import DAO.AddressDAO;
import DAO.NoteDAO;
import DAO.StudentDAO;
import Model.Address;
import Model.Note;
import Model.Student;
import Model.TableViewItem;
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

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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

    @FXML
    void handleAddFileButton(MouseEvent event) {

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
