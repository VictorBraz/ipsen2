package View;


import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

import javafx.scene.paint.Color;
import javafx.stage.Stage;



/**
 * Created by Gebruiker on 12-10-2016.
 */
public class MainView extends Application {

    //model aanmaken en meegeven aan de constructor

    public static void main(String[] args) {
        launch(args);

    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane borderPane = new BorderPane();
        HBox hBox = new HBox();

        Button project = new Button("Project");
        Button student = new Button("Student");
        Button company = new Button("Student");
        Button client = new Button("Client");

        Menu options = new Menu("Options");

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(options);

        project.setStyle("-fx-background-color: #4a148c;" +"-fx-background-radius: 30; " +
                         "-fx-background-insets: 0,1,2,3,0; -fx-text-fill: #FFFFFF; -fx-font-weight: bold; " +
                         "-fx-font-size: 50px; -fx-padding: 10 20 10 20;");

        project.setMinWidth(200);
        project.setMinHeight(200);

        student.setMinWidth(200);
        student.setMinHeight(200);

        company.setMinWidth(200);
        company.setMinHeight(200);

        client.setMinWidth(200);
        client.setMinHeight(200);


        student.setStyle("-fx-background-color: #7b1fa2;" +"-fx-background-radius: 30;" +
                         " -fx-background-insets: 0,1,2,3,0; -fx-text-fill: #FFFFFF; -fx-font-weight: bold; " +
                         "-fx-font-size: 50px; -fx-padding: 10 20 10 20;");

        company.setStyle("-fx-background-color: #9c27b0;" +"-fx-background-radius: 30;" +
                         " -fx-background-insets: 0,1,2,3,0; -fx-text-fill: #FFFFFF; -fx-font-weight: bold; " +
                         "-fx-font-size: 50px; -fx-padding: 10 20 10 20;");

        client.setStyle("-fx-background-color: #ba68c8;" +"-fx-background-radius: 30;" +
                        " -fx-background-insets: 0,1,2,3,0; -fx-text-fill: #FFFFFF; -fx-font-weight: bold; " +
                        "-fx-font-size:50px; -fx-padding: 10 20 10 20;");

        hBox.setMargin(project,new Insets(0,10,0,10));
        hBox.setMargin(student,new Insets(0,10,0,10));
        hBox.setMargin(company,new Insets(0,10,0,10));
        hBox.setMargin(client,new Insets(0,10,0,10));

        hBox.getChildren().addAll(project,student,company,client);
        hBox.setAlignment(Pos.CENTER);
        borderPane.setCenter(hBox);
        borderPane.setTop(menuBar);


        Image image = new Image("mediaMap/background1.jpg");

        borderPane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.AUTO));


        Scene scene = new Scene(borderPane, 1024, 768);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
