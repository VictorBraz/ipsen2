package Controller;

import DAO.AccountDAO;
import Model.Account;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

import java.util.ArrayList;

/**
 * Created by Victor on 26-10-2016.
 */
public class AccountController {

    @FXML private JFXTextField usernameTextField;
    @FXML private JFXPasswordField passwordTextField;

    private AccountDAO accountDAO;


    public AccountController(){
        try{
            accountDAO = new AccountDAO();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void cmdCreateAccount(Account account){
        try{
            accountDAO.createAccount(account);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void cmdDeleteAccount(Account account){
        try{
            accountDAO.deleteAccount(account.getId());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void cmdEditAccount(Account account){
        try{
            accountDAO.editAccount(account);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
/**
    public Account cmdSelectAccount(Account account){
        Account acc = new Account();
        try{
            acc = accountDAO.selectAccount(account);
        }catch (Exception e){
            e.printStackTrace();
        }
        return acc;
    }
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

}
