package com.finalproject.jdbc;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToBD {

    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "roottest";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(DRIVER).getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Registering JDBC driver...");
        } catch(ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                InvocationTargetException | SQLException e) {
            e.printStackTrace();
            System.out.println("Register error");
        }
        return connection;
    }

}
