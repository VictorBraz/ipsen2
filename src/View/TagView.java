package View;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Negin Nafissi on 12-10-2016.
 */
public class TagView extends HBox {

    Label tag = new Label("Tags");
    Label tag1 = new Label("bla");
    Label tag2 = new Label("blabla");


    public TagView(){
        tag.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        this.getChildren().addAll(tag, tag1, tag2);
        this.setMargin(tag, new Insets(5, 20, 10, 5));
        this.setMargin(tag1, new Insets(10, 20, 10, 15));
        this.setMargin(tag2, new Insets(10, 20, 10, 15));
    }
}
