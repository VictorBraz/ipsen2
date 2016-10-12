package Model;

/**
 * Created by Bernd on 12-10-2016.
 */
public class Client {
    private int clientID;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String emailAddress;
    private String telephoneNumber;

    private Address address;
    private Note note;

    public Client() {

    }

    public Client(int clientID, String firstName, String lastName, String birthDate,
                  String emailAddress, String telephoneNumber, int addressID, int noteID ) {
        this.clientID = clientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.emailAddress = emailAddress;
        this.telephoneNumber = telephoneNumber;
        this.address =  new Address(addressID);
        this.note = new Note(noteID);
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
}
