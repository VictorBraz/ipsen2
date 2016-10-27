package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Created by Negin Nafissi on 12-10-2016.
 */
public class TagView extends HBox {

    HBox hBox1 = new HBox();
    HBox hBox2 = new HBox();
    Label tag = new Label("Tags: ");
    Label tag1 = new Label("bla");
    Label tag2 = new Label("blabla");
    Button edit = new Button("Edit");

    public TagView(){
        tag.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        hBox1.getChildren().addAll(tag, tag1, tag2);
        hBox2.getChildren().addAll(edit);
        hBox2.setAlignment(Pos.CENTER_RIGHT);
        hBox1.setSpacing(20);
        hBox1.setPadding(new Insets(10, 10, 10, 10));
        hBox2.setMargin(edit, new Insets(5, 20, 10, 15));
        this.getChildren().addAll(hBox1, hBox2);
    }
}
