package services;

import contentloader.PropertiesLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * @author Victor
 * Created by Victor on 12-9-2016.
 */
public class Database extends PropertiesLoader{
    /**
     * @author Victor
     */
    private static Database connectionInstance;


    private Database() {
        super();

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

        //String URL = "jdbc:postgresql://localhost:5432/HubSpot";

        String URL = "jdbc:postgresql://"+ getHost() +":" + getPort() +  "/" + getDatabaseName() +"";
        Properties info = new Properties();
        Connection conn = DriverManager.getConnection(URL, getUserName(), getPassword());
        return conn;
    }



}

