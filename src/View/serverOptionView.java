package View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



/**
 * Created by Gebruiker on 13-10-2016.
 */
public class serverOptionView extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        HBox hBox = new HBox();
        VBox vBox1 = new VBox();
        VBox vBox2 = new VBox();

        Label host = new Label("Host: ");
        Label port = new Label("Port: ");
        Label serverName = new Label("Server name: ");
        Label username = new Label("Username");
        Label password = new Label("Password");

        TextField[] textField = new TextField[5];

        vBox1.getChildren().addAll(host, port, serverName, username, password);
        vBox2.getChildren().addAll(textField);

        hBox.getChildren().addAll(vBox1, vBox2);

        pane.getChildren().add(hBox);
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Server options");
        primaryStage.show();
    }
}
