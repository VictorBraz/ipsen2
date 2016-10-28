package Controller;

import DAO.DocumentDAO;
import Model.Document;

import java.sql.SQLException;

/**
 * Created by Negin Nafissi on 28-10-2016.
 */
public class DocumentController {

    private DocumentDAO dao;

    public DocumentController() throws IllegalAccessException, SQLException, InstantiationException {
        this.dao = new DocumentDAO();
    }

    public void cmdAddDocument(Document document) throws SQLException{
        dao.addDocument(document);
    }

    public void cmdDeleteDocument(int documentID) throws SQLException {
        dao.deleteDocument(documentID);
    }

    public void cmdSelectDocument(int documentID) throws SQLException {
        dao.selectDocument(documentID);
    }
}
