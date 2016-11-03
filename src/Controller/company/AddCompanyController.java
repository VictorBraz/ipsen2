package Controller.company;

import Controller.handlers.TableViewListener;
import DAO.AddressDAO;
import DAO.CompanyDAO;
import Model.Address;
import Model.Company;
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
import java.util.ArrayList;
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

    @FXML
    void handleAddFileButton(MouseEvent event) {


    }

    @FXML
    void handleCancelButton(MouseEvent event) {
        addContent(resources.getString("COMPANIES"));

    }

    @FXML
    void handleComfirmButton(MouseEvent event) {

        Company company = new Company();
        Address address = new Address();

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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}