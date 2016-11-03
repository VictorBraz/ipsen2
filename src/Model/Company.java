package Model;

import java.util.ArrayList;

/**
 * Created by Victor on 13-10-2016.
 */
public class Company extends TableViewItem{

    private int companyID;
    private String companyName;
    private Address companyAddressid;
    private String contactPerson;
    private String phoneNumber;
    private String emailAddress;
    private String tag;

    /**
     * @author Victor
     * @param companyID
     * @param companyName
     * @param contactPerson
     * @param phoneNumber
     * @param emailAddress
     */
    public Company(int companyID, String companyName, Address companyAddressid, String contactPerson, String phoneNumber, String emailAddress, String tag){

        this.companyID = companyID;
        this.companyName = companyName;
        this.companyAddressid = companyAddressid;
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
    public Company( String companyName, Address companyAddressid, String contactPerson, String phoneNumber, String emailAddress, String tag){

        this.companyName = companyName;
        this.companyAddressid = companyAddressid;
        this.contactPerson = contactPerson;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.tag = tag;
    }

    public Company (){
        this.companyID = 0;
        this.companyName = new String();
        this.companyAddressid = new Address();
        this.contactPerson = new String();
        this.emailAddress = new String();
        this.phoneNumber = new String();
        this.tag = new String();
    }

    /**
     * @author Victor
     * @return companyID
     */
    public int getCompanyID() {
        return companyID;
    }

    /**
     * @author Victor
     * @param companyID
     */
    public void setCompanyID(int companyID) {
        this.companyID = companyID;
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
     * @return companyAddressid
     */
    public Address getCompanyAddressid() {
        return companyAddressid;
    }

    /**
     * @author Victor
     * @param companyAddressid
     */
    public void setCompanyAddressid(Address companyAddressid) {
        this.companyAddressid = companyAddressid;
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
