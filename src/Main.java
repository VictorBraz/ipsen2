import contentloader.ContentLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.QueryUpdater;

/**
 * Created by Bernd on 28-10-2016.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        new QueryUpdater().update();

        primaryStage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(ContentLoader.loadMainFrame());
        primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("mediaMap/taskbarIcon.png")));

        primaryStage.setScene(scene);
        primaryStage.show();



    }


    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String args[]){
        launch(args);
    }

}
