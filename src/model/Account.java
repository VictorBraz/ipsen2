package model;

/**
 * Created by Victor on 26-10-2016.
 */
public class Account extends TableViewItem {
    private String userName;
    private String password;
    private int privilege;
    private int id;
    private int userId;

    /**
     * Instantiates a new Account.
     */
    public Account(){
        userName = "";
        password = "";
        privilege = 0;
        id = 0;
        userId = 0;
    }

    /**
     * Instantiates a new Account.
     *
     * @param userName  the user name
     * @param password  the password
     * @param privilege the privilege
     * @param id        the id
     */
    public Account(String userName, String password, int privilege, int id){
        this.userName = userName;
        this.password = password;
        this.privilege = privilege;
        this.id = id;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets right name.
     *
     * @return the right name
     */
    public int getRightName() {
        return privilege;
    }

    /**
     * Sets right name.
     *
     * @param rightName the right name
     */
    public void setRightName(int rightName) {
        this.privilege = rightName;
    }

    /**
     * Set user id.
     *
     * @param userId the user id
     */
    public void setUserID(int userId){
        this.userId = userId;
    }

    public  int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }
}
