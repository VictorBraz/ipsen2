package model;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Bernd on 5-11-2016.
 */


public class Messaging implements Initializable{

    @FXML
    private StackPane stackPane;

    /**
     * Load dialog.
     */
    @FXML
    public void loadDialog() {
        JFXDialogLayout content = new JFXDialogLayout();

        JFXDialog dialog = new JFXDialog(stackPane, new Label("fout"), JFXDialog.DialogTransition.CENTER);

        dialog.show();

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
