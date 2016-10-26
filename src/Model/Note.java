package Model;

import DAO.ClientDAO;
import DAO.NoteDAO;

import java.util.HashMap;

/**
 * Created by Bernd on 13-10-2016.
 */
public class Note {

    private int noteID;
    private int ownerID;
    private String text;

    public Note() {
    }

    public Note(int noteID) {
        this.noteID = noteID;
        this.ownerID = Integer.parseInt(null);
        this.text = null;
    }

    public Note(int noteID, int ownerID, String text) {
        this.noteID = noteID;
        this.ownerID = noteID;
        this.text = text;
    }

    public int getNoteID() {
        return noteID;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public String getText() {
        return text;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public void setText(String text) {
        this.text = text;
    }

}
