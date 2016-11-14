package Controller;

import Controller.handlers.TableViewListener;
import DAO.AccountDAO;
import DAO.ClientDAO;
import DAO.CompanyDAO;
import DAO.StudentDAO;
import Model.Account;
import Model.Client;
import Model.Company;
import Model.Student;
import com.jfoenix.controls.*;
import contentloader.ContentLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Victor on 26-10-2016.
 */
public class AccountController extends ContentLoader implements Initializable, TableViewListener {

    @FXML private JFXTextField usernameTextField;
    @FXML private JFXPasswordField passwordTextField;
    @FXML private JFXButton cancelButton;
    @FXML private JFXButton submitButton;
    @FXML private JFXComboBox medewerkerComboBox;
    @FXML private JFXComboBox clientComboBox;
    @FXML private JFXComboBox bedrijfComboBox;

    private ArrayList<Integer> selectedRows;
    public int selectedID;

    private ArrayList<Company> companyData;
    private ArrayList<Student> studentData;
    private ArrayList<Client> clientData;

    private Account account;
    private ResourceBundle resources;
    private AccountDAO accountDAO;
    private CompanyDAO companyDAO;
    private StudentDAO studentDAO;
    private ClientDAO clientDAO;


    public AccountController(){
        try{
            accountDAO = new AccountDAO();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAddAccount(MouseEvent event) throws Exception{

        account = new Account();
        account.setUserName(usernameTextField.getText());
        account.setPassword(passwordTextField.getText());
        account.setUserID(selectedID);
        account.setRightName(1);

        try{
            accountDAO.createAccount(account);
        }catch (Exception e){
            e.printStackTrace();
        }
        addContent(resources.getString("SETTINGS"));
    }

    @FXML
    public void handleCancelButton(MouseEvent event) throws Exception{
        addContent(resources.getString("SETTINGS"));
    }

    @FXML
    public void cmdDeleteAccount(Account account){
        try{
            accountDAO.deleteAccount(account.getId());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void cmdEditAccount(Account account){
        try{
            accountDAO.editAccount(account);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Account> cmdSelectAllAccounts(){
        ArrayList<Account> accounts = new ArrayList<>();
        try{
            accounts = accountDAO.getAllAccounts();
        }catch (Exception e){
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public void setSelectedRows(ArrayList selectedRows) {
        this.selectedRows = selectedRows;
        System.out.println("rows selected:" + selectedRows);
    }

    @Override
    public void setSelectedItem(int selectedItemId) {
        this.selectedID = selectedItemId;
        System.out.println(selectedItemId);
    }
//
//    public void fillComboBoxes(){
//        for(Client client : clientData){
//            clientComboBox.getItems().add(client);
//        }
//        for(Student student : studentData){
//            medewerkerComboBox.getItems().add(student);
//        }
//        for(Company company : companyData){
//            bedrijfComboBox.getItems().add(company);
//        }
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.resources = resources;
//        try{
//            companyDAO = new CompanyDAO();
//            clientDAO = new ClientDAO();
//            studentDAO = new StudentDAO();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        companyData = companyDAO.getCompanies();
//        clientData = clientDAO.selectAllClients();
//        studentData = studentDAO.selectAllStudents();

        //fillComboBoxes();

    }

}
