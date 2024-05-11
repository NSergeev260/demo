package com.finalproject.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
@Component
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
            log.info("Registering JDBC driver...");
        } catch(ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                InvocationTargetException | SQLException e) {
            e.printStackTrace();
            log.info("Register error");
        }
        return connection;
    }

}
