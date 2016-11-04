package ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Bernd on 4-11-2016.
 */
public abstract class DatabaseSettingsLoader {

    private String host;
    private String port;
    private String databaseName;
    private String userName;
    private String password;


    public DatabaseSettingsLoader() {
    InputStream in = DatabaseSettingsLoader.class.getResourceAsStream("database.properties");
    Properties config = new Properties();
        try {
            config.load(in);
            System.out.println(config.getProperty("host"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
