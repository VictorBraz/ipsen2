//package services;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Properties;
//
///**
// * Created by Bernd on 4-11-2016.
// */
//public abstract class DatabaseSettingsLoader {
//
//    private String host;
//    private String port;
//    private String databaseName;
//    private String userName;
//    private String password;
//
//
//    public DatabaseSettingsLoader() {
//        InputStream in = DatabaseSettingsLoader.class.getResourceAsStream("/resources/database.properties");
//        Properties config = new Properties();
//        try {
//            config.load(in);
//            setHost(config.getProperty("host"));
//            setPort(config.getProperty("port"));
//            setDatabaseName(config.getProperty("databaseName"));
//            setUserName(config.getProperty("userName"));
//            setPassword(config.getProperty("password"));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    protected String getHost() {
//        return host;
//    }
//
//    protected void setHost(String host) {
//        this.host = host;
//    }
//
//    protected String getPort() {
//        return port;
//    }
//
//    protected void setPort(String port) {
//        this.port = port;
//    }
//
//    protected String getDatabaseName() {
//        return databaseName;
//    }
//
//    protected void setDatabaseName(String databaseName) {
//        this.databaseName = databaseName;
//    }
//
//    protected String getUserName() {
//        return userName;
//    }
//
//    protected void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    protected String getPassword() {
//        return password;
//    }
//
//    protected void setPassword(String password) {
//        this.password = password;
//    }
//}
