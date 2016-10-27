package Model;

import DAO.AddressDAO;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Bernd on 13-10-2016.
 */
public class Address {
    private int addressID;
    private String address;
    private String zipCode;
    private String city;

    /**
     * Instantiates a new Address.
     */
    public Address() {

    }

    /**
     * Instantiates a new Address.
     *
     * @param addressID the address id
     */
    public Address(int addressID) {
        this.addressID = addressID;
        this.address = null;
        this.zipCode = null;
        this.city = null;
    }

    /**
     * Instantiates a new Address.
     *
     * @param addressID the address id
     * @param address   the address
     * @param zipCode   the zip code
     * @param city      the city
     */
    public Address(int addressID, String address, String zipCode, String city) {
        this.addressID = addressID;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
    }

    /**
     * Gets address id.
     *
     * @return the address id
     */
    public int getAddressID() {
        return addressID;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets zip code.
     *
     * @return the zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets address id.
     *
     * @param addressID the address id
     */
    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets zip code.
     *
     * @param zipCode the zip code
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }
}