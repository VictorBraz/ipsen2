package contentloader;

import Controller.menu.MainFrameController;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Bernd on 28-10-2016.
 */


public abstract class ContentLoader {

    private static String host;
    private static String port;
    private static String databaseName;
    private static String userName;
    private static String password;

    private static MainFrameController mainController;
    /**
     * The constant primaryStage.
     */
    public static Stage primaryStage;
    private static FXMLLoader loader;

    /**
     * The constant lastWindow.
     */
    public static String lastWindow;

    public ContentLoader() {
        InputStream in = ContentLoader.class.getResourceAsStream("/resources/database.properties");
        Properties config = new Properties();
        try {
            config.load(in);
            setHost(config.getProperty("host"));
            setPort(config.getProperty("port"));
            setDatabaseName(config.getProperty("databaseName"));
            setUserName(config.getProperty("userName"));
            setPassword(config.getProperty("password"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets main controller.
     *
     * @param mainFrameController the main frame controller
     */
    public static void setMainController(MainFrameController mainFrameController) {
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
    public Stage getPrimaryStage(Event event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        return primaryStage;
    }

    /**
     * Set main frame title.
     *
     * @param title the title
     */
    public void setMainFrameTitle(String title){
        mainController.setTitle(title);
    }






    protected String getHost() {
        return host;
    }

    protected void setHost(String host) {
        this.host = host;
    }

    protected String getPort() {
        return port;
    }

    protected  void setPort(String port) {
        this.port = port;
    }

    protected String getDatabaseName() {
        return databaseName;
    }

    protected void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    protected String getUserName() {
        return userName;
    }

    protected void setUserName(String userName) {
        this.userName = userName;
    }

    protected String getPassword() {
        return password;
    }

    protected void setPassword(String password) {
        this.password = password;
    }


}

