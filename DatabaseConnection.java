package com.resq.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // ✅ Tor Database er URL, USER, PASSWORD
    private static final String URL = "jdbc:mysql://localhost:3306/resqdb";
    private static final String USER = "root";      // MySQL er user (default root)
    private static final String PASSWORD = "1234";  // Tumi je password set korecho

    public static Connection getConnection() throws SQLException {
        try {
            // ✅ MySQL Connector load
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // ✅ Database er sathe connection return korbe
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
