package Model;

import java.util.ArrayList;

/**
 * Created by Victor on 13-10-2016.
 */
public class Company {

    private int companyID;
    private String companyName;
    private int companyAddressid;
    private String contactPerson;
    private String phoneNumber;
    private String emailAddress;

    /**
     * @author Victor
     * @param companyID
     * @param companyName
     * @param contactPerson
     * @param phoneNumber
     * @param emailAddress
     */
    public Company(int companyID, String companyName, int companyAddressid, String contactPerson, String phoneNumber, String emailAddress){

        this.companyID = companyID;
        this.companyName = companyName;
        this.companyAddressid = companyAddressid;
        this.contactPerson = contactPerson;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;

    }

    /**
     * @author Victor
     * @param companyName
     * @param contactPerson
     * @param phoneNumber
     * @param emailAddress
     */
    public Company( String companyName, int companyAddressid, String contactPerson, String phoneNumber, String emailAddress){

        this.companyName = companyName;
        this.companyAddressid = companyAddressid;
        this.contactPerson = contactPerson;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public Company (){
        this.companyID = 0;
        this.companyName = new String();
        this.companyAddressid = 0;
        this.contactPerson = new String();
        this.emailAddress = new String();
        this.phoneNumber = new String();
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
    public int getCompanyAddressid() {
        return companyAddressid;
    }

    /**
     * @author Victor
     * @param companyAddressid
     */
    public void setCompanyAddressid(int companyAddressid) {
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
}
