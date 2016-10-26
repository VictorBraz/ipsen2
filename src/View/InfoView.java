package View;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Created by Gebruiker on 26-10-2016.
 */
public class InfoView extends VBox {

    public InfoView(){
        Label id = new Label("... ");
        Label name = new Label(".... ");
        Label birthDate = new Label("..... ");
        Label email = new Label("...... ");
        Label phoneNumber = new Label("....... ");
        Label address = new Label("........ ");
        this.getChildren().addAll(id, name, birthDate, email, phoneNumber, address);
        this.setSpacing(40);
        this.setMaxHeight(300);
        this.setPadding(new Insets(10, 10, 10, 20));
    }
}
