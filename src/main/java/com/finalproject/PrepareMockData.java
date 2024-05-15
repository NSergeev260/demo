package com.finalproject;

import com.finalproject.card.CreditCard;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

import com.finalproject.card.ICard;
import com.finalproject.jdbc.ConnectionToDB;
import com.finalproject.jdbc.CrudMethodsCard;
import com.finalproject.services.CardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class PrepareMockData {

    @EventListener(ContextRefreshedEvent.class)
    void prepareData() {
        try (Connection connection = ConnectionToDB.getConnection()) {
            Statement statement = connection.createStatement();
            Path path = Paths.get("src\\main\\resources\\DBTransportCard.sql").toAbsolutePath();
            List<String> lines = Files.lines(Path.of(String.valueOf(path)))
                .filter(x -> !x.isBlank())
                .collect(Collectors.toList());
            for (String str : lines) {
                statement.executeUpdate(str);
            }

            List<ICard> cards = CrudMethodsCard.getCardsCollection(connection);
            log.info("Get cards data {}", cards.toString());
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
