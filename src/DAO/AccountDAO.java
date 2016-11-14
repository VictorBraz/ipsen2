package DAO;

import model.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Victor on 26-10-2016.
 */
public class AccountDAO extends DAO{

    /**
     * The Accounts.
     */
    ArrayList<Account> accounts = new ArrayList<>();
    /**
     * The Stmt.
     */
    PreparedStatement stmt = null;

    /**
     * Instantiates a new Account dao.
     *
     * @throws Exception the exception
     */
    public AccountDAO() throws Exception{
        super();
    }

    /**
     * Create account.
     *
     * @param account the account
     */
    public void createAccount(Account account) {
        try{
            createAccountQuery(account);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Delete account.
     *
     * @param accountid the accountid
     */
    public void deleteAccount(int accountid) {
        try {
            String sql = "DELETE FROM account WHERE id =?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, accountid);
            int row = stmt.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Edit account.
     *
     * @param account the account
     */
    public void editAccount(Account account) {
        try{
            String sql = "UPDATE account SET accountname=?, password=?, privilege=?" +
                    "WHERE userid=?";
            stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = stmt.getGeneratedKeys();
            while(resultSet.next()){
                if(resultSet.equals(account.getId())){
                    stmt.setString(1, account.getUserName());
                    stmt.setString(2, account.getPassword());
                    stmt.setInt(3, account.getRightName());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Gets all accounts.
     *
     * @return the all accounts
     */
    public ArrayList<Account> getAllAccounts() {

        String sql = "SELECT * FROM account";

        try {
            stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                Account account = new Account();
                account.setId(resultSet.getInt(1));
                account.setUserName(resultSet.getString(2));
                account.setPassword(resultSet.getString(3));
                account.setRightName(resultSet.getInt(4));
                account.setUserID(resultSet.getInt(5));

                accounts.add(account);

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return accounts;

    }

    /**
     * Create account query.
     *
     * @param account the account
     * @throws Exception the exception
     */
    public void createAccountQuery(Account account) throws Exception{
        String sql = "INSERT INTO account(accountName, password, privilege, userid)" +
                "VALUES (?,?,?,?)";

        stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setString(1, account.getUserName());
        stmt.setString(2, account.getPassword());
        stmt.setInt(3, account.getRightName());
        stmt.setInt(4, account.getId());

        int rowInserted = stmt.executeUpdate();

    }
}
