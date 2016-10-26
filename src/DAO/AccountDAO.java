package DAO;

import Model.Account;

import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 * Created by Victor on 26-10-2016.
 */
public class AccountDAO extends DAO{

    ArrayList<Account> accounts;
    PreparedStatement stmt;

    public AccountDAO() throws Exception{
        super();
    }

    public void createAccount(Account account) {


    }

    public void deleteAccount(Account account) {
    }

    public void editAccount(Account account) {
    }

    public Account selectAccount(Account account) {

        return account;
    }

    public ArrayList<Account> getAllAccounts() {

    }

    public void createAccountQuery(Account account) throws Exception{
        String sql = "INSERT INTO account(accountName, password, privilege, accountidclient, accountidstudent, accountidcompany" +
                "VALUES (?,?,?,?,?,?)";
        stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setString(1, account.getUserName());
        stmt.setString(2, account.getPassword());
        stmt.setString(3, account.getRightName());

    }
}
