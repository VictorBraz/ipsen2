package Controller.menu;


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

public class MainFrameController extends ContentLoader implements Initializable {

    @FXML private AnchorPane root;
    @FXML private StackPane contentHolder;
    @FXML private Pane contentCover;
    @FXML private AnchorPane actionBar;
    @FXML private Label titelLabel;
    @FXML private ImageView menuButton;
    @FXML private ImageView closeButton;
    @FXML private ImageView minimizeButton;
    @FXML private AnchorPane navigationMenu;
    @FXML private Pane homeNav;
    @FXML private Pane studentsNav;
    @FXML private Pane clientsNav;
    @FXML private Pane projectsNav;
    @FXML private Pane companiesNav;

    private static double xOffset = 0;
    private static double yOffset = 0;
    private ResourceBundle resources;


    public void setTitle(String titel){
        titelLabel.setText(titel);
    }

    @FXML
    void handleCloseButton(MouseEvent event) {
        getPrimaryStage(event).close();
    }

    @FXML
    void handleMinimizeButton(MouseEvent event) {
        getPrimaryStage(event).toBack();
    }

    @FXML
    void handleMouseDragged(MouseEvent event) {
        primaryStage.setX(event.getScreenX() + xOffset);
        primaryStage.setY(event.getScreenY() + yOffset);
    }

    @FXML
    void handleMousePressed(MouseEvent event) {
        primaryStage = getPrimaryStage(event);
        xOffset = primaryStage.getX() - event.getScreenX();
        yOffset = primaryStage.getY() - event.getScreenY();
    }

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
        }
        closeNavMenu();
        }

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

    public void removeAllContent() {
        contentHolder.getChildren().removeAll();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        prepareSlideMenuAnimation();
    }
}