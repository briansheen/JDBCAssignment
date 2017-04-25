package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by bsheen on 4/25/17.
 */
public class DatabaseUtils {
    private static DatabaseUtils ourInstance = new DatabaseUtils();

    public static DatabaseUtils getInstance() {
        return ourInstance;
    }

    private static final String USER_NAME = "mydbuser";
    private static final String PASSWORD = "mydbpass";
    private static final String DATABASE = "mydb";
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final boolean USE_SSL = false;

    private static final String CONNECT = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + (USE_SSL ? "" : "?useSSL=false");

    private DatabaseUtils(){
    }

    public Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(CONNECT,USER_NAME,PASSWORD);
        return connection;
    }
}
