package org.example.connection;

import java.sql.*;

public class DBConnection {

    public static String url = "jdbc:mysql://localhost:3306/jdbc_demo";

    public static String userName = "root";

    public static String password = "ahad12345";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, userName, password);
    }

}
