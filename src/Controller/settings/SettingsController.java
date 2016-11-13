package Controller.settings;

import Controller.handlers.TableViewListener;
import Controller.handlers.TableViewSelectHandler;
import DAO.AccountDAO;
import Model.Account;
import Model.TableViewItem;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import contentloader.ContentLoader;
import contentloader.PropertiesLoader;
import contentloader.PropertiesLoaderInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import services.Database;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Bernd on 4-11-2016.
 */
public class SettingsController extends ContentLoader implements Initializable, TableViewListener {

    @FXML private JFXTextField hostAddressTextField;
    @FXML private JFXTextField portNumberTextField;
    @FXML private JFXTextField serverNameTextfield;
    @FXML private JFXButton cancelButton;
    @FXML private JFXButton submitButton;
    @FXML private JFXTextField userNameTextField;
    @FXML private JFXTextField passwordTextField;
    @FXML private ImageView editSettingsButton;
    @FXML private TableView<TableViewItem> tableView;
    @FXML private TableColumn checkBoxColumn;
    @FXML private TableColumn userNameColumn;
    @FXML private TableColumn passwordColumn;
    @FXML private TableColumn accountIdColumn;

    private int selectedAccountID;
    private ObservableList<TableViewItem> accountData;
    private ArrayList<Integer> selectedRows;
    //private ArrayList<Account> accountdata;
    private JFXCheckBox selectAllCheckBox;
    PropertiesLoaderInterface properties;

    private Database database;

    private AccountDAO accountDAO;
    private ResourceBundle resources;

    @FXML
    void handleAccountDeleteButton(MouseEvent event) {
        if(selectedRows.size() != 0){
            selectedRows.forEach(row -> accountDAO.deleteAccount(row));
            selectedRows.clear();
            addContent(resources.getString("SETTINGS"));
        }
    }

    @FXML
    void handleAddAccountsButton(MouseEvent event) {

    }

    @FXML
    void handleCancelButton(MouseEvent event) {
        fillFields();
        editable(false);

    }

    @FXML
    void handleComfirmButton(MouseEvent event) {
        updateSettings();
        fillFields();
        editable(false);

    }

    @FXML
    void handleEditSettingsButton(MouseEvent event) {
        editable(true);

    }

    @FXML
    void handleZoominAccountButton(MouseEvent event) {

    }

    private void showTable(){
        TableViewSelectHandler handler = new TableViewSelectHandler(tableView, this);
        handler.createCheckBoxColumn();
        handler.createSelectAllCheckBox();

        accountIdColumn.setCellValueFactory(new PropertyValueFactory<Account, Integer>("id"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("userName"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("password"));
        tableView.setItems(accountData);

    }

    private void fillFields() {
        hostAddressTextField.setText(properties.getHost());
        portNumberTextField.setText(properties.getPort());
        serverNameTextfield.setText(properties.getDatabaseName());
        userNameTextField.setText(properties.getUserName());
        passwordTextField.setText(properties.getPassword());


    }

    private void editable(boolean editBoolean) {
        hostAddressTextField.setEditable(editBoolean);
        portNumberTextField.setEditable(editBoolean);
        serverNameTextfield.setEditable(editBoolean);
        userNameTextField.setEditable(editBoolean);
        passwordTextField.setEditable(editBoolean);
        cancelButton.setVisible(editBoolean);
        submitButton.setVisible(editBoolean);


    }

    private void updateSettings() {
        properties.setHost(hostAddressTextField.getText());
        properties.setPort(portNumberTextField.getText());
        properties.setDatabaseName(serverNameTextfield.getText());
        properties.setUserName(userNameTextField.getText());
        properties.setPassword(passwordTextField.getText());
    }



    @Override
    public void setSelectedRows(ArrayList selectedRows) {
        this.selectedRows = selectedRows;
        System.out.println("Selected rowss: " + selectedRows);
    }

    @Override
    public void setSelectedItem(int selectedItemId) {
        this.selectedAccountID = selectedItemId;
        System.out.println(selectedAccountID);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.resources = resources;
        setMainFrameTitle(resources.getString("SETTINGS_TITLE"));
        properties = new PropertiesLoader();



        try {
            accountDAO = new AccountDAO();
            selectedRows = new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
        fillFields();
        editable(false);

        accountData = FXCollections.observableArrayList(accountDAO.getAllAccounts());
        accountData.forEach(row -> System.out.println(row.getId()));
        showTable();
    }

}

