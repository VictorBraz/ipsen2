package View;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Created by Negin Nafissi on 24-10-2016.
 */
public class TagEditView extends HBox {

    Label tag = new Label("Tags");
    TextField tags = new TextField();
    Button save = new Button("Save");


    public TagEditView(){
        tag.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        this.getChildren().addAll(tag, tags, save);
        this.setMargin(tag, new Insets(5, 20, 10, 5));
        this.setMargin(save, new Insets(5, 20, 10, 5));
    }
}
