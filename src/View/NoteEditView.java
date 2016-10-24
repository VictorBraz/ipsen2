package View;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Created by Gebruiker on 24-10-2016.
 */
public class NoteEditView extends HBox {

    Label note = new Label("Note");
    TextArea noteText = new TextArea();


    public NoteEditView(){
        note.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        this.getChildren().addAll(note, noteText);
        this.setMargin(note, new Insets(10, 20, 0, 15));
    }
}
