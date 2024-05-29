package com.finalproject.jdbc;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
//@Component
public class ConnectionToDB {

    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "roottest";
    private static boolean initPerformed;

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(DRIVER).getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(URL + "/card_db", USERNAME, PASSWORD);
            log.info("Registering JDBC driver...");
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException | SQLException e) {
            initDB();
            return getConnection();
        }
        return connection;
    }

    private static synchronized void initDB() {
        if (initPerformed) {
            log.info("DB was already initialized");
            return;
        }

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            Class.forName(DRIVER).getDeclaredConstructor().newInstance();
            InitDB initDB = new InitDB();
            initDB.initializationDB(connection);
            initPerformed = true;
            log.info("DB is initialized");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
