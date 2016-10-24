package View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Created by Gebruiker on 24-10-2016.
 */
public class Iets extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane pane = new Pane();
        HBox hBox = new HBox();

        pane.getChildren().addAll(hBox);
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
