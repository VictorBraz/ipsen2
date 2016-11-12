package Controller;

import Model.Account;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import contentloader.ContentLoader;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    @FXML private StackPane stackpane;
    @FXML private Pane forgotPasswordAlert;
    @FXML private JFXButton submitButton;
    @FXML private Pane loginFailAlert;


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
            } else {
                loginFailAlert.setVisible(true);
                FadeTransition animation = new FadeTransition(Duration.millis(3000));
                animation.setNode(loginFailAlert);
                animation.setFromValue(0.0);
                animation.setFromValue(1.0);
                animation.play();
            }


        }



    }
    @FXML
    void forgotPasswordLink(MouseEvent event) {
        forgotPasswordAlert.setVisible(true);
        FadeTransition animation = new FadeTransition(Duration.millis(3000));
        animation.setNode(forgotPasswordAlert);

        animation.setFromValue(0.0);
        animation.setFromValue(1.0);
        animation.play();


    }

    @FXML
    void handleComfirmButton(MouseEvent event) {
        forgotPasswordAlert.setVisible(false);
        loginFailAlert.setVisible(false);

    }

    @FXML
    void cmdCloseApplication(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        setHamburgerMenuVisible(false);
        forgotPasswordAlert.setVisible(false);
        loginFailAlert.setVisible(false);


    }
}
