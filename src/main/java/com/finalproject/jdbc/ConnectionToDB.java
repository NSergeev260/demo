package com.finalproject.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
@Component
public class ConnectionToDB {

    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.dbname}")
    private String dbname;
    private static boolean initPerformed;

    public Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(url + "/" + dbname, username, password);
            log.info("Registering JDBC driver...");
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException | SQLException e) {
            initDB();
            return getConnection();
        }
        return connection;
    }

    private synchronized void initDB() {

        if (initPerformed) {
            log.info("DB was already initialized");
            return;
        }

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Class.forName(driver).getDeclaredConstructor().newInstance();
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
