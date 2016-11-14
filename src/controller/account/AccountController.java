package controller.account;

import controller.handlers.TableViewListener;
import DAO.AccountDAO;
import DAO.ClientDAO;
import DAO.CompanyDAO;
import DAO.StudentDAO;
import model.Account;
import model.Client;
import model.Company;
import model.Student;
import com.jfoenix.controls.*;
import contentloader.ContentLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Victor on 26-10-2016.
 */
public class AccountController extends ContentLoader implements Initializable, TableViewListener {

    @FXML private JFXTextField usernameTextField;
    @FXML private JFXPasswordField passwordTextField;
    @FXML private JFXButton cancelButton;
    @FXML private JFXButton submitButton;
    @FXML private JFXComboBox medewerkerComboBox;
    @FXML private JFXComboBox clientComboBox;
    @FXML private JFXComboBox bedrijfComboBox;

    private ArrayList<Integer> selectedRows;
    /**
     * The Selected id.
     */
    public int selectedID;

    private ArrayList<Company> companyData;
    private ArrayList<Student> studentData;
    private ArrayList<Client> clientData;

    private Account account;
    private ResourceBundle resources;
    private AccountDAO accountDAO;
    private CompanyDAO companyDAO;
    private StudentDAO studentDAO;
    private ClientDAO clientDAO;


    /**
     * Instantiates a new Account controller.
     */
    public AccountController(){
        try{
            accountDAO = new AccountDAO();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Handle add account.
     *
     * @param event the event
     * @throws Exception the exception
     */
    @FXML
    public void handleAddAccount(MouseEvent event) throws Exception{

        if(!usernameTextField.getText().isEmpty() & !passwordTextField.getText().isEmpty()){

        account = new Account();
        account.setUserName(usernameTextField.getText());
        account.setPassword(passwordTextField.getText());
        account.setUserID(selectedID);
        account.setRightName(1);

        try{
            accountDAO.createAccount(account);
        }catch (Exception e){
            e.printStackTrace();
        }
        addContent(resources.getString("SETTINGS"));
        } else {
            addContent(resources.getString("SETTINGS"));
        }
    }

    /**
     * Handle cancel button.
     *
     * @param event the event
     * @throws Exception the exception
     */
    @FXML
    public void handleCancelButton(MouseEvent event) throws Exception{
        addContent(resources.getString("SETTINGS"));
    }

    /**
     * Cmd delete account.
     *
     * @param account the account
     */
    @FXML
    public void cmdDeleteAccount(Account account){
        try{
            accountDAO.deleteAccount(account.getId());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Cmd edit account.
     *
     * @param account the account
     */
    public void cmdEditAccount(Account account){
        try{
            accountDAO.editAccount(account);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Cmd select all accounts array list.
     *
     * @return the array list
     */
    public ArrayList<Account> cmdSelectAllAccounts(){
        ArrayList<Account> accounts = new ArrayList<>();
        try{
            accounts = accountDAO.getAllAccounts();
        }catch (Exception e){
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public void setSelectedRows(ArrayList selectedRows) {
        this.selectedRows = selectedRows;
    }

    @Override
    public void setSelectedItem(int selectedItemId) {
        this.selectedID = selectedItemId;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.resources = resources;


    }

}
