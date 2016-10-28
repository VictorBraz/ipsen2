package DAO;

import Model.Document;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Negin Nafissi on 27-10-2016.
 */
public class DocumentDAO extends DAO{

    public DocumentDAO() throws IllegalAccessException, InstantiationException, SQLException {
        super();
    }

    public void addDocument(Document document) throws SQLException{
        try {
            addDocumentQuery(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Document> selectAllDocuments(int ownerID) throws SQLException{
        ArrayList<Document> documents = selectAllDocumentsQuery(ownerID);
        return documents;
    }

    public Document selectDocument(int documentID) throws SQLException{
        Document document = selectDocumentQuery(documentID);
        return document;
    }

    //Document ook meegeven voor delete document
    public void deleteDocument(int documentID) throws SQLException{
        try {
            deleteDocumentQuery(documentID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void addDocumentQuery(Document document) throws SQLException {
        String sql = "INSERT INTO document (documentname, ownerid," +
                " date, ownername) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, document.getDocumentName());
        statement.setInt(2, document.getOwnerID());
        statement.setString(3, document.getDate());
        statement.setString(4, document.getOwnerName());

        statement.close();
    }


    public ArrayList<Document> selectAllDocumentsQuery(int ownerID) throws SQLException {
        ArrayList<Document> documents = new ArrayList<Document>();
            String sql = "SELECT * FROM document WHERE ownerid = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Document document = new Document();
                document.setDocumentName(result.getString(2));
                document.setOwnerID(result.getInt(3));
                document.setDate(result.getString(4));
                document.setOwnerName(result.getString(5));
            }
        statement.close();
        return documents;
    }

    private Document selectDocumentQuery(int documentID) throws SQLException {
        String sql = "SELECT * FROM document WHERE documenttid = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.getGeneratedKeys();
        Document document = null;

        while(result.next()) {
            if(result.equals(document)) {
                document = new Document();
                document.setDocumentName(result.getString(2));
                document.setOwnerID(result.getInt(3));
                document.setDate(result.getString(4));
                document.setOwnerName(result.getString(5));
            }
        }
        statement.close();
        return document;
    }


    private void deleteDocumentQuery(int documentID) throws SQLException {
        String sql = "DELETE FROM document WHERE documentid=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet keyResultSet = statement.getGeneratedKeys();

        while (keyResultSet.next()) {
            if(keyResultSet.equals(documentID)) {
                int rowsDeleted = statement.executeUpdate();
                if(rowsDeleted > 0) {
                    System.out.println("A document was deleted succesfully");
                }
            }
        }
        statement.close();
    }

}
