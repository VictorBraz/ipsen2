package View;


import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import javafx.stage.Stage;



/**
 * Created by Negin Nafissi on 12-10-2016.
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
        Button options = new Button();


        //Image background = new Image(getClass().getClassLoader().getResourceAsStream("mediaMap/option.png"),32,32,true,true);
        //options.setGraphic(new ImageView(background));

//      BackgroundImage background1 = new BackgroundImage(background, null, null, null, null);

//        Menu options = new Menu("Options");
//        MenuItem menu1 = new MenuItem("bla");
//        MenuItem menu2 = new MenuItem("blabla");
//        MenuItem menu3 = new MenuItem("blablabla");
//        options.getItems().addAll(menu1, menu2, menu3);
//        MenuBar menuBar = new MenuBar();
//        menuBar.getMenus().addAll(options);

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
        options.setStyle("-fx-background-color: #FFFFFF;" +"-fx-background-radius: 180;" +
                " -fx-background-insets: 0,1,2,3,0; -fx-font-weight: bold; "
                + "-fx-padding: 10 20 10 20;");

        project.setOnAction(e ->{
            //mouse click on project button
        });

        student.setOnAction(e ->{
            //mouse click on student button
        });

        company.setOnAction(e ->{
            //mouse click on company button
        });

        client.setOnAction(e ->{
            //mouse click on client button
        });

        options.setOnAction(e ->{
            //mouse click on option button
        });

        hBox.setMargin(project,new Insets(0,10,0,10));
        hBox.setMargin(student,new Insets(0,10,0,10));
        hBox.setMargin(company,new Insets(0,10,0,10));
        hBox.setMargin(client,new Insets(0,10,0,10));

        hBox.getChildren().addAll(project,student,company,client);
        hBox.setAlignment(Pos.CENTER);
        borderPane.setCenter(hBox);
        borderPane.setTop(options);
        borderPane.setAlignment(options, Pos.TOP_RIGHT);
        borderPane.setAlignment(hBox, Pos.CENTER);
        borderPane.setMargin(options,new Insets(10,10,0,0));

//        borderPane.setBackground();
//        Image background1 = new Image(getClass().getClassLoader().getResourceAsStream("mediaMap/background1.jpg"));
//        borderPane.setBackground(
//                new Background(new BackgroundImage(background1,
//                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)
//                        ));
//    }

        borderPane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #0d47a1, #e3f2fd);");

        Scene scene = new Scene(borderPane, 1024, 768);
//        scene.getStylesheets().addAll(getClass().getResource("/mediaMap/background1.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
