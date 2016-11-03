package services;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;


/**
 * @author Victor
 * Created by Victor on 12-9-2016.
 */
public class Database {
    /**
     * @author Victor
     */
    private volatile static Database connectionInstance;
    private Database() {
        try {
            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {
            System.out.println("Error: Class not found");
            e.printStackTrace();
            return;
        }

    }

    /**
     * @author Victor
     * @return
     */
    public static Database getInstance() {
        if(connectionInstance == null) {
            synchronized (Database.class){
                if(connectionInstance == null){
                    connectionInstance = new Database();
                }
            }
        }
        return connectionInstance;
    }

    /**
     * @author
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        String URL = "jdbc:postgresql://localhost:5432/HubSpot";
        Properties info = new Properties();
        info.put("user", "postgres");
        info.put("password", "1");
        Connection conn = DriverManager.getConnection(URL, info);
//        if (conn != null) {
//            System.out.println("Succesfully connected to the database.");
//        }
        return conn;
    }
}

