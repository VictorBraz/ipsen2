package View;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Created by Gebruiker on 26-10-2016.
 */
public class InfoLabelsView extends VBox{

    public InfoLabelsView(){
        Label id = new Label("ID: ");
        Label name = new Label("Name: ");
        Label email = new Label("E-mail: ");
        Label phoneNumber = new Label("Phone Number: ");
        Label address = new Label("Address: ");
        this.getChildren().addAll(id, name, email, phoneNumber, address);
        this.setSpacing(40);
        this.setMaxHeight(300);
        this.setPadding(new Insets(10, 10, 10, 20));
    }
}
