package com.finalproject.jdbc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Component
public class InitDB {

    @Value("${jdbc.initScript}")
    public String initScript;

    public String initializationDB(Connection connection) {

        try (Statement statement = connection.createStatement()) {
            Path path = Paths.get(initScript).toAbsolutePath();
            List<String> lines = Files.lines(Path.of(String.valueOf(path)))
                .filter(x -> !x.isBlank())
                .toList();
            String temp = "";

            for (String str : lines) {
                if ((str.contains("SELECT")) || (str.contains("DESCRIBE"))) {
                    continue;
                }
                if (str.contains(";")) {
                    if (!temp.isEmpty()) {
                        statement.executeUpdate(temp + str);
                        temp = "";
                    } else {
                        statement.executeUpdate(str);
                    }
                } else {
                    temp += str;
                }
            }
            return "DB is created";
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return "Exception";
        }
    }
}