package Controller;

import DAO.ClientDAO;
import Model.Client;
import javafx.collections.FXCollections;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Bernd on 12-10-2016.
 */
public class ClientController {

    private ClientDAO dao;

    public ClientController() {
        try {
            this.dao = new ClientDAO();
        } catch (Exception e) {
            e.printStackTrace();
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
        dao.addClient(client);
    }

    public void cmdDeleteClient(int clientID) {
        dao.deleteClient(clientID);
    }

    public void cmdEditClient(int clientID, Client client) {
        dao.updateClient(clientID, client);
    }
}
