package com.finalproject.jdbc;

import com.finalproject.card.ICard;
import com.finalproject.jdbc.CrudMethodsCard;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class JDBCRunner {
    private static ConnectionToBD connectionToDB;

    public static void main(String[] args) {
        
        try (Connection connection = connectionToDB.getConnection()) {
            Statement statement = connection.createStatement();
            Path path = Paths.get("src\\main\\resources\\DBTransportCard.sql").toAbsolutePath();
            List<String> lines = Files.lines(Path.of(String.valueOf(path)))
                .filter(x -> !x.isBlank())
                .collect(Collectors.toList());
            for (String str : lines) {
                statement.executeUpdate(str);
            }

            List<ICard> cards = CrudMethodsCard.getCardsData(connection);
            log.info("Get cards data {}", cards.toString());
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}