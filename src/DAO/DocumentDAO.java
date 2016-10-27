package DAO;

import Model.Document;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Negin Nafissi on 27-10-2016.
 */
public class DocumentDAO extends DAO{

    public DocumentDAO() throws IllegalAccessException, InstantiationException, SQLException {
        super();
    }

    public void addDocument(Document document){
        try {
            addDocumentQuery(document);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Document selectDocument(int documentID){
        Document document = selectDocumentQuery(documentID);
        return document;
    }

    public void deleteDocument(int documentID){
        deleteDocumentQuery(documentID);
    }


    private void addDocumentQuery(Document document) throws SQLException {
        String sql = "INSERT INTO document (documentname, ownerid," +
                " date, ownername) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, document.getDocumentName());
        statement.setInt(2, document.getOwnerID());
        statement.setString(3, document.getDate());
        statement.setString(4, document.getOwnerName());
    }


    private Document selectDocumentQuery(int documentID) {
        Document document = null;
        return document;
    }


    private void deleteDocumentQuery(int documentID) {
    }
}
