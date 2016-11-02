package Model;

import java.io.IOException;

/**
 * Created by Negin Nafissi on 1-11-2016.
 */
public class Tag {
    private String tagName;
    private int ownerID;

    public Tag() throws IOException {
    }

    public Tag(String tagName, int ownerID) throws IOException {
        this.tagName = tagName;
        this.ownerID = ownerID;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }
}
