package DAO;

import model.Address;
import model.Client;
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
     * @return the client
     */
    public Client addClient(Client client) {
        try {
            addClientQuery(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }

    /**
     * Select client client.
     *
     * @param id the client id
     * @return the client
     */
    public Client selectClient(int id) {
        Client client = null;
        try {
            client = selectClientQuery(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
     * @param client the client
     */
    public void updateClient(Client client) {
        try {
            updateClientQuery(client);
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
        String sql = "DELETE FROM Client WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, clientID);
        statement.executeUpdate();
        statement.close();
    }

    private Client selectClientQuery(int id) throws SQLException{
        Client client = new Client();
        Address address = new Address();

        String sql1 = "SELECT * FROM client WHERE id = ?";
        String sql2 = "SELECT * FROM Address WHERE addressid=?";
        PreparedStatement statement1 = conn.prepareStatement(sql1);
        PreparedStatement statement2 = conn.prepareStatement(sql2);

        statement1.setInt(1,id);
        ResultSet resultSet1 = statement1.executeQuery();
        while (resultSet1.next()){
            statement2.setInt(1,resultSet1.getInt(2));
            ResultSet resultSet2 = statement2.executeQuery();
            client.setId(resultSet1.getInt(1));
            client.setFirstName(resultSet1.getString(3));
            client.setLastName(resultSet1.getString(4));
            client.setBirthDate(resultSet1.getString(5));
            client.setStudy(resultSet1.getString(6));
            client.setEmailAddress(resultSet1.getString(7));
            client.setPhoneNumber(resultSet1.getString(8));
            client.setTag(resultSet1.getString(9));
            while (resultSet2.next()){
                address.setAddressID(resultSet2.getInt(1));
                address.setAddress(resultSet2.getString(2));
                address.setZipCode(resultSet2.getString(3));
                address.setCity(resultSet2.getString(4));
            }
            client.setAddress(address);
        }

        statement1.close();
        statement2.close();
        return client;
    }

    private ArrayList <Client> selectAllClientsQuery()  {
        ArrayList<Client> clients = new ArrayList<Client>();
        String sql = "SELECT id, clientaddressid, firstname," +
                " lastname, birthdate, study, email, phonenumber, tag FROM client";

        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                Client client = new Client();
                client.setId(result.getInt(1));
                client.setFirstName(result.getString(3));
                client.setLastName(result.getString(4));
                client.setBirthDate(result.getString(5));
                client.setStudy(result.getString(6));
                client.setEmailAddress(result.getString(7));
                client.setPhoneNumber(result.getString(8));
                client.setAddress(new Address(result.getInt("clientaddressid")));
                client.setTag(result.getString(9));

                clients.add(client);
                statement.closeOnCompletion();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return clients;
    }

    private Client addClientQuery(Client client) throws SQLException {
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
        ResultSet rs = statement.getGeneratedKeys();

        if (rs.next()) {
            int id = rs.getInt(1);
            client.setId(id);
        }

        statement.close();
        return client;
    }

    private void updateClientQuery(Client client) throws Exception {
        String sql = "UPDATE client SET clientaddressid=?, firstname=?, lastname=?, " +
                "birthdate=?, study=?, email=?, phonenumber=?, tag=? WHERE id=?";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, client.getAddress().getAddressID());
        statement.setString(2, client.getFirstName());
        statement.setString(3, client.getLastName());
        statement.setString(4, client.getBirthDate());
        statement.setString(5, client.getStudy());
        statement.setString(6, client.getEmailAddress());
        statement.setString(7, client.getPhoneNumber());
        statement.setString(8, client.getTag());
        
        statement.setInt(9, client.getId());
        int rowInserted = statement.executeUpdate();

        statement.close();


    }
}