package com.example.demo.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton class for managing SQLite database connection.
 * Provides a single instance of the database connection.
 */
public class SqliteConnection {
    private static Connection instance = null;

    /**
     * Private constructor to prevent instantiation.
     * Initializes the database connection.
     */
    private SqliteConnection() {
        String url = "jdbc:sqlite:childcaredb.db";
        try {
            instance = DriverManager.getConnection(url);
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
    }

    /**
     * Returns the single instance of the database connection.
     * If the instance is null, it initializes the connection.
     *
     * @return the single instance of the database connection
     */
    public static Connection getInstance() {
        if (instance == null) {
            new SqliteConnection();
        }
        return instance;
    }
}
