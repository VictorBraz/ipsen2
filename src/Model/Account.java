package Model;

/**
 * Created by Victor on 26-10-2016.
 */
public class Account {
    private String userName;
    private String password;
    private int privilege;
    private int userId;

    public Account(){
        userName = "";
        password = "";
        privilege = 0;
    }

    public Account(String userName, String password, int privilege, int userId){
        this.userName = userName;
        this.password = password;
        this.privilege = privilege;
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRightName() {
        return privilege;
    }

    public void setRightName(int rightName) {
        this.privilege = rightName;
    }

    public void setUserID(int userId){
        this.userId = userId;
    }

    public  int getUserId(){
        return this.userId;
    }
}
