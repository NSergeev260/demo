package com.finalproject.jdbc;

import com.finalproject.history.CardHistory;
import com.finalproject.card.ICard;
import com.finalproject.history.History;
import com.finalproject.transport.Transport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CrudMethodsHistory {

    private static String GET_HISTORY = "SELECT * FROM cardHistory WHERE cardId = ?";
    private static String INSERT_HISTORY = "INSERT INTO cardHistory(cardId, operation, result, amount, dateOfOperation, balanceAfterOperation) VALUES (?, ?, ?, ?, ?, ?)";
    private static Connection connection = ConnectionToDB.getConnection();

    public static List<CardHistory> getHistory() {
        List<CardHistory> history = new ArrayList<>();
        try {
            PreparedStatement shownStatement = connection.prepareStatement("SELECT * FROM cardHistory");
            ResultSet resultSet = shownStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String cardId = resultSet.getString("cardId");
                String operation = resultSet.getString("operation");
                boolean result = resultSet.getBoolean("result");
                BigDecimal amount = resultSet.getBigDecimal("amount");
                String dateOfOperation = resultSet.getString("dateOfOperation");
                BigDecimal balanceAfterOperation = resultSet.getBigDecimal("balanceAfterOperation");
                history.add(new CardHistory(id, cardId, operation, result, amount, dateOfOperation, balanceAfterOperation));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }

    public static void insertHistory(ICard card, String operation, Transport typeOfTransport) {
        try {
            PreparedStatement insertedStatement = connection.prepareStatement(INSERT_HISTORY);
            insertedStatement.setString(1, card.getCardId());
            insertedStatement.setString(2, String.valueOf(History.valueOf(operation)));
            insertedStatement.setBoolean(3, CrudMethodsCard.result);
            insertedStatement.setBigDecimal(4, Transport.valueOf(String.valueOf(typeOfTransport)).getTripCost());
            insertedStatement.setString(5, String.valueOf(LocalDateTime.now()));
            insertedStatement.setBigDecimal(6, card.getBalance());
            insertedStatement.executeUpdate();
            log.info("History record for card with id {} is added successfully", card.getCardId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static CardHistory getHistory(String cardID) {
        CardHistory history = null;
        try {
            PreparedStatement selectStatement = connection.prepareStatement(GET_HISTORY);
            selectStatement.setString(1, cardID);
            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String cardId = resultSet.getString("cardId");
                String operation = resultSet.getString("operation");
                boolean result = resultSet.getBoolean("result");
                BigDecimal amount = resultSet.getBigDecimal("amount");
                String dateOfOperation = resultSet.getString("dateOfOperation");
                BigDecimal balanceAfterOperation = resultSet.getBigDecimal("balanceAfterOperation");
                return new CardHistory(id, cardId, operation, result, amount, dateOfOperation, balanceAfterOperation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }
}
