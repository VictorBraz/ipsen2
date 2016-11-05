package Controller.settings;

import Controller.handlers.TableViewListener;
import DAO.AccountDAO;
import Model.Account;
import Model.TableViewItem;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import contentloader.ContentLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    private int selectedAccountID;
    private ObservableList<TableViewItem> accountData;
    private ArrayList<Integer> selectedRows;
    private ArrayList<Account> accountdata;
    private JFXCheckBox selectAllCheckBox;

    private Database database;

    private AccountDAO accountDAO;
    private ResourceBundle resources;

    @FXML
    void handleAccountDeleteButton(MouseEvent event) {

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

    private void fillFields() {
        hostAddressTextField.setText(getHost());
        portNumberTextField.setText(getPort());
        serverNameTextfield.setText(getDatabaseName());
        userNameTextField.setText(getUserName());
        passwordTextField.setText(getPassword());


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
        setHost(hostAddressTextField.getText());
        setPort(portNumberTextField.getText());
        setDatabaseName(serverNameTextfield.getText());
        setUserName(userNameTextField.getText());
        setPassword(passwordTextField.getText());
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
        setMainFrameTitle(resources.getString("SETTINGS_TITLE"));



        try {
            accountDAO = new AccountDAO();
            selectedRows = new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
        fillFields();
        editable(false);

        accountData = FXCollections.observableArrayList(accountDAO.getAllAccounts());
    }

}

