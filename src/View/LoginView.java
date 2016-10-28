package View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.swing.text.StyledEditorKit;

/**
 * Created by Negin Nafissi on 18-10-2016.
 */
public class LoginView extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(25, 25, 25, 25));

        Label login = new Label("Login");
        login.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        pane.add(login, 0, 0, 2, 1);


        Label username = new Label("Username:");
        pane.add(username, 0, 1);
        username.setStyle("-fx-font-weight: bold;");

        TextField usernameField = new TextField();
        pane.add(usernameField, 1, 1);

        Label password = new Label("Password:");
        pane.add(password, 0, 2);

        PasswordField passwordField = new PasswordField();
        pane.add(passwordField, 1, 2);
        password.setStyle("-fx-font-weight: bold;");

        Button signIn = new Button("Sign in");
        signIn.setStyle("-fx-background-color: #FFFFFF;");
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.getChildren().add(signIn);
        pane.add(hBox, 1, 4);

        Scene scene = new Scene(pane, 350, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
