package DAO;

import model.Address;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Bernd on 13-10-2016.
 */
public class AddressDAO extends DAO {

    /**
     * Instantiates a new Address dao.
     *
     * @throws IllegalAccessException the illegal access exception
     * @throws InstantiationException the instantiation exception
     * @throws SQLException           the sql exception
     */
    public AddressDAO() throws IllegalAccessException, InstantiationException, SQLException {
        super();
    }

    /**
     * Add address address.
     *
     * @param address the address
     * @return the address
     */
    public Address addAddress(Address address) {
        try {
            address = addAddressQuery(address);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address;
    }

    /**
     * Update address.
     *
     * @param address the address
     */
    public void updateAddress(Address address) {
        try {
            updateAddressQuery(address);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Select address address.
     *
     * @param addressID the address id
     * @return the address
     * @throws Exception the exception
     */
    public Address selectAddress(int addressID) throws Exception {
        Address address = selectAddressQuery(addressID);
        return address;
    }

    /**
     * Delete address.
     *
     * @param addressID the address id
     */
    public void deleteAddress(int addressID) {
        try {
            deleteAddressQuery(addressID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void deleteFlagAddress(int addressID) {
//        deleteFlagAddressQuery();
//    }


    private Address addAddressQuery(Address address) throws SQLException {
        String sql = "INSERT INTO address (address, zipcode, city) VALUES (?,?,?)";
        PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        statement.setString(1, address.getAddress());
        statement.setString(2, address.getZipCode());
        statement.setString(3, address.getCity());

        int rowInserted = statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()) {
            int id = rs.getInt(1);
            address.setAddressID(id);
        }
        return address;
    }

    private void updateAddressQuery(Address address) throws SQLException {

        String sql = "UPDATE address SET address=?, zipcode=?, city=? WHERE addressid=? ";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, address.getAddress());
        statement.setString(2, address.getZipCode());
        statement.setString(3, address.getCity());
        statement.setInt(4, address.getAddressID());
        int rowUpdated = statement.executeUpdate();
        if(rowUpdated > 0){
            System.out.println("Address updated successfully!");
        }
    }

    private Address selectAddressQuery(int addressID) throws SQLException {
        String sql = "SELECT * FROM address WHERE addressid =?";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.getGeneratedKeys();
        Address address = null;

        while(result.next()) {
            if(result.equals(addressID)) {
                address = new Address();
                address.setAddress(result.getString(1));
                address.setZipCode(result.getString(2));
                address.setCity(result.getString(3));
            }
        }
        return address;
    }

    private void deleteAddressQuery(int addressID) throws SQLException {
        String sql = "DELETE FROM address WHERE addressid=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet keyResultSet = statement.getGeneratedKeys();

        while(keyResultSet.next()) {
            if(keyResultSet.equals(addressID)) {
                int rowsDeleted = statement.executeUpdate();
                if(rowsDeleted > 0) {
                    System.out.println("A client was deleted succesfully!");
                }
            }
        }
    }
}
