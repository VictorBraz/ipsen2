package DAO;

import Model.Address;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * Created by Bernd on 13-10-2016.
 */
public class AddressDAO extends DAO {

    public AddressDAO() throws IllegalAccessException, InstantiationException, SQLException {
        super();
    }

    public Address addAddress(Address address) {
        try {
            address = addAddressQuery(address);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address;
    }

    public void updateAddress(int addressID, Address address) {
        try {
            updateAddressQuery(addressID, address);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Address selectAddress(int addressID) throws Exception {
        Address address = selectAddressQuery(addressID);
        return address;
    }

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

        if (rowInserted > 0) {
            System.out.println("A new address was inserted succesfully!");
        }
        return address;

    }

    private void updateAddressQuery(int addressID, Address address) throws SQLException {
        String sql = "UPDATE address SET address=?, zipcode=?, city=? ";
        PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ResultSet keyResultSet = statement.getGeneratedKeys();
        while (keyResultSet.next()) {
            if(keyResultSet.equals(addressID)) {
                statement.setString(1, address.getAddress());
                statement.setString(2, address.getZipCode());
                statement.setString(3, address.getCity());

                int rowsUpdated = statement.executeUpdate();
                if(rowsUpdated > 0) {
                    System.out.println("An existing address was updated");
                }
            }
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

//    private void deleteFlagAddressQuery() {
//    }

}
