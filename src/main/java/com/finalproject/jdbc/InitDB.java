package com.finalproject.jdbc;

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
    public String initializationDB(Connection connection) {

        try (Statement statement = connection.createStatement()) {
            Path path = Paths.get("src\\main\\resources\\DBTransportCard.sql").toAbsolutePath();
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
                    temp = temp + str;
                }
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return "DB is created";
    }
}