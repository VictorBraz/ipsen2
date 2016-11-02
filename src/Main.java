import contentloader.ContentLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by Bernd on 28-10-2016.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(ContentLoader.loadMainFrame());
        primaryStage.setScene(scene);
        primaryStage.show();
       // new QueryUpdater().update();


    }


    public static void main(String args[]){
        launch(args);
    }

}
