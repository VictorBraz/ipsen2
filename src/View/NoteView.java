package View;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Created by Negin on 24-10-2016.
 */
public class NoteView extends HBox {

    Label note = new Label("Note");
    Label noteText = new Label("blablablablabla...!");


    public NoteView(){
        note.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        this.getChildren().addAll(note, noteText);
        this.setMargin(note, new Insets(10, 20, 0, 15));
    }

}
