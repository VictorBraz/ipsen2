package Model;

import DAO.DocumentDAO;
import java.io.*;


/**
 * Created by Negin Nafissi on 27-10-2016.
 */
public class Document {
    private int documentID;
    private String documentName;
    private int ownerID;
    private String date;
    private String ownerName;
    private DocumentDAO documentDAO;
    private File file;


    public Document() throws IOException {
    }

    public Document(int documentID, String documentName, int ownerID,
                    String date, String ownerName, File file) throws IOException {

        this.documentID = documentID;
        this.documentName = documentName;
        this.ownerID = ownerID;
        this.date = date;
        this.ownerName = ownerName;
        this.file = file;
    }

    public int getDocumentID() {
        return documentID;
    }

    public void setDocumentID(int documentID) {
        this.documentID = documentID;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public DocumentDAO getDocumentDAO() {
        return documentDAO;
    }

    public void setDocumentDAO(DocumentDAO documentDAO) {
        this.documentDAO = documentDAO;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
