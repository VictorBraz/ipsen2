package Controller.client;

import Controller.handlers.TableViewListener;
import Controller.handlers.TableViewSelectHandler;
import DAO.AddressDAO;
import DAO.ClientDAO;
import Model.Client;
import Model.TableViewItem;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import contentloader.ContentLoader;
import javafx.animation.FadeTransition;
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
import javafx.util.Duration;

import java.io.IOException;
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
    @FXML private TableColumn firstNameColumn;
    @FXML private TableColumn lastNameColumn;
    @FXML private TableColumn birthDateColumn;
    @FXML private TableColumn addressColumn;
    @FXML private TableColumn zipCodeColumn;
    @FXML private TableColumn cityColum;
    @FXML private TableColumn emailColumn;
    @FXML private TableColumn studyColum;
    @FXML private TableColumn  phoneNumberColumn;
    @FXML private TableColumn tagColumn;
    @FXML private TableColumn clientIdColumn;
    @FXML private Pane deleteAlert;
    @FXML private JFXButton confirmButton;
    @FXML private Pane zoominAlert;



    public int selectedClientID;
    private ObservableList<TableViewItem> clientData;
    private ArrayList<Integer> selectedRows;
    private ArrayList<Client> clientdata;
    private JFXCheckBox selectAllCheckBox;

    private ClientDAO clientDAO;
    private AddressDAO addressDAO;
    private ResourceBundle resources;

    @FXML
    void handleAddButton(MouseEvent event) throws IOException {
        addContent(new AddClientController(), resources.getString("NEW_CLIENT_DIALOG"));
    }

    @FXML
    void handleDeleteButton(MouseEvent event) {
        if (selectedRows.size() != 0) {
            selectedRows.forEach(row -> clientDAO.deleteClient(row));
            System.out.println(selectedRows.toString());
            addContent(resources.getString("CLIENTS"));
        } else {
            deleteAlert.setVisible(true);
            FadeTransition animation = new FadeTransition(Duration.millis(3000));
            animation.setNode(deleteAlert);
            animation.setFromValue(0.0);
            animation.setFromValue(1.0);
            animation.play();
        }
    }

    @FXML
    void handleZoominButton(MouseEvent event) {
        if (selectedRows.size() != 0) {
            ArrayList<EditClientController> controller = new ArrayList<>();
            controller.add(new EditClientController());
            controller.get(0).setSelectedItem(selectedClientID);
            addContent(controller.get(0), resources.getString("NEW_CLIENT_DIALOG"));
            controller.remove(true);
        } else {
            zoominAlert.setVisible(true);
            FadeTransition animation = new FadeTransition(Duration.millis(3000));
            animation.setNode(zoominAlert);
            animation.setFromValue(0.0);
            animation.setFromValue(1.0);
            animation.play();
        }

    }
    @FXML
    void handleComfirmButton(MouseEvent event) {
        deleteAlert.setVisible(false);
        zoominAlert.setVisible(false);

    }



    @Override
    public void setSelectedRows(ArrayList selectedRows) {
        this.selectedRows = selectedRows;


    }

    @Override
    public void setSelectedItem(int selectedItemId) {
        this.selectedClientID = selectedItemId;

    }





    private void showTable() {
        TableViewSelectHandler tableViewSelectHandler = new TableViewSelectHandler(tableView, this);
        tableViewSelectHandler.createCheckBoxColumn();
        tableViewSelectHandler.createSelectAllCheckBox();

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("birthDate"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("emailAddress"));
        studyColum.setCellValueFactory(new PropertyValueFactory<Client, String>("study"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("phoneNumber"));

        tableView.setItems(clientData);
        tableView.setPlaceholder(new Label("Er is geen data beschikbaar"));
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        setMainFrameTitle(resources.getString("CLIENT_TITLE"));

        try {
            clientDAO = new ClientDAO();
            selectedRows = new ArrayList<>();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        clientData = FXCollections.observableArrayList(clientDAO.selectAllClients());
        showTable();
        deleteAlert.setVisible(false);
        zoominAlert.setVisible(false);



    }

}
