package Controller.company;

import Controller.handlers.TableViewListener;
import DAO.AddressDAO;
import DAO.CompanyDAO;
import Model.TableViewItem;
import contentloader.ContentLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Victor on 3-11-2016.
 */
public class CompanyEditController extends ContentLoader implements Initializable, TableViewListener {

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

    private ResourceBundle resources;
    private AddressDAO addressDAO;
    private CompanyDAO companyDAO;



    @FXML
    void handleAddFileButton(MouseEvent event){

    }

    @FXML
    void handleDeleteFileButton(MouseEvent event){

    }

    @FXML
    void handleCancelButton(MouseEvent event){
        addContent(resources.getString("COMPANIES"));
    }

    @FXML
    void handleComfirmButton(MouseEvent event){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        try {
            this.companyDAO = new CompanyDAO();
            this.addressDAO = new AddressDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void setSelectedRows(ArrayList selectedRows){

    }

    @Override
    public void setSelectedItem(int selectedItemId){

    }
}
