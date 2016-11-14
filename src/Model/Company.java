package model;

/**
 * Created by Victor on 13-10-2016.
 */
public class Company extends TableViewItem{

    private int id;
    private String companyName;
    private Address companyAddress;
    private String contactPerson;
    private String phoneNumber;
    private String emailAddress;
    private String tag;

    /**
     * @author Victor
     * @param id
     * @param companyName
     * @param contactPerson
     * @param phoneNumber
     * @param emailAddress
     */
    public Company(int id, String companyName, Address companyAddress, String contactPerson, String phoneNumber, String emailAddress, String tag){

        this.id = id;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.contactPerson = contactPerson;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.tag = tag;

    }

    /**
     * @author Victor
     * @param companyName
     * @param contactPerson
     * @param phoneNumber
     * @param emailAddress
     */
    public Company(String companyName, Address companyAddress, String contactPerson, String phoneNumber, String emailAddress, String tag){

        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.contactPerson = contactPerson;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.tag = tag;
    }

    public Company (){
        this.id = 0;
        this.companyName = new String();
        this.companyAddress = new Address();
        this.contactPerson = new String();
        this.emailAddress = new String();
        this.phoneNumber = new String();
        this.tag = new String();
    }

    /**
     * @author Victor
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * @author Victor
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @author Victor
     * @return companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @author Victor
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @author Victor
     * @return companyAddress
     */
    public Address getCompanyAddress() {
        return companyAddress;
    }

    /**
     * @author Victor
     * @param companyAddress
     */
    public void setCompanyAddress(Address companyAddress) {
        this.companyAddress = companyAddress;
    }

    /**
     * @author Victor
     * @return contactPerson
     */
    public String getContactPerson() {
        return contactPerson;
    }

    /**
     * @author Victor
     * @param contactPerson
     */
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    /**
     * @author Victor
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @author Victor
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @author Victor
     * @return emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @author Victor
     * @param emailAddres
     */
    public void setEmailAddress(String emailAddres) {
        this.emailAddress = emailAddres;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
