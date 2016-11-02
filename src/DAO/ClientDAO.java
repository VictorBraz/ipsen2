package DAO;

import Model.Address;
import Model.Client;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * The type Client dao.
 */
public class ClientDAO extends DAO {

    private AddressDAO addressDAO;

    //TODO testen en notitieid toevoegen in de logica van client

    /**
     * Instantiates a new Client dao.
     *
     * @throws IllegalAccessException the illegal access exception
     * @throws SQLException           the sql exception
     * @throws InstantiationException the instantiation exception
     */
    public ClientDAO() throws IllegalAccessException, SQLException, InstantiationException {
        super();
        this.addressDAO = new AddressDAO();
    }

    /**
     * Add client.
     *
     * @param client the client
     */
    public void addClient(Client client) {
        try {
            addClientQuery(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Select client client.
     *
     * @param clientID the client id
     * @return the client
     */
    public Client selectClient(int clientID) throws SQLException {
        Client client = selectClientQuery(clientID);
        return client;
    }

    /**
     * Select all clients array list .
     *
     * @return the array list
     * @throws SQLException the sql exception
     */
    public ArrayList <Client> selectAllClients() {
        ArrayList<Client> clients = selectAllClientsQuery();

        for(Client client: clients) {
            try {
                client.setAddress(this.addressDAO.selectAddress(client.getAddress().getAddressID()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return clients;

    }

    /**
     * Update client.
     *
     * @param clientID the client id
     * @param client   the client
     */
    public void updateClient(int clientID, Client client) {
        try {
            updateClientQuery(clientID, client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete client.
     *
     * @param clientID the client id
     */
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

    private Client selectClientQuery(int clientID) throws SQLException{
        String sql = "SELECT * FROM client WHERE clientid = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        Client client = null;

        while(result.next()) {
            if(result.equals(client)) {
                client = new Client();
                client.setFirstName(result.getString(2));
                client.setLastName(result.getString(3));
                client.setBirthDate(result.getString(4));
                client.setStudy(result.getString(5));
                client.setEmailAddress(result.getString(6));
                client.setPhoneNumber(result.getString(7));
                client.setTag(result.getString(8));
            }
        }
        return client;
    }

    private ArrayList <Client> selectAllClientsQuery()  {
        ArrayList<Client> clients = new ArrayList<Client>();
        String sql = "SELECT clientid, clientaddressid, firstname," +
                " lastname, birthdate, study, email, phonenumber, clientaddressid FROM client";
        try {
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
                client.setAddress(new Address(result.getInt("clientaddressid")));
                client.setTag(result.getString(8));

                clients.add(client);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return clients;
    }

    private void addClientQuery(Client client) throws SQLException {
        String sql = "INSERT INTO client (clientaddressid, firstname, lastname," +
                " birthdate, study, email, phonenumber, tag) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setInt(1, client.getAddress().getAddressID());
        statement.setString(2, client.getFirstName());
        statement.setString(3, client.getLastName());
        statement.setString(4, client.getBirthDate());
        statement.setString(5, client.getStudy());
        statement.setString(6, client.getEmailAddress());
        statement.setString(7, client.getPhoneNumber());
        statement.setString(8, client.getTag());

        int rowsInserted = statement.executeUpdate();
        if(rowsInserted > 0) {
            System.out.println("A new document was inserted succesfully!");
        }
    }

    private void updateClientQuery(int clientID, Client client) throws Exception {
        String sql = "UPDATE client SET clientaddressid=?, firstname=?, lastname=?, " +
                "birthdate=?, study=?, email=?, phonenumber=?, tag=? WHERE clientid=?";
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
                statement.setString(8, client.getTag());

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("An existing client was updated successfully!");
                }
            }
        }
    }
}