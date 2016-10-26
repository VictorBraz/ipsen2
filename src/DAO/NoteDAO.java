package DAO;

import Model.Note;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by Bernd on 13-10-2016.
 */
public class NoteDAO extends DAO{

    public NoteDAO() throws IllegalAccessException, InstantiationException, SQLException {
        super();
    }
    
    public void addNote(Note note) {
        addNoteQuery(note);
    }
    
    public void update(int noteID, Note note) {
        updateNoteQuery(noteID, note);
    }

    public Note selectNote(int noteID) {
        Note note = selectNoteQuery(noteID);
        return note;
    }

    public void deleteNote(int noteID) {
        deleteNoteQuery(noteID);
    }




    
    private void addNoteQuery(Note note) {
    }

    private void updateNoteQuery(int noteID, Note note) {
    }

    private Note selectNoteQuery(int noteID) {
        Note note = null;
        return note;
    }

    private void deleteNoteQuery(int noteID) {
    }

}
