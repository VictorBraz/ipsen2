package DAO;

import Model.Address;
import Model.Company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 * Created by Victor on 13-10-2016.
 */
public class CompanyDAO extends DAO {

    ArrayList<Company> companies;
    PreparedStatement stmt;


    public CompanyDAO() throws Exception{
        super();
    }

    public Company selectCompany(int id) {
        Company company = new Company();
        Address address = new Address();

        String sql = "SELECT * FROM company WHERE id = ?";
        String sqlAddress = "SELECT * FROM address WHERE addressid = ?";
        try {
            stmt = conn.prepareStatement(sql);
            PreparedStatement stmt2 = conn.prepareStatement(sqlAddress);
            stmt.setInt(1, id);

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                company.setId(resultSet.getInt(1));
                //company.setCompanyAddress(new AddressDAO().selectAddress(resultSet.getInt("companyaddressid")));
                company.setCompanyName(resultSet.getString(3));
                company.setContactPerson(resultSet.getString(4));
                company.setPhoneNumber(resultSet.getString(5));
                company.setEmailAddress((resultSet.getString(6)));
                company.setTag(resultSet.getString(7));

                stmt2.setInt(1, resultSet.getInt(2));
                ResultSet rs = stmt2.executeQuery();
                while (rs.next()){
                    address.setAddressID(rs.getInt(1));
                    address.setAddress(rs.getString(2));
                    address.setZipCode(rs.getString(3));
                    address.setCity(rs.getString(4));
                }
                company.setCompanyAddress(address);

            }
            stmt.close();
            stmt2.close();
        }catch (Exception e){
            e.printStackTrace();
        }


        return company;
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
            updateCompanyQuery(company.getId(), company);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @author Victor
     * @param id
     */
    public void deleteCompany(int id){
        System.out.println(id);
        String sql = "DELETE FROM company WHERE id=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @author Victor
     * @return
     * @throws Exception
     */
    public ArrayList<Company> getCompanies() {

        String sql = "SELECT * FROM company";
        companies = new ArrayList<>();

        try {
            AddressDAO dao = new AddressDAO();
            Address address;
            stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Company company = new Company();
                company.setId(resultSet.getInt(1));
                address = dao.selectAddress(resultSet.getInt(2));
                //company.setCompanyAddress(new AddressDAO().selectAddress(resultSet.getInt("companyaddressid")));
                company.setCompanyName(resultSet.getString(3));
                company.setContactPerson(resultSet.getString(4));
                company.setPhoneNumber(resultSet.getString(5));
                company.setEmailAddress((resultSet.getString(6)));
                company.setTag(resultSet.getString(7));

                company.setCompanyAddress(address);

                companies.add(company);
            }
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }


        return companies;
    }

    /**
     * @author Victor
     * @param company
     * @throws Exception
     */

    private Company addCompanyQuery(Company company)throws Exception{
        // Hier heb ik de companyid bij insert into en nextval('id_seq_company') bij values verwijderd om document werkend te krijgen.
        String sql = "INSERT INTO company(companyaddressid, companyname, contactperson, phonenumber, email, tag)"+
                "VALUES (?,?,?,?,?,?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, company.getCompanyAddress().getAddressID());
        stmt.setString(2, company.getCompanyName());
        stmt.setString(3, company.getContactPerson());
        stmt.setString(4, company.getPhoneNumber());
        stmt.setString(5, company.getEmailAddress());
        stmt.setString(6, company.getTag());

        int rowsInserted = stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();

        if (rs.next()) {
            int id = rs.getInt(1);
            company.setId(id);
        }

        if (rowsInserted > 0){
            System.out.print("New company inserted succesfully!");
        }

        stmt.close();

        return company;

    }

    /**
     * @author Victor
     * @param companyID
     * @param company
     * @throws Exception
     */
    private void updateCompanyQuery(int companyID, Company company)throws Exception{
        String sql = "UPDATE company SET companyaddressid=?, companyname=?, contactperson=?, phonenumber=?, email=?, tag=?" +
                "WHERE id =?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, company.getCompanyAddress().getAddressID());
        stmt.setString(2, company.getCompanyName());
        stmt.setString(3, company.getContactPerson());
        stmt.setString(4, company.getPhoneNumber());
        stmt.setString(5, company.getEmailAddress());
        stmt.setString(6, company.getTag());
        stmt.setInt(7, company.getId());

        int rowUpdated = stmt.executeUpdate();
        if(rowUpdated > 0){
            System.out.print("Company updated successfully!");
        }
        stmt.close();
        }


}
