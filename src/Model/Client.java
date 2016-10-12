package Model;

import DAO.ClientDAO;

import java.util.ArrayList;

/**
 * Created by Bernd on 12-10-2016.
 */
public class Client extends ClientDAO {
    private int clientID;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String emailAddress;
    private String telephoneNumber;
    private String study;
    private Address address;
    private Note note;
    private ClientDAO clientDAO;

    public Client() {

    }

    public Client(int clientID) {
        this.clientID = clientID;
        this.firstName = null;
        this.lastName = null;
        this.birthDate = null;
        this.emailAddress = null;
        this.telephoneNumber = null;
        this.address =  null;
        this.note = null;
        this.study = null;
    }

    public Client(int clientID, String firstName, String lastName, String birthDate,
                  String emailAddress, String telephoneNumber, int addressID, int noteID, String study ) {
        this.clientID = clientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.emailAddress = emailAddress;
        this.telephoneNumber = telephoneNumber;
        this.address =  new Address(addressID);
        this.note = new Note(noteID);
        this.study = study;
    }

    public void addClient(ArrayList data) {
        Integer addressID = this.address.create(data);
        Integer noteID = this.note.create(data);
        data.put(addressID);
        data.put(noteID);
        this.clientDAO.create(data);
    }

    public void editClient(int clientID, ArrayList data) {
        Client client = this.clientDAO.find(id);
        int addressID = client.getAddress().getAddressID();
        data.put(addressID);
        this.address.update(addressID, data);
        this.clientDAO.update(clientID, data);
    }

    public void disableClient(int clientID) {
        Client client = clientDAO.find(clientID);
        clientDAO.Disable(clientID);
    }

    public Client find(int clientID) {
        Client client = clientDAO.find(clientID);
        client.setAddress(address.find(client.getAddress().getAddressID()));
        client.setNote(note.find(client.getNote().getNoteID()));
        return client;
    }

    public int getClientID() {
        return clientID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public Note getNote() {
        return note;
    }

    public String getStudy() {
        return study;
    }

    public ClientDAO getClientDAO() {
        return clientDAO;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public void setStudy(String study) {
        this.study = study;
    }

    public void setClientDAO(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }
}
