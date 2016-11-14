package contentloader;

/**
 * Created by Bernd on 9-11-2016.
 */
public interface PropertiesLoaderInterface {
    /**
     * Gets host.
     *
     * @return the host
     */
    String getHost();

    /**
     * Sets host.
     *
     * @param host the host
     */
    void setHost(String host);

    /**
     * Gets port.
     *
     * @return the port
     */
    String getPort();

    /**
     * Sets port.
     *
     * @param port the port
     */
    void setPort(String port);

    /**
     * Gets database name.
     *
     * @return the database name
     */
    String getDatabaseName();

    /**
     * Sets database name.
     *
     * @param databaseName the database name
     */
    void setDatabaseName(String databaseName);

    /**
     * Gets user name.
     *
     * @return the user name
     */
    String getUserName();

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    void setUserName(String userName);

    /**
     * Gets password.
     *
     * @return the password
     */
    String getPassword();

    /**
     * Sets password.
     *
     * @param password the password
     */
    void setPassword(String password);
}
