package Controller;

import DAO.DocumentDAO;
import Model.Document;
import javafx.stage.FileChooser;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Negin Nafissi on 28-10-2016.
 */
public class DocumentController {

    private DocumentDAO dao;
    private Document document;

    public DocumentController() throws IllegalAccessException, SQLException, InstantiationException {
        this.dao = new DocumentDAO();
    }

    public void cmdAddDocument(Document document) throws SQLException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.pdf"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"),
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.aac"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        //TODO:stage meegeven
//        File selectedFile = fileChooser.showOpenDialog();
//         if(selectedFile != null){
//            document.setFile(selectedFile);
//            dao.addDocument(document);
//        }

    }

    public void cmdDeleteDocument(int documentID) throws SQLException {
        dao.deleteDocument(documentID);
    }

    public void cmdSelectDocument(int documentID) throws SQLException, IOException {
        dao.selectDocument(documentID);
    }
}
