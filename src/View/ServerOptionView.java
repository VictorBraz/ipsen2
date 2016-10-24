package View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;



/**
 * Created by Negin Nafissi on 13-10-2016.
 */

public class ServerOptionView extends Application{

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(primaryStage);

        HBox hBox = new HBox();
        VBox vBox1 = new VBox();
        VBox vBox2 = new VBox();

        hBox.setMargin(vBox1, new Insets( 0, 20, 0, 40));
        hBox.setMargin(vBox2, new Insets( 0, 20, 0, 0));


        Label host = new Label("Host: ");
        Label port = new Label("Port: ");
        Label serverName = new Label("Server name: ");
        Label username = new Label("Username: ");
        Label password = new Label("Password: ");

        TextField hostField = new TextField();
        TextField portField = new TextField();
        TextField serverNameField = new TextField();
        TextField usernameField = new TextField();
        TextField passwordField = new TextField();

        vBox1.getChildren().addAll(host, port, serverName, username, password);
        vBox1.setMargin(host, new Insets(16, 0, 0, 0));
        vBox1.setMargin(port, new Insets(16, 0, 0, 0));
        vBox1.setMargin(serverName, new Insets(16, 10, 0, 0));
        vBox1.setMargin(username, new Insets(16, 10, 0, 0));
        vBox1.setMargin(password, new Insets(17, 10, 0, 0));

        vBox2.getChildren().addAll(hostField, portField, serverNameField,usernameField, passwordField);
        vBox2.setMargin(hostField, new Insets(8, 0, 0, 0));
        vBox2.setMargin(portField, new Insets(8, 0, 0, 0));
        vBox2.setMargin(serverNameField, new Insets(8, 0, 0, 0));
        vBox2.setMargin(usernameField, new Insets(8, 0, 0, 0));
        vBox2.setMargin(passwordField, new Insets(8, 0, 0, 0));

        host.setStyle("-fx-font-weight: bold;");
        port.setStyle("-fx-font-weight: bold;");
        serverName.setStyle("-fx-font-weight: bold;");
        username.setStyle("-fx-font-weight: bold;");
        password.setStyle("-fx-font-weight: bold;");

        hBox.getChildren().addAll(vBox1, vBox2);

        hBox.setStyle("-fx-background-color: linear-gradient(from 50% 50% to 5% 5%,  #3498db , #e3f2fd);");
        Scene scene = new Scene(hBox, 400, 200);
        stage.setScene(scene);
        stage.show();
    }
}
