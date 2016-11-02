package DAO;

import Model.Tag;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Gebruiker on 1-11-2016.
 */
public class TagDAO extends DAO {

    private Tag tag;

    public TagDAO() throws IllegalAccessException, InstantiationException, SQLException {
    }

    public void addTag(Tag tag){
        try {
            addTagQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTag(String tagName){
        try {
            deleteTagQuery(tagName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Tag> selectAllTags(int ownerID){
        ArrayList<Tag> tags = null;
        try {
            tags = selectAllTagsQuery(ownerID);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tags;
    }

    private void addTagQuery() throws SQLException {
        String sql = "INSERT INTO Tag (tagname, ownerid) VALUES (?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, tag.getTagName());
        statement.setInt(2, tag.getOwnerID());

        statement.execute();
        statement.close();
    }


    private ArrayList<Tag> selectAllTagsQuery(int ownerID) throws SQLException, IOException {
        ArrayList<Tag> tags = new ArrayList<Tag>();
        String sql = "SELECT * FROM Tag WHERE ownerid = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            Tag tag = new Tag();
            tag.setTagName(result.getString(1));
            tag.setOwnerID(result.getInt(2));
        }
        statement.close();
        return tags;
    }


    private void deleteTagQuery(String tagName) throws SQLException {
        String sql = "DELETE FROM tag WHERE tagname=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet keyResultSet = statement.getGeneratedKeys();

        while (keyResultSet.next()) {
            if(keyResultSet.equals(tagName)) {
                int rowsDeleted = statement.executeUpdate();
                if(rowsDeleted > 0) {
                    System.out.println("A document was deleted succesfully");
                }
            }
        }
        statement.close();
    }
}
