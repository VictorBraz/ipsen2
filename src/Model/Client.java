package model;

/**
 * Created by Bernd on 12-10-2016.
 */
public class Client extends TableViewItem {
    private int id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String emailAddress;
    private String phoneNumber;
    private String study;
    private Address address;
    private Note note;
    private int clientAddressid;
    private String tag;

    /**
     * Instantiates a new Client.
     */
    public Client() {
    }

    /**
     * Instantiates a new Client.
     *
     * @param firstName    the first name
     * @param lastName     the last name
     * @param birthDate    the birth date
     * @param study        the study
     * @param emailAddress the email address
     * @param phoneNumber  the phone number
     * @param tag          the tag
     */
    public Client(String firstName, String lastName, String birthDate, String study,
                  String emailAddress, String phoneNumber, String tag) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.study = study;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.tag = tag;
    }

    /**
     * Instantiates a new Client.
     *
     * @param id the client id
     */
    public Client(int id) {
        this.id = id;
        this.firstName = null;
        this.lastName = null;
        this.birthDate = null;
        this.emailAddress = null;
        this.phoneNumber = null;
        this.address =  null;
        this.note = null;
        this.study = null;
        this.tag = null;
    }

    /**
     * Instantiates a new Client.
     *
     * @param id           the client id
     * @param firstName    the first name
     * @param lastName     the last name
     * @param birthDate    the birth date
     * @param emailAddress the email address
     * @param phoneNumber  the telephone number
     * @param addressID    the address id
     * @param noteID       the note id
     * @param study        the study
     * @param tag          the tag
     */
    public Client(int id, String firstName, String lastName, String birthDate,
                  String emailAddress, String phoneNumber, int addressID, int noteID, String study, String tag ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.address =  new Address(addressID);
        this.note = new Note(noteID);
        this.study = study;
        this.tag = tag;
    }

    /**
     * Gets client id.
     *
     * @return the client id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets birth date.
     *
     * @return the birth date
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Gets email address.
     *
     * @return the email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Gets telephone number.
     *
     * @return the telephone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Gets note.
     *
     * @return the note
     */
    public Note getNote() {
        return note;
    }

    /**
     * Gets study.
     *
     * @return the study
     */
    public String getStudy() {
        return study;
    }

    /**
     * Sets client id.
     *
     * @param id the client id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets birth date.
     *
     * @param birthDate the birth date
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Sets email address.
     *
     * @param emailAddress the email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Sets telephone number.
     *
     * @param phoneNumber the telephone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Sets note.
     *
     * @param note the note
     */
    public void setNote(Note note) {
        this.note = note;
    }

    /**
     * Sets study.
     *
     * @param study the study
     */
    public void setStudy(String study) {
        this.study = study;
    }


    /**
     * Gets client addressid.
     *
     * @return the client addressid
     */
    public int getClientAddressid() {
        return clientAddressid;
    }

    /**
     * Sets client addressid.
     *
     * @param clientAddressid the client addressid
     */
    public void setClientAddressid(int clientAddressid) {
        this.clientAddressid = clientAddressid;
    }

    /**
     * Gets tag.
     *
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * Sets tag.
     *
     * @param tag the tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }
}