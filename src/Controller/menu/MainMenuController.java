package Controller.menu;

import contentloader.ContentLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController extends ContentLoader implements Initializable {

    @FXML private Pane companyButton;
    @FXML private Pane projectButton;
    @FXML private Pane studentButton;
    @FXML private Pane clientButton;

    private ResourceBundle resources;

    @FXML void handleShortcutButton(MouseEvent event) {
        Object selectedPane = event.getSource();
        if (selectedPane == studentButton) {
            addContent(resources.getString("STUDENTS"));
        } else if (selectedPane == clientButton) {
            addContent(resources.getString("CLIENTS"));
        } else if (selectedPane == companyButton) {
            addContent(resources.getString("COMPANIES"));
        }else if (selectedPane == projectButton) {
            addContent(resources.getString("PROJECTS"));
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        setMainFrameTitle(resources.getString("HOME_TITLE"));
    }
}
