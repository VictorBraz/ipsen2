
package Controller.company;

import Controller.handlers.TableViewListener;
import Controller.handlers.TableViewSelectHandler;
import DAO.AddressDAO;
import DAO.CompanyDAO;
import Model.Company;
import Model.TableViewItem;
import com.jfoenix.controls.JFXCheckBox;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Victor on 13-10-2016.
 */
public class CompanyController extends ContentLoader implements Initializable, TableViewListener {

    @FXML private TableView<TableViewItem> tableView;
    @FXML private TableColumn checkBoxColumn;
    @FXML private TableColumn companyNameColumn;
    @FXML private TableColumn addressColumn;
    @FXML private TableColumn zipCodeColumn;
    @FXML private TableColumn cityColumn;
    @FXML private TableColumn contactPersonColumn;
    @FXML private TableColumn phoneNumberColum;
    @FXML private TableColumn emailColumn;
    @FXML private TableColumn tagColumn;
    @FXML private TableColumn companyIdCol;

    public int selectedCompanyID;
    private ObservableList<TableViewItem> companyData;
    private ArrayList<Integer> selectedRows;
    private JFXCheckBox selectAllCheckBox;

    private CompanyDAO dao;
    private AddressDAO addressDAO;
    private ResourceBundle resources;


    @FXML
    void handleAddButton(MouseEvent event) {
        addContent(resources.getString("NEW_COMPANY_DIALOG"));

    }

    @FXML
    void handleDeleteButton(MouseEvent event) {

        if (selectedRows.size() != 0) {
            selectedRows.forEach(row -> dao.deleteCompany(row));
            System.out.println(selectedRows.toString());
            selectedRows.clear();
            addContent(resources.getString("COMPANIES"));
        } else {
            System.out.println("geen bedrijf geselecteerd");
        }

    }

    @FXML
    void handleZoominButton(MouseEvent event) {

        addContent(resources.getString("EDIT_COMPANY_DIALOG"));
    }

    @Override
    public void setSelectedRows(ArrayList selectedRows) {
        this.selectedRows = selectedRows;
        System.out.println("rows selected:" + selectedRows);
    }

    @Override
    public void setSelectedItem(int selectedItemId) {
        this.selectedCompanyID = selectedItemId;

    }

    @Override
    public void openEditMenu() {
        if(this.selectedCompanyID != 0){
            addContent(resources.getString("EDIT_COMPANY_DIALOG"));
        }
    }

    private void showTable(){
        TableViewSelectHandler tableViewSelectHandler = new TableViewSelectHandler(tableView, this);
        tableViewSelectHandler.createCheckBoxColumn();
        tableViewSelectHandler.createSelectAllCheckBox();

        companyNameColumn.setCellValueFactory(new PropertyValueFactory<Company, String>("companyName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Company, String>("companyAddressId"));
        zipCodeColumn.setCellValueFactory(new PropertyValueFactory<Company, String>("zipcode"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<Company, String>("city"));
        contactPersonColumn.setCellValueFactory(new PropertyValueFactory<Company, String>("contactPerson"));
        phoneNumberColum.setCellValueFactory(new PropertyValueFactory<Company, String>("phoneNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Company, String>("emailAddress"));
        tagColumn.setCellValueFactory(new PropertyValueFactory<Company, String>("tag"));
        companyIdCol.setCellValueFactory(new PropertyValueFactory<Company, Integer>("companyId"));
        tableView.setItems(companyData);

        tableView.setPlaceholder(new Label("Er is geen data beschikbaar"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        setMainFrameTitle(resources.getString("COMPANY_TITLE"));
        try {
            this.dao = new CompanyDAO();
            this.addressDAO = new AddressDAO();
            selectedRows = new ArrayList<>();

        } catch (Exception e) {
            e.printStackTrace();
        }

        companyData = FXCollections.observableArrayList(dao.getCompanies());
        showTable();

    }

}
