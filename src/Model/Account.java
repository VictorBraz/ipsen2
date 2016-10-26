package Model;

/**
 * Created by Victor on 26-10-2016.
 */
public class Account {
    private String userName;
    private String password;
    private String rightName;

    public Account(){
        userName = "";
        password = "";
        rightName = "";
    }

    public Account(String userName, String password, String rightName){
        this.userName = userName;
        this.password = password;
        this.rightName = rightName;
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

    public String getRightName() {
        return rightName;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName;
    }
}
