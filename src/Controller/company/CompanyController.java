
package Controller.company;

import Controller.handlers.TableViewListener;
import DAO.AddressDAO;
import DAO.CompanyDAO;
import Model.Company;
import Model.TableViewItem;
import contentloader.ContentLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Victor on 13-10-2016.
 */
public class CompanyController extends ContentLoader implements Initializable, TableViewListener {

    @FXML private TableView<TableViewItem> tableView;
    @FXML private TableColumn checkBoxColumn;
    @FXML private TableColumn companyNameColumn;
    @FXML private TableColumn addressColumn;
    @FXML private TableColumn zipCodeColumn;
    @FXML private TableColumn cityColumn;
    @FXML private TableColumn contactPersonColumn;
    @FXML private TableColumn phoneNumberColum;
    @FXML private TableColumn emailColumn;
    @FXML private TableColumn tagColumn;

    public int selectedCompanyID;
    private ObservableList<TableViewItem> companyData;
    private ArrayList<Integer> selectedRows;
    private CheckBox selectAllCheckBox;

    private CompanyDAO dao;
    private AddressDAO addressDAO;
    private ResourceBundle resources;


    @FXML
    void handleAddButton(MouseEvent event) {
        addContent(new AddCompanyController(), resources.getString("EDIT_COMPANY_DIALOG"));

    }

    @FXML
    void handleDeleteButton(MouseEvent event) {

    }

    @FXML
    void handleZoominButton(MouseEvent event) {

    }



    //private final MainMenuController mainController;


    public CompanyController() {
        //this.mainController = mainController;

        try{

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
            companies.addAll(dao.getCompanies());
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
                dao.deleteCompany(company);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void cmdUpdateCompany(Company company){
        try{
            dao.updateCompany(company);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void setSelectedRows(ArrayList selectedRows) {

    }

    @Override
    public void setSelectedItem(int selectedItemId) {

    }

    @Override
    public void openEditMenu() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        setMainFrameTitle(resources.getString("COMPANY_TITLE"));
        try {
            this.dao = new CompanyDAO();
            this.addressDAO = new AddressDAO();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
