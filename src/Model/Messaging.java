package Model;

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

    @FXML
    public void loadDialog() {
        JFXDialogLayout content = new JFXDialogLayout();

        JFXDialog dialog = new JFXDialog(stackPane, new Label("fout"), JFXDialog.DialogTransition.CENTER);

        dialog.show();

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

//    public boolean isShowingMessage;
//    private static Messaging messagingInstance;
//
//    /**
//     * Gets instance.
//     *
//     * @return the instance
//     */
//    public static synchronized Messaging getInstance()
//    {
//        if(messagingInstance == null)
//        {
//            messagingInstance = new Messaging();
//        }
//
//        return messagingInstance;
//    }
//
//    /**
//     * Show message.
//     *
//     * @param title   the title
//     * @param header  the header
//     * @param message the message
//     */
//    public void show(String title, String header, String message)
//    {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle(title);
//        alert.setHeaderText(header);
//        alert.setContentText(message);
//        alert.setX(280);
//        alert.
//
//        DialogPane dialogPane = alert.getDialogPane();
//        dialogPane.getStylesheets().add(
//                getClass().getResource("../mediaMap/styles.css").toExternalForm());
//        dialogPane.getStyleClass().add("myDialog");
//
//        if (!this.isShowingMessage)
//        {
//            this.isShowingMessage = true;
//
//            Optional<ButtonType> result = alert.showAndWait();
//
//            if (result.get() == ButtonType.OK)
//            {
//                this.isShowingMessage = false;
//            }
//        }
//    }
}
