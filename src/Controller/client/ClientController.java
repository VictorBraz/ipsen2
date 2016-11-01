package Controller.client;

import Controller.handlers.TableViewListener;
import Controller.handlers.TableViewSelectHandler;
import DAO.AddressDAO;
import DAO.ClientDAO;
import Model.Client;
import Model.TableViewItem;
import contentloader.ContentLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
/**
 * Created by Bernd on 12-10-2016.
 */
public class ClientController extends ContentLoader implements Initializable, TableViewListener{

    @FXML  TableView<TableViewItem> tableView;
    @FXML private TableColumn checkBoxColumn;
    @FXML private TableColumn<Client, String> firstNameColumn;
    @FXML private TableColumn<Client, String> lastNameColumn;
    @FXML private TableColumn<Client, String> birthDateColumn;
    @FXML private TableColumn<Client, String> adresColumn;
    @FXML private TableColumn<Client, String> zipCodeColumn;
    @FXML private TableColumn<Client, String> cityColum;
    @FXML private TableColumn<Client, String> emailColumn;
    @FXML private TableColumn<Client, String> studyColum;
    @FXML private TableColumn<Client, String> phoneNumberColumn;
    @FXML private TableColumn tagColumn;


    public int selectedClientID;
    private ObservableList<Client> clientData;
    private ArrayList<Integer> selectedRows;
    private CheckBox selectAllCheckBox;

    private ClientDAO clientDAO;
    private AddressDAO addressDAO;
    private ResourceBundle resources;

    @FXML
    void handleAddButton(MouseEvent event) {
        addContent(new AddClientController(), resources.getString("NEW_CLIENT_DIALOG"));
    }

    @FXML
    void handleDeleteButton(MouseEvent event) {
        if (selectedRows.size() != 0) {
            selectedRows.forEach(row -> clientDAO.deleteClient(row));
            clientData = FXCollections.observableArrayList(clientDAO.selectAllClients());
            addContent(resources.getString("CLIENTS"));
        } else {
            System.out.println("geen client geselecteerd");
        }
    }

    @FXML
    void handleZoominButton(MouseEvent event) {
        if (this.selectedClientID != 0) {
            addContent(new EditClientController(selectedClientID), resources.getString("EDIT_CLIENT_DIALOG"));
        }

    }


//TODO clientenlijst ophalen uit de dao
//    Public ObserverableList <Client> cmdGetClients() {
//        ArrayList<Client> clients = new ArrayList<>();
//        try {
//            clients.addAll(dao.selectAllClients());
//            return FXCollections.observableArrayList(clients);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//    }

    public void cmdAddClient(Client client) {
        clientDAO.addClient(client);
    }

    public void cmdDeleteClient(int clientID) {
        this.clientDAO.deleteClient(clientID);
    }

    public void cmdEditClient(int clientID, Client client) {
        this.clientDAO.updateClient(clientID, client);
    }


    @Override
    public void setSelectedRows(ArrayList selectedRows) {
        this.selectedRows = selectedRows;
    }

    @Override
    public void setSelectedItem(int selectedItemId) {
        this.selectedClientID = selectedClientID;

    }

    @Override
    public void openEditMenu() {

    }

    private void showTable() {
        TableViewSelectHandler tableViewSelectHandler = new TableViewSelectHandler(tableView, this);
        tableViewSelectHandler.createCheckBoxColumn();
        tableViewSelectHandler.createSelectAllCheckBox();

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("firstname"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("lastname"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("birthdate"));
        adresColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("address"));
        zipCodeColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("zipcode"));
        cityColum.setCellValueFactory(new PropertyValueFactory<Client, String>("city"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
        studyColum.setCellValueFactory(new PropertyValueFactory<Client, String>("study"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("phonenumber"));

        //TODO werkt nog niet
     //   tableView.setItems(clientData);

        tableView.setPlaceholder(new Label("Er is geen data beschikbaar"));


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        setMainFrameTitle(resources.getString("CLIENT_TITLE"));
        selectedRows = new ArrayList<>();
        try {
            clientDAO = new ClientDAO();
            addressDAO = new AddressDAO();
            clientData = FXCollections.observableArrayList(clientDAO.selectAllClients());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


    }

}
