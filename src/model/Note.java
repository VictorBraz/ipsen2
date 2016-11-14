package model;

/**
 * Created by Bernd on 13-10-2016.
 */
public class Note {

    private int noteID;
    private int ownerID;
    private String text;

    /**
     * Instantiates a new Note.
     */
    public Note() {
    }

    /**
     * Instantiates a new Note.
     *
     * @param noteID the note id
     */
    public Note(int noteID) {
        this.noteID = noteID;
        this.ownerID = Integer.parseInt(null);
        this.text = null;
    }

    /**
     * Instantiates a new Note.
     *
     * @param noteID  the note id
     * @param ownerID the owner id
     * @param text    the text
     */
    public Note(int noteID, int ownerID, String text) {
        this.noteID = noteID;
        this.ownerID = noteID;
        this.text = text;
    }

    /**
     * Gets note id.
     *
     * @return the note id
     */
    public int getNoteID() {
        return noteID;
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
     * Gets text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets note id.
     *
     * @param noteID the note id
     */
    public void setNoteID(int noteID) {
        this.noteID = noteID;
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
     * Sets text.
     *
     * @param text the text
     */
    public void setText(String text) {
        this.text = text;
    }


}
