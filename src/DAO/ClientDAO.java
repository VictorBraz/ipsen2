package DAO;

import Model.Client;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Bernd on 12-10-2016.
 */
public class ClientDAO extends DAO {

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

    public void selectAllClients() {
        try {
            selectAllClientsQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    private void selectAllClientsQuery() throws SQLException {
        String sql = "SELECT * FROM Client";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);

        int count = 0;

        while (result.next()) {
            String firstName = result.getString(2);
            String lastName = result.getString(3);
            String birthDate = result.getString(4);
            String study = result.getString(5);
            String email = result.getString(6);
            String phoneNumber = result.getString(7);

            String output = "client #%d: %s - %s - %s - %s - %s - %s ";
            System.out.println(String.format(output, ++count, firstName, lastName,
                    birthDate, study, email, phoneNumber));
        }

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
        statement.setString(7, client.getTelephoneNumber());

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
                statement.setString(7, client.getTelephoneNumber());

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("An existing client was updated successfully!");
            }
            System.out.println("no existing id!");
        }
    }
}