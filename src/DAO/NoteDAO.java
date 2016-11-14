package DAO;

import Model.Note;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by Roel on 13-10-2016.
 */
public class NoteDAO extends DAO{

    /**
     * Instantiates a new Note dao.
     *
     * @throws IllegalAccessException the illegal access exception
     * @throws InstantiationException the instantiation exception
     * @throws SQLException           the sql exception
     */
    public NoteDAO() throws IllegalAccessException, InstantiationException, SQLException {
        super();
    }

    /**
     * Add note note.
     *
     * @param note the note
     * @return the note
     */
    public Note addNote(Note note){
        try{
            note = addNoteQuery(note);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return note;
    }

    /**
     * Update note.
     *
     * @param note the note
     * @return the note
     * @throws SQLException the sql exception
     */
    public Note update(Note note) throws SQLException{
        return updateNoteQuery(note);
    }

    /**
     * Select note note.
     *
     * @param ownerID the owner id
     * @return the note
     * @throws SQLException the sql exception
     */
    public Note selectNote(int ownerID) throws SQLException{
        Note note = selectNoteQuery(ownerID);
        return note;
    }

    /**
     * Delete note.
     *
     * @param note the note
     * @throws SQLException the sql exception
     */
    public void deleteNote(Note note) throws  SQLException{
        deleteNoteQuery(note);
    }


    private Note addNoteQuery(Note note) throws SQLException{
        String sql = "INSERT INTO note(note, ownerID) VALUES(?,?)";
        PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        statement.setString(1, note.getText());
        statement.setInt(2, note.getOwnerID());

        int rowInserted = statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();

        if (rs.next()){
            int id = rs.getInt(1);
            note.setNoteID(id);
        }

        if (rowInserted > 0) {
            System.out.println("A new Note was inserted succesfully!");
        }
        statement.close();
        return note;
    }

    private Note updateNoteQuery(Note note) throws SQLException{
        String sql = "UPDATE note SET note=? WHERE noteID=?";
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setString(1,note.getText());
        statement.setInt(2,note.getNoteID());
        int rowInserted = statement.executeUpdate();

        if (rowInserted > 0){
            System.out.println("The note Has Been updated succesfully!");
        }
        statement.close();
        return note;
    }

    private Note selectNoteQuery(int ownerID) throws SQLException{
        String sql = "SELECT * FROM note WHERE ownerID=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        Note note = new Note();

        statement.setInt(1,ownerID);
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            note.setNoteID(rs.getInt(1));
            note.setText(rs.getString(2));
            note.setOwnerID(rs.getInt(3));
        }
        statement.close();
        return note;
    }

    private void deleteNoteQuery(Note note) throws SQLException{
        String sql = "DELETE FROM note WHERE ownerID=?";
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1,note.getOwnerID());
        statement.executeUpdate();
        statement.close();
    }

}
