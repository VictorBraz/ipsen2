package View;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Created by Negin Nafissi on 24-10-2016.
 */
public class NoteView extends HBox {

    Label note = new Label("Note: ");
    Label noteText = new Label("blablablablabla...!");


    public NoteView(){
        note.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        this.getChildren().addAll(note, noteText);
        this.setSpacing(20);
        this.setPadding(new Insets(10, 10, 10, 10));
    }

}
