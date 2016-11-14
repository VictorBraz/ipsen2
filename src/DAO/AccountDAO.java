package DAO;

import model.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Victor on 26-10-2016.
 */
public class AccountDAO extends DAO{

    ArrayList<Account> accounts = new ArrayList<>();
    PreparedStatement stmt = null;

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

    public void deleteAccount(int accountid) {
        try {
            String sql = "DELETE FROM account WHERE id =?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, accountid);
            int row = stmt.executeUpdate();
            if (row > 0){
                System.out.println("Account deleted!");
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

    public ArrayList<Account> getAllAccounts() {

        String sql = "SELECT * FROM account";

        try {
            stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                Account account = new Account();
                account.setId(resultSet.getInt(1));
                System.out.println("id opgehaald" + account.getId());
                account.setUserName(resultSet.getString(2));
                account.setPassword(resultSet.getString(3));
                account.setRightName(resultSet.getInt(4));
                account.setUserID(resultSet.getInt(5));

                accounts.add(account);

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        for(Account acc : accounts){
            System.out.println("wel id?: " + acc.getId());
        }
        return accounts;

    }

    public void createAccountQuery(Account account) throws Exception{
        String sql = "INSERT INTO account(accountName, password, privilege, userid)" +
                "VALUES (?,?,?,?)";

        stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setString(1, account.getUserName());
        stmt.setString(2, account.getPassword());
        stmt.setInt(3, account.getRightName());
        stmt.setInt(4, account.getId());

        int rowInserted = stmt.executeUpdate();
        if (rowInserted > 0){
            System.out.println("Account created!");
        }

    }
}
