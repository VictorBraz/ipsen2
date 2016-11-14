package model;

import DAO.DocumentDAO;
import java.io.*;


/**
 * Created by Negin Nafissi on 27-10-2016.
 */
public class Document extends TableViewItem {
    private int id;
    private String documentName;
    private int ownerID;
    private String date;
    private String ownerName;
    private DocumentDAO documentDAO;
    private File file;


    /**
     * Instantiates a new Document.
     *
     * @throws IOException the io exception
     */
    public Document() throws IOException {
    }

    /**
     * Instantiates a new Document.
     *
     * @param id           the id
     * @param documentName the document name
     * @param ownerID      the owner id
     * @param date         the date
     * @param ownerName    the owner name
     * @param file         the file
     * @throws IOException the io exception
     */
    public Document(int id, String documentName, int ownerID,
                    String date, String ownerName, File file) throws IOException {

        this.id = id;
        this.documentName = documentName;
        this.ownerID = ownerID;
        this.date = date;
        this.ownerName = ownerName;
        this.file = file;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets document name.
     *
     * @return the document name
     */
    public String getDocumentName() {
        return documentName;
    }

    /**
     * Sets document name.
     *
     * @param documentName the document name
     */
    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    /**
     * Gets owner id.
     *
     * @return the owner id
     */
    public int getOwnerID() {
        return ownerID;
    }

    /**
     * Sets owner id.
     *
     * @param ownerID the owner id
     */
    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets owner name.
     *
     * @return the owner name
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Sets owner name.
     *
     * @param ownerName the owner name
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * Gets document dao.
     *
     * @return the document dao
     */
    public DocumentDAO getDocumentDAO() {
        return documentDAO;
    }

    /**
     * Sets document dao.
     *
     * @param documentDAO the document dao
     */
    public void setDocumentDAO(DocumentDAO documentDAO) {
        this.documentDAO = documentDAO;
    }

    /**
     * Gets file.
     *
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * Sets file.
     *
     * @param file the file
     */
    public void setFile(File file) {
        this.file = file;
    }


}
