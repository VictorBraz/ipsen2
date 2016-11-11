package contentloader;

/**
 * Created by Bernd on 9-11-2016.
 */
public interface PropertiesLoaderInterface {
    String getHost();
    void setHost(String host);

    String getPort();
    void setPort(String port);

    String getDatabaseName();
    void setDatabaseName(String databaseName);

    String getUserName();
    void setUserName(String userName);

    String getPassword();
    void setPassword(String password);
}
