package com.finalproject.jdbc;

import com.finalproject.card.CardType;
import com.finalproject.card.CreditCard;
import com.finalproject.card.ICard;
import com.finalproject.jdbc.CrudMethodsCard;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
public class JDBCRunner {

    public static void main(String[] args) {

        try (Connection connection = ConnectionToDB.getConnection()) {
            Statement statement = connection.createStatement();
            Path path = Paths.get("src\\main\\resources\\DBTransportCard.sql").toAbsolutePath();
            List<String> lines = Files.lines(Path.of(String.valueOf(path)))
                .filter(x -> !x.isBlank())
                .collect(Collectors.toList());

            String temp = null;
            for (String str : lines) {
                if ((str.contains("SELECT")) || (str.contains("DESCRIBE"))) {
                    break;
                }

                if (str.contains(";")) {
                    if (temp != null) {
                        statement.executeUpdate(temp + str);
                        temp = null;
                    } else {
                        statement.executeUpdate(str);
                    }
                } else {
                    if (temp == null) {
                        temp = str;
                    } else {
                        temp = temp + str;
                    }
                }
            }

//            List<ICard> cards = CrudMethodsCard.getCardsCollection(connection);
//            log.info("Get cards data {}", cards);

            ICard newCard = new CreditCard("1", new BigDecimal(50),
                CardType.CREDIT, false, UUID.randomUUID().toString());
            log.info(String.valueOf(CrudMethodsCard.insertCard(connection, newCard)));
//
//            log.info(String.valueOf(CrudMethodsCard.updateCard(connection, "1",
//                String.valueOf(new BigDecimal(100)))));
//
//            ICard card = CrudMethodsCard.getCard(connection, "1");
//            log.info(String.valueOf(card));
//
//            log.info(String.valueOf(CrudMethodsCard.deleteCard(connection, "1")));
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}