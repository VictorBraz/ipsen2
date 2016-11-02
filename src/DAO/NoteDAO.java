package DAO;

import Model.Note;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by Bernd on 13-10-2016.
 */
public class NoteDAO extends DAO{

    public NoteDAO() throws IllegalAccessException, InstantiationException, SQLException {
        super();
    }
    
    public Note addNote(Note note) throws SQLException{
        return addNoteQuery(note);
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


    /**
     *
     * @Author Roel
     * @param note
     * @throws SQLException
     */
    private Note addNoteQuery(Note note) throws SQLException{
        String sql = "INSERT INTO note(note, ownerid) VALUES(?,?)";
        PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        statement.setString(1, note.getText());
        statement.setInt(1,note.getOwnerID());
        int rowInserted = statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()){
            int id = rs.getInt(1);
            note.setNoteID(id);
        }
        if (rowInserted > 0) {
            System.out.println("A new Note was inserted succesfully!");
        }
        return note;
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
