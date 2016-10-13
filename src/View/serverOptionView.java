package View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Scanner;

/**
 * Created by Negin Nafissi on 13-10-2016.
 */
public class serverOptionView extends Application{

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Scanner input = new Scanner(System.in);

        Pane pane = new Pane();
        HBox hBox = new HBox();
        VBox vBox1 = new VBox();
        VBox vBox2 = new VBox();
        Label [] labels = new Label[5];

        labels[0] = new Label("Host");
        labels[1] = new Label("Port");
        labels[2] = new Label("Database name");
        labels[3] = new Label("Username");
        labels[4] = new Label("Password");

        TextField [] textfields = new TextField[5];

        textfields[0] = new TextField();
        textfields[0].setText(input.nextLine());
        textfields[1] = new TextField();
        textfields[1].setText(input.nextLine());
        textfields[2] = new TextField();
        textfields[2].setText(input.nextLine());
        textfields[3] = new TextField();
        textfields[3].setText(input.nextLine());
        textfields[4] = new TextField();
        textfields[4].setText(input.nextLine());

        hBox.getChildren().addAll(vBox1, vBox2);

        vBox1.getChildren().addAll(labels[0], labels[1], labels[2], labels[3], labels[4]);
        vBox2.getChildren().addAll(textfields[0], textfields[1], textfields[2], textfields[3], textfields[4]);

        pane.getChildren().addAll(hBox);

        Scene scene = new Scene(pane, 100, 100);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
