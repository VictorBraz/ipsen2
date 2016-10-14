package DAO;

import Model.Company;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Victor on 13-10-2016.
 */
public class CompanyDAO extends DAO {

    public CompanyDAO() throws Exception{
        super();
    }

    /**
     * @author Victor
     * @param id
     * @return
     * @throws Exception
     */
    public Company getCompany(int id) throws Exception{

        Company company = new Company();
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM company WHERE companyID = " + id);
            while (result.next()){
                company.setCompanyID(result.getInt("companyid"));
                company.setCompanyName(result.getString("companyname"));
                company.setCompanyAddressid(result.getInt("companyaddressid"));
                company.setContactPerson(result.getString("contactperson"));
                company.setPhoneNumber(result.getString("phonenumber"));
                company.setEmailAddress(result.getString("email"));

            }
        return company;

    }

    /**
     * @author Victor
     * @return
     * @throws Exception
     */
    public ArrayList<Company> getAllCompanies() throws Exception {

        ArrayList<Company> companies = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM company");
        while (result.next()) {
            companies.add(new Company(
                    result.getInt("companyid"),
                    result.getString("companyname"),
                    result.getInt("companyaddressid"),
                    result.getString("contactperson"),
                    result.getString("phonenumber"),
                    result.getString("email")));
        }
        return companies;
    }

    public void addCompany(Company company) throws Exception{

        Statement stmt = conn.createStatement();
        stmt.executeQuery("Insert into company(companyaddressid, companyname, contactperson, phonenumber, email) +" +
                "values(" +
                "'" + company.getCompanyAddressid() + "','" +
                company.getCompanyName() + "','" +
                company.getContactPerson() + "','" +
                company.getPhoneNumber() + "','" +
                company.getEmailAddress()+"')");

    }

    public void updateCompany(Company company) throws Exception{

        Statement stmt = conn.createStatement();
        stmt.executeUpdate("UPDATE company SET companyaddressid='" + company.getCompanyAddressid() +"',"+
        "companyname='" + company.getCompanyName() + "'," +
        "contactperson='" + company.getContactPerson()+ "'," +
        "phonenumber='" + company.getPhoneNumber()+ "'," +
        "email='" + company.getEmailAddress()+ "'," +
        "WHERE companyid=" + company.getCompanyID());
    }

    public void deleteCompany(Company company){
        try {
            Statement stmt = conn.createStatement();
            stmt.executeQuery("")

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
