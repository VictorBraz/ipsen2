package DAO;

import Model.Document;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Negin Nafissi on 27-10-2016.
 */
public class DocumentDAO extends DAO{

    private File file;
    private FileInputStream fis;
    private String outputStream;

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

    public ArrayList<Document> selectAllDocuments(int ownerID) throws SQLException, IOException {
        ArrayList<Document> documents = selectAllDocumentsQuery(ownerID);
        return documents;
    }

    public Document selectDocument(int documentID) throws SQLException, IOException {
        Document document = selectDocumentQuery(documentID);
        return document;
    }

    public void deleteDocument(int documentID) throws SQLException{
        try {
            deleteDocumentQuery(documentID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void addDocumentQuery(Document document) throws SQLException, IOException {
        String sql = "INSERT INTO document (documentname, ownerid, documentdate, pdffile) VALUES (?, ?, ?, ?)";
        File file = document.getFile();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, document.getDocumentName());
        statement.setInt(2, document.getOwnerID());
        statement.setString(3, document.getDate());
        if (file != null){
            fis = new FileInputStream(file);
            statement.setBinaryStream(4, fis);
            fis.close();
        } else {
            statement.setNull(4, Types.OTHER);
        }
        statement.executeUpdate();
        statement.close();
    }


    public ArrayList<Document> selectAllDocumentsQuery(int ownerID) throws SQLException, IOException {
        ArrayList<Document> documents = new ArrayList<Document>();
            String sql = "SELECT documentname FROM document WHERE ownerid = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Document document = new Document();
                document.setDocumentName(result.getString(1));
            }
        statement.close();
        return documents;
    }

    private Document selectDocumentQuery(int documentID) throws SQLException, IOException {
        String sql = "SELECT * FROM document WHERE documenttid = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        Document document = null;

        while(result.next()) {
            if(result.equals(document)) {
                document = new Document();
                document.setDocumentName(result.getString(1));
                document.setOwnerID(result.getInt(2));
                document.setDate(result.getString(3));
                InputStream input = result.getBinaryStream(4);
                file = new File("src/test.pdf");
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buffer = new byte[1];
                while (input.read(buffer) > 0) {
                    fos.write(buffer);
                }
                fos.close();
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
        statement.executeUpdate();
        statement.close();
    }
}
