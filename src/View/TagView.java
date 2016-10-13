package View;

import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Negin Nafissi on 12-10-2016.
 */
public class TagView extends HBox{

    Scanner input = new Scanner(System.in);
    TextArea tagsArea = new TextArea("Tags: ");
    ArrayList<String> tag = new ArrayList<String>();

    public TagView(){

    }
}
