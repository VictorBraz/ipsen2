package controller.account;

import DAO.AccountDAO;
import model.Account;

import java.util.ArrayList;

/**
 * Created by Victor on 26-10-2016.
 */
public class AccountController {

    private AccountDAO accountDAO;
//    private AccountView accountView;
//    private AccountCreatorView accountCreatorView;
//    private LoginView.fxml loginView;

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
            accountDAO.deleteAccount(account);
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
