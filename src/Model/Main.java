package Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.Database;
import services.QueryUpdater;

import java.sql.Connection;

/**
 * @author Victor
 *
 */
public class Main extends Application {

    protected Connection conn;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        this.conn = Database.getInstance().getConnection();
        if (conn != null) {
            new QueryUpdater().update();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
