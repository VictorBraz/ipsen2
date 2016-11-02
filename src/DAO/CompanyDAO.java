package DAO;

import Model.Company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


/**
 * Created by Victor on 13-10-2016.
 */
public class CompanyDAO extends DAO {

    ArrayList<Company> companies;

    public CompanyDAO() throws Exception{
        super();
    }

    public void addCompany(Company company) throws Exception{
        try{
            addCompanyQuery(company);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * @author Victor
     * @param company
     * @throws Exception
     */
    public void updateCompany(Company company) throws Exception{

        try{
            updateCompanyQuery(company.getCompanyID(), company);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @author Victor
     * @param company
     */
    public void deleteCompany(Company company){
        try {
            deleteCompanyQuery(company);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * @author Victor
     * @return
     * @throws Exception
     */
    public ArrayList<Company> getCompanies() throws Exception{

        return getCompaniesQuery();
    }

    /**
     * @author Victor
     * @param company
     * @throws Exception
     */
    private void addCompanyQuery(Company company)throws Exception{
        String sql = "INSERT INTO company(companyid, companyaddressid, companyname, contactperson, phonenumber, email, tag)"+
                "VALUES (null,?,?,?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, company.getCompanyAddressid());
        stmt.setString(2, company.getCompanyName());
        stmt.setString(3, company.getContactPerson());
        stmt.setString(4, company.getPhoneNumber());
        stmt.setString(5, company.getEmailAddress());
        stmt.setString(6, company.getTag());

        int rowsInserted = stmt.executeUpdate();
        if (rowsInserted > 0){
            System.out.print("New company inserted succesfully!");
        }
    }

    /**
     * @author Victor
     * @param companyID
     * @param company
     * @throws Exception
     */
    private void updateCompanyQuery(int companyID, Company company)throws Exception{
        String sql = "UPDATE company SET companyaddressid=?, companyname=?, contactperson=?, phonenumber=?, email=?, tag=?" +
                "WHERE companyid =?";
        PreparedStatement stmt = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
        ResultSet resultSet = stmt.getGeneratedKeys();
        while(resultSet.next()){
            if(resultSet.equals(companyID)){
                stmt.setInt(1, company.getCompanyAddressid());
                stmt.setString(2, company.getCompanyName());
                stmt.setString(3, company.getContactPerson());
                stmt.setString(4, company.getPhoneNumber());
                stmt.setString(5, company.getEmailAddress());
                stmt.setString(6, company.getTag());

                int rowUpdated = stmt.executeUpdate();
                if(rowUpdated > 0){
                    System.out.print("Company updated successfully!");
                }
            }else
                System.out.println("No matching companyID found!");
        }

    }

    /**
     * @author Victor
     * @param company
     * @throws Exception
     */
    private void deleteCompanyQuery(Company company)throws Exception{
        String sql = "DELETE FROM company WHERE companyid=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet resultSet = stmt.getGeneratedKeys();

        while(resultSet.next()){
            if(resultSet.equals(company.getCompanyID())){
                int rowDeleted = stmt.executeUpdate();
                if (rowDeleted > 0)
                    System.out.println("Company deleted!");
            }
        }
    }
    private ArrayList<Company> getCompaniesQuery()throws  Exception{
        String sql = "SELECT * FROM company";
        PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ResultSet resultSet = stmt.getGeneratedKeys();

        while(resultSet.next()){
            Company company = new Company();
            company.setCompanyID(resultSet.getInt(1));
            company.setCompanyAddressid(resultSet.getInt(2));
            company.setCompanyName(resultSet.getString(3));
            company.setContactPerson(resultSet.getString(4));
            company.setPhoneNumber(resultSet.getString(5));
            company.setEmailAddress(resultSet.getString(6));
            company.setTag(resultSet.getString(7));

            companies.add(company);
        }
        return companies;
    }

}
