package DAO;

import services.Database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Victor on 13-10-2016.
 */
public abstract class DAO {

    protected Connection conn;

    public DAO() throws IllegalAccessException, InstantiationException, SQLException {
        this.conn = Database.getInstance().getConnection();
    }
}
