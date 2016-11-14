package DAO;

import services.Database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Victor on 13-10-2016.
 */
public abstract class DAO {

    /**
     * The Conn.
     */
    protected Connection conn;

    /**
     * Instantiates a new Dao.
     *
     * @throws IllegalAccessException the illegal access exception
     * @throws InstantiationException the instantiation exception
     * @throws SQLException           the sql exception
     */
    public DAO() throws IllegalAccessException, InstantiationException, SQLException {
        this.conn = Database.getInstance().getConnection();
    }
}
