package Controller;

import DAO.CompanyDAO;
import Model.Company;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Created by Victor on 13-10-2016.
 */
public class CompanyController {

    //private final MainController mainController;
    private CompanyDAO dao;

    public CompanyController() {
        //this.mainController = mainController;

        try{
            this.dao = new CompanyDAO();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @author
     * @return
     */
    public ObservableList<Company> cmdGetCompanies(){
        ArrayList<Company> companies = new ArrayList<>();
        try{
            companies.addAll(dao.getAllCompanies());
        }catch (Exception e){
            e.printStackTrace();
        }
        return FXCollections.observableArrayList(companies);
    }

    public void cmdAddCompany(Company company){
        try{
            dao.addCompany(company);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void cmdDeleteCompany(ObservableList<Company> companies){
        try{
            for(Company company: companies){
                dao.
            }
        }
    }

}
