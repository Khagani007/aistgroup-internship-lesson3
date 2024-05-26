package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    public static Connection getConnection() throws SQLException {
        String jdbcUrl = "jdbc:h2:file:data/geocoding_db";
        String username = "admin";
        String password = "admin";
        return DriverManager.getConnection(jdbcUrl, username, password);
    }
}
