package com.company.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private static Connection conn;
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";

    public static Connection getConnection(){
        if (conn == null) {
            try {
                Class.forName("org.postgresql.Driver");
                Properties props = new Properties();
                props.setProperty("user", "postgres");
                props.setProperty("password","postgres");
                conn = DriverManager.getConnection(url, props);
                System.out.println("Connection to DB has been established.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static void closeConnection(){
        try {
            if (conn != null) {
                conn.close();
                conn = null;
                System.out.println("Connection to DB has been closed.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

