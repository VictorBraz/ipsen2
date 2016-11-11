package contentloader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Bernd on 9-11-2016.
 */
public class PropertiesLoader implements PropertiesLoaderInterface {
    private static String host;
    private static String port;
    private static String databaseName;
    private static String userName;
    private static String password;

    public PropertiesLoader () {
        InputStream in = ContentLoader.class.getResourceAsStream("/resources/database.properties");
        Properties config = new Properties();
        try {
            config.load(in);
            setHost(config.getProperty("host"));
            setPort(config.getProperty("port"));
            setDatabaseName(config.getProperty("databaseName"));
            setUserName(config.getProperty("userName"));
            setPassword(config.getProperty("password"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public  String getHost() {
        return host;
    }

    public void setHost(String host) {
        PropertiesLoader.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        PropertiesLoader.port = port;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        PropertiesLoader.databaseName = databaseName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        PropertiesLoader.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        PropertiesLoader.password = password;
    }
}
