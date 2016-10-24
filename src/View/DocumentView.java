package View;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Created by Gebruiker on 24-10-2016.
 */
public class DocumentView extends HBox {

    Label document = new Label("Documents");
    VBox vBox1 = new VBox();
    VBox vBox2 = new VBox();
    Button document1 = new Button("CV");
    Button document2 = new Button("Motivatie");
    Button add = new Button("Add");
    Button delete = new Button("Delete");


    public DocumentView() {
        document.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        vBox1.getChildren().addAll(document, document1, document2);
        vBox1.setMargin(document, new Insets(10, 20, 10, 15));
        vBox1.setMargin(document1, new Insets(10, 20, 10, 15));
        vBox1.setMargin(document2, new Insets(10, 20, 10, 15));
        vBox2.getChildren().addAll(add, delete);
        this.getChildren().addAll(vBox1, vBox2);

    }
}
