package DAO;

import Model.Client;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Bernd on 12-10-2016.
 */
public class ClientDAO extends DAO {

    //TODO testen en notitieid toevoegen in de logica van client

    public ClientDAO() throws IllegalAccessException, SQLException, InstantiationException {
        super();
    }

    public void addClient(Client client) {
        try {
            addClientQuery(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList <Client> selectAllClients() throws SQLException {
        ArrayList<Client> clients = selectAllClientsQuery();
        return clients;
    }

    public void updateClient(int clientID, Client client) {
        try {
            updateClientQuery(clientID, client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteClient(int clientID) {
        try {
            deleteClientQuery(clientID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteClientQuery(int clientID) throws Exception {
        String sql = "DELETE FROM Client WHERE clientid=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet keyResultSet = statement.getGeneratedKeys();

        while (keyResultSet.next()) {
            if(keyResultSet.equals(clientID)) {
                int rowsDeleted = statement.executeUpdate();
                if(rowsDeleted > 0) {
                    System.out.println("A Client was deleted succesfully");
                }
            }
        }
    }

    private ArrayList <Client> selectAllClientsQuery() throws SQLException {
        ArrayList<Client> clients = new ArrayList<Client>();
        String sql = "SELECT * FROM Client";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {
            Client client = new Client();
            client.setFirstName(result.getString(2));
            client.setLastName(result.getString(3));
            client.setBirthDate(result.getString(4));
            client.setStudy(result.getString(5));
            client.setEmailAddress(result.getString(6));
            client.setPhoneNumber(result.getString(7));

            clients.add(client);
        }
        return clients;
    }

    private void addClientQuery(Client client) throws SQLException {
        String sql = "INSERT INTO client (clientid, clientaddressid, firstname, lastname," +
                " birthdate, study, email, phonenumber) VALUES (null, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setInt(1, client.getAddress().getAddressID());
        statement.setString(2, client.getFirstName());
        statement.setString(3, client.getLastName());
        statement.setString(4, client.getBirthDate());
        statement.setString(5, client.getStudy());
        statement.setString(6, client.getEmailAddress());
        statement.setString(7, client.getPhoneNumber());

        int rowsInserted = statement.executeUpdate();
        if(rowsInserted > 0) {
            System.out.println("A new client was inserted succesfully!");
        }
    }

    private void updateClientQuery(int clientID, Client client) throws Exception {
        String sql = "UPDATE client SET clientaddressid=?, firstname=?, lastname=?, " +
                "birthdate=?, study=?, email=?, phonenumber=? WHERE clientid=?";
        PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ResultSet keyResultSet = statement.getGeneratedKeys();
        while (keyResultSet.next()) {
            if(keyResultSet.equals(clientID)) {
                statement.setInt(1, client.getAddress().getAddressID());
                statement.setString(2, client.getFirstName());
                statement.setString(3, client.getLastName());
                statement.setString(4, client.getBirthDate());
                statement.setString(5, client.getStudy());
                statement.setString(6, client.getEmailAddress());
                statement.setString(7, client.getPhoneNumber());

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("An existing client was updated successfully!");
                }
            }
            System.out.println("no existing id!");
        }
    }
}