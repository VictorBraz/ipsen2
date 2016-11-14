package contentloader;

import controller.menu.MainFrameController;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Bernd on 28-10-2016.
 */
public abstract class ContentLoader {

    private static MainFrameController mainController;
    /**
     * The constant primaryStage.
     */
    protected static Stage primaryStage;
    private static FXMLLoader loader;

    /**
     * Sets main controller.
     *
     * @param mainFrameController the main frame controller
     */
    private static void setMainController(MainFrameController mainFrameController) {
        mainController = mainFrameController;
    }

    /**
     * Add content.
     *
     * @param fxml the fxml
     */
    public static void addContent(String fxml) {
        mainController.removeAllContent();

        try {
            loader = configureFXMLLoader();
            setFXMLFileForLoader(loader, fxml);
            mainController.setContent(loader.load());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load main frame pane.
     *
     * @return the pane
     * @throws IOException the io exception
     */
    public static Pane loadMainFrame() throws IOException {
        loader = configureFXMLLoader();
        setFXMLFileForLoader(loader, loader.getResources().getString("MAINFRAME"));
        Pane mainFrame =   loader.load();
        mainFrame.getStylesheets().add(loader.getResources().getString("STYLE"));
        MainFrameController mainController = loader.getController();

        setMainController(mainController);
        addContent(loader.getResources().getString("LOGIN"));
        mainController.hamburgerMenuVisible(false);
        return mainFrame;
    }

    /**
     * Add content.
     *
     * @param controller the controller
     * @param fxml       the fxml
     */
    public static void addContent(Object controller, String fxml) {
        FXMLLoader loader = configureFXMLLoader();
        setFXMLFileForLoader(loader, fxml);
        loader.setController(controller);
        try {
            mainController.setContent(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialises FXMLLoader
     */
    private static FXMLLoader configureFXMLLoader(){
        loader = new FXMLLoader();
        loader.setResources(ResourceBundle.getBundle("resources.Bundle", Locale.ENGLISH));
        return loader;
    }

    /**
     * Sets fxml file for FXMLLoader object <br>
     */
    private static void setFXMLFileForLoader(FXMLLoader loader, String fxml) {
        loader.setLocation((ContentLoader.class.getResource(
                fxml)));
    }

    /**
     * Gets primary stage.
     *
     * @param event the event
     * @return the primary stage
     */
    protected Stage getPrimaryStage(Event event) {
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }

    /**
     * Set main frame title.
     *
     * @param title the title
     */
    protected void setMainFrameTitle(String title){
        mainController.setTitle(title);
    }

    /**
     * Sets hamburger menu visible.
     *
     * @param editBoolean the edit boolean
     */
    protected void setHamburgerMenuVisible(boolean editBoolean) {
        mainController.hamburgerMenuVisible(editBoolean);
    }
}

