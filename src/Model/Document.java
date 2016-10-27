package Model;

/**
 * Created by Negin Nafissi on 27-10-2016.
 */
public class Document {
    private int documentID;
    private String documentName;
    private int ownerID;
    private String date;
    private String ownerName;

    public Document(){

    }
    public Document(int documentID, String documentName, int ownerID,
                    String date, String ownerName){
        this.documentID = documentID;
        this.documentName = documentName;
        this.ownerID = ownerID;
        this.date = date;
        this.ownerName = ownerName;
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
}
