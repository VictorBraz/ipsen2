package controller.menu;


import contentloader.ContentLoader;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Bernd on 2-11-2016
 */
public class MainFrameController extends ContentLoader implements Initializable {

    @FXML private StackPane contentHolder;
    @FXML private Pane contentCover;
    @FXML private Label titelLabel;
    @FXML private ImageView menuButton;
    @FXML private AnchorPane navigationMenu;
    @FXML private Pane homeNav;
    @FXML private Pane studentsNav;
    @FXML private Pane clientsNav;
    @FXML private Pane projectsNav;
    @FXML private Pane companiesNav;
    @FXML private Pane searchNav;
    @FXML private Pane settingsNav;

    private static double xOffset = 0;
    private static double yOffset = 0;
    private ResourceBundle resources;


    /**
     * Set title.
     *
     * @param titel the titel
     */
    public void setTitle(String titel){
        titelLabel.setText(titel);
    }

    /**
     * Handle close button.
     *
     * @param event the event
     */
    @FXML
    void handleCloseButton(MouseEvent event) {
        getPrimaryStage(event).close();
    }

    /**
     * Handle minimize button.
     *
     * @param event the event
     */
    @FXML
    void handleMinimizeButton(MouseEvent event) {
        getPrimaryStage(event).setIconified(true);
    }

    /**
     * Handle mouse dragged.
     *
     * @param event the event
     */
    @FXML
    void handleMouseDragged(MouseEvent event) {
        primaryStage.setX(event.getScreenX() + xOffset);
        primaryStage.setY(event.getScreenY() + yOffset);
    }

    /**
     * Handle mouse pressed.
     *
     * @param event the event
     */
    @FXML
    void handleMousePressed(MouseEvent event) {
        primaryStage = getPrimaryStage(event);
        xOffset = primaryStage.getX() - event.getScreenX();
        yOffset = primaryStage.getY() - event.getScreenY();
    }

    /**
     * Handle nav button.
     *
     * @param event the event
     */
    @FXML
    void handleNavButton(MouseEvent event) {
        Object selectedPane = event.getSource();
        if (selectedPane == homeNav){
            addContent(resources.getString("MAINMENU"));
        } else if (selectedPane == clientsNav){
            addContent(resources.getString("CLIENTS"));
        } else if (selectedPane == studentsNav) {
            addContent(resources.getString("STUDENTS"));
        } else if (selectedPane == companiesNav) {
            addContent(resources.getString("COMPANIES"));
        } else if (selectedPane == projectsNav) {
            addContent(resources.getString("PROJECTS"));
        } else if (selectedPane == searchNav) {
            addContent(resources.getString("SEARCH"));
        } else if (selectedPane == settingsNav) {
            addContent(resources.getString("SETTINGS"));
        }
        closeNavMenu();
        }

    /**
     * Sets content.
     *
     * @param node the node
     */
    public void setContent(Node node) {
        contentHolder.getChildren().setAll(node);
        FadeTransition animation = new FadeTransition(Duration.millis(400), node);
        animation.setFromValue(0);
        animation.setToValue(1.0);
        animation.play();
    }

    private void prepareSlideMenuAnimation() {
        menuButton.setOnMouseClicked(event -> {
            if (navigationMenu.getTranslateX() != 0) {
                openNavMenu();
                contentCover.setOnMouseClicked(e -> closeNavMenu());
            } else {
                closeNavMenu();
            }
        });
    }


    private void closeNavMenu() {
        contentCover.setDisable(true);
        TranslateTransition closeNavigationMenu = new TranslateTransition(new Duration(350), navigationMenu);
        closeNavigationMenu.setToX(-(navigationMenu.getWidth()));
        closeNavigationMenu.play();
        contentCover.setStyle(null);
    }

    private void openNavMenu() {
        contentCover.setDisable(false);
        TranslateTransition openNavigatieMenu = new TranslateTransition(new Duration(350), navigationMenu);
        openNavigatieMenu.setToX(0);

        openNavigatieMenu.play();
        contentCover.setStyle("-fx-background-color:rgba(0,0,0,0.2);");
        FadeTransition animation = new FadeTransition(Duration.millis(200), contentCover);
        animation.setFromValue(0);
        animation.setToValue(1.0);
        animation.play();
    }

    /**
     * Remove all content.
     */
    public void removeAllContent() {
        contentHolder.getChildren().removeAll();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        prepareSlideMenuAnimation();
    }

    /**
     * Hamburger menu visible.
     *
     * @param editBoolean the edit boolean
     */
    public void hamburgerMenuVisible(boolean editBoolean) {
        menuButton.setVisible(editBoolean);
    }
}