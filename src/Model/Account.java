package Model;

/**
 * Created by Victor on 26-10-2016.
 */
public class Account extends TableViewItem {
    private String userName;
    private String password;
    private int privilege;
    private int id;
    private int userId;

    public Account(){
        userName = "";
        password = "";
        privilege = 0;
        id = 0;
        userId = 0;
    }

    public Account(String userName, String password, int privilege, int id){
        this.userName = userName;
        this.password = password;
        this.privilege = privilege;
        this.id = id;
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

    public  int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }
}
