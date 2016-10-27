package DAO;

import Model.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        try{
            createAccountQuery(account);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteAccount(Account account) {
        try {
            String sql = "DELETE FROM account WHERE accountname =?";
            stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.getGeneratedKeys();

            while(resultSet.next()){
                if(resultSet.equals(account.getUserName())){
                    int rowDeleted = stmt.executeUpdate();
                    if (rowDeleted > 0)
                        System.out.println("Account deleted!");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void editAccount(Account account) {
        try{
            String sql = "UPDATE account SET accountname=?, password=?, privilege=?" +
                    "WHERE userid=?";
            stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = stmt.getGeneratedKeys();
            while(resultSet.next()){
                if(resultSet.equals(account.getUserId())){
                    stmt.setString(1, account.getUserName());
                    stmt.setString(2, account.getPassword());
                    stmt.setString(3, account.getRightName());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Account> getAllAccounts() {

        try {
            String sql = "SELECT * FROM account";
            stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = stmt.getGeneratedKeys();

            while(resultSet.next()){
                Account account = new Account();
                account.setUserID(resultSet.getInt(1));
                account.setUserName(resultSet.getString(2));
                account.setPassword(resultSet.getString(3));
                account.setRightName(resultSet.getString(4));

                accounts.add(account);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return accounts;

    }

    public void createAccountQuery(Account account) throws Exception{
        String sql = "INSERT INTO account(accountName, password, privilege, userid)" +
                "VALUES (?,?,?,?)";

        stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setString(1, account.getUserName());
        stmt.setString(2, account.getPassword());
        stmt.setString(3, account.getRightName());
        stmt.setInt(4, account.getUserId());

        int rowInserted = stmt.executeUpdate();
        if (rowInserted > 0){
            System.out.println("Account created!");
        }

    }
}
