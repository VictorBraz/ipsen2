package View;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Created by Gebruiker on 24-10-2016.
 */
public class TagEditView extends HBox {

    Label tag = new Label("Tags");
    TextField tags = new TextField();


    public TagEditView(){
        tag.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        this.getChildren().addAll(tag, tags);
        this.setMargin(tag, new Insets(5, 20, 10, 5));
    }
}
