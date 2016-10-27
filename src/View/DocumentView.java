package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Created by Negin Nafissi on 24-10-2016.
 */
public class DocumentView extends HBox {

    Label document = new Label("Documents: ");
    HBox hBox1 = new HBox();
    HBox hBox2 = new HBox();
    Button document1 = new Button("CV");
    Button document2 = new Button("Motivatie");
    Button add = new Button("Add");
    Button delete = new Button("Delete");


    public DocumentView() {
        document.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        hBox1.getChildren().addAll(document1, document2);
        hBox1.setSpacing(20);
        hBox1.setPadding(new Insets(10, 10, 10, 10));
        hBox2.getChildren().addAll(add, delete);
        hBox2.setSpacing(20);
        hBox2.setPadding(new Insets(10, 10, 10, 10));
        this.getChildren().addAll(document, hBox1, hBox2);
        this.setPrefWidth(1024);
    }
}
