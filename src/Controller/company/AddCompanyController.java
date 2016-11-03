package Controller.company;

import Controller.handlers.TableViewListener;
import DAO.AddressDAO;
import DAO.CompanyDAO;
import DAO.DocumentDAO;
import Model.Address;
import Model.Company;
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

    private CompanyDAO companyDAO;
    private AddressDAO addressDAO;
    private ResourceBundle resources;
    private DocumentDAO documentDAO;

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
        addContent(resources.getString("COMPANIES"));

    }

    @FXML
    void handleComfirmButton(MouseEvent event) throws IOException {

        Company company = new Company();
        Address address = new Address();
        Document document = new Document();

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
            company.setCompanyAddressid(address);
            companyDAO.addCompany(company);
            System.out.println(company.getId());
            document.setOwnerID(company.getId());

            addContent(resources.getString("COMPANIES"));

        }catch (Exception e){
            e.printStackTrace();
        }
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
            this.companyDAO = new CompanyDAO();
            this.addressDAO = new AddressDAO();
            this.documentDAO = new DocumentDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}