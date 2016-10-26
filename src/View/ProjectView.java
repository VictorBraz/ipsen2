package View;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Created by Gebruiker on 26-10-2016.
 */
public class ProjectView extends Pane {

    GridPane pane = new GridPane();

    VBox bottom = new VBox();
    HBox top = new HBox();

    DocumentView documents;
    TagView tags;
    NoteView notes;

    public ProjectView() {

        documents = new DocumentView();
        tags = new TagView();
        notes = new NoteView();

        Label project = new Label("Project: ");
        project.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        Button edit = new Button("Edit");
        Button home = new Button("Home");



        top.getChildren().addAll(home,project, edit);
        top.setSpacing(410);
        top.setPadding(new Insets(10, 10, 10, 10));
        top.setPrefHeight(50);

        bottom.getChildren().addAll(documents, tags, notes);
        bottom.setPrefWidth(1024);
        bottom.setStyle("-fx-background-color: WHITE;");

        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPrefSize(1024, (768-(bottom.getHeight()+top.getHeight())));
        pane.relocate(0, 50);
        this.getChildren().addAll(top, pane, bottom);
        this.setPrefSize(1024, 768);
        this.setStyle("-fx-background-color: linear-gradient(from 50% 50% to 5% 5%,  #3498db , #e3f2fd);");
        bottom.relocate(0, 630);
    }
}
