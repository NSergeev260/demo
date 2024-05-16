package com.finalproject.jdbc;

import com.finalproject.card.CardType;
import com.finalproject.card.CreditCard;
import com.finalproject.card.ICard;
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

            String temp = "";
            for (String str : lines) {
                if ((str.contains("SELECT")) || (str.contains("DESCRIBE"))) {
                    continue;
                }
                if (str.contains(";")) {
                    if (temp.contains("")) {
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

        List<ICard> cards = CrudMethodsCard.getCards();
        log.info("Get cards {}", cards);

        ICard newCard = new CreditCard("1", new BigDecimal(50),
            CardType.CREDIT, true, UUID.randomUUID().toString());
        CrudMethodsCard.insertCard(newCard);

        System.out.println("getCard============================");
        ICard card = CrudMethodsCard.getCard("1");
        log.info(String.valueOf(card));

        System.out.println("UpdateCard============================");
        CrudMethodsCard.updateCard("1", BigDecimal.valueOf(100), false, "1");

        System.out.println("getCard============================");
        card = CrudMethodsCard.getCard("1");
        log.info(String.valueOf(card));

        System.out.println("getCards============================");
        cards = CrudMethodsCard.getCards();
        log.info("Get cards {}", cards);

        CrudMethodsCard.deleteCard("1");
    }
}