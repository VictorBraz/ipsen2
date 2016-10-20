package View;

import javafx.scene.Node;
import javafx.scene.control.Label;


/**
 * Created by Negin Nafissi on 12-10-2016.
 */

public class LabelBold extends Label {

    public LabelBold() {
        super();
        this.setStyle();
    }

    public LabelBold(String text) {
        super(text);
        this.setStyle();
    }

    private void setStyle(){
        this.setStyle("-fx-font-weight: bold; -fx-font-family: 'Arial'; -fx-font-size:16px;");
    }
}
