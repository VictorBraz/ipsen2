package Controller;

import Model.Account;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import contentloader.ContentLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * Created by Bernd on 28-10-2016.
 */
public class LoginController extends ContentLoader implements Initializable {

    @FXML private JFXTextField userNameTextField;
    @FXML private JFXPasswordField passwordTextField;
    @FXML private JFXButton loginButton;
    @FXML private JFXButton ForgotPasswordButton;
    @FXML private JFXButton closeButton;

    private ResourceBundle resources;

    private ArrayList<Account> accounts = new ArrayList<>();
    private AccountController controller;

    /**
     * @author Bernd, Victor
     * @param event
     * @throws Exception
     */
    @FXML
    public void cmdLogin(ActionEvent event) throws Exception {
        String username = userNameTextField.getText();
        String password = passwordTextField.getText();
        controller = new AccountController();

        accounts = controller.cmdSelectAllAccounts();

        for(Account account : accounts) {
            if (username.equals(account.getUserName()) && password.equals(account.getPassword())) {
                addContent(resources.getString("MAINMENU"));
                System.out.println("Welcome");
            }
        }

        /*if(username.equals("test") &&password.equals("test")) {
            addContent(resources.getString("MAINMENU"));
            System.out.println("Welcome");
        } else {
            System.out.println("Wrong Password");
        }*/

    }

    @FXML
    void cmdCloseApplication(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        setMainFrameTitle(resources.getString("HOME_TITLE"));
    }
}
