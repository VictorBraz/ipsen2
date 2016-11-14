package DAO;

import model.Document;

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
    /**
     * The My file.
     */
    File myFile;

    /**
     * Instantiates a new Document dao.
     *
     * @throws IllegalAccessException the illegal access exception
     * @throws InstantiationException the instantiation exception
     * @throws SQLException           the sql exception
     */
    public DocumentDAO() throws IllegalAccessException, InstantiationException, SQLException {
        super();
    }

    /**
     * Add document document.
     *
     * @param document the document
     * @return the document
     * @throws SQLException the sql exception
     */
    public Document addDocument(Document document) throws SQLException{
        try {
            addDocumentQuery(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }

    /**
     * Select all documents array list.
     *
     * @param ownerID the owner id
     * @return the array list
     * @throws SQLException the sql exception
     * @throws IOException  the io exception
     */
    public ArrayList<Document> selectAllDocuments(int ownerID) throws SQLException, IOException {
        ArrayList<Document> documents = selectAllDocumentsQuery(ownerID);
        return documents;
    }

    /**
     * Select document document.
     *
     * @param id the id
     * @return the document
     * @throws SQLException the sql exception
     * @throws IOException  the io exception
     */
    public Document selectDocument(int id) throws SQLException, IOException {
        Document document = selectDocumentQuery(id);
        return document;
    }

    /**
     * Delete document.
     *
     * @param id the id
     * @throws SQLException the sql exception
     */
    public void deleteDocument(int id) throws SQLException{
        try {
            deleteDocumentQuery(id);
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
            statement.setBinaryStream(4, fis, (int)file.length());

        } else {
            statement.setNull(4, Types.OTHER);
        }
        statement.executeUpdate();
        statement.close();
        fis.close();
    }


    /**
     * Select all documents query array list.
     *
     * @param ownerID the owner id
     * @return the array list
     * @throws SQLException the sql exception
     * @throws IOException  the io exception
     */
    public ArrayList<Document> selectAllDocumentsQuery(int ownerID) throws SQLException, IOException {
        ArrayList<Document> documents = new ArrayList<Document>();
            String sql = "SELECT * FROM document WHERE ownerid = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,ownerID);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Document document = new Document();
                document.setId(result.getInt(1));
                document.setDocumentName(result.getString(3));
                documents.add(document);
            }
        statement.close();
        return documents;
    }

    private Document selectDocumentQuery(int id) throws SQLException, IOException {
        String sql = "SELECT * FROM document WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1,id);
        ResultSet result = statement.executeQuery();
        Document document = null;

        myFile = File.createTempFile("test",".pdf");
        InputStream input;
        FileOutputStream output = new FileOutputStream(myFile);
        byte[] buffer = new byte[1024];

        while(result.next()) {
            document = new Document();
            document.setDocumentName(result.getString(1));
            document.setOwnerID(result.getInt(2));
            document.setDate(result.getString(3));
            input = result.getBinaryStream(5);
            while (input.read(buffer) > 0){
                output.write(buffer);
            }
        }

        document.setFile(myFile);
        output.close();
        statement.close();
        return document;
    }


    private void deleteDocumentQuery(int id) throws SQLException {
        String sql = "DELETE FROM document WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet keyResultSet = statement.getGeneratedKeys();

        statement.setInt(1,id);
        while (keyResultSet.next()) {
            if(keyResultSet.equals(id)) {
                int rowsDeleted = statement.executeUpdate();

            }
        }
        statement.executeUpdate();
        statement.close();
    }


}
