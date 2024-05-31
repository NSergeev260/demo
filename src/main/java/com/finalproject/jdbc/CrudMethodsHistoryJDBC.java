package com.finalproject.jdbc;

import com.finalproject.hibernate.IHistoryCrud;
import com.finalproject.history.CardHistory;
import com.finalproject.card.ICard;
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
public class CrudMethodsHistoryJDBC implements IHistoryCrud {

    private static final String GET_HISTORY = "SELECT * FROM card_history WHERE card_id = ?";
    private static final String INSERT_HISTORY = "INSERT INTO card_history(card_id, operation, result, amount, date_of_operation, balance_after_operation, terminal_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final Connection connection;

    public CrudMethodsHistoryJDBC(ConnectionToDB connectionToDB) {
        connection = connectionToDB.getConnection();
    }

    public void insertHistory(ICard card, String operation, boolean result,
                              BigDecimal amount, String terminalId) {
        try (PreparedStatement insertedStatement = connection.prepareStatement(INSERT_HISTORY)) {
            insertedStatement.setString(1, card.getCardId());
            insertedStatement.setString(2, operation);
            insertedStatement.setBoolean(3, result);
            insertedStatement.setBigDecimal(4, amount);
            insertedStatement.setString(5, String.valueOf(LocalDateTime.now()));
            insertedStatement.setBigDecimal(6, card.getBalance());
            insertedStatement.setString(7, terminalId);
            insertedStatement.executeUpdate();
            log.info("History record for card with id {} is added successfully", card.getCardId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CardHistory> getHistory() {
        List<CardHistory> history = new ArrayList<>();
        try (PreparedStatement shownStatement = connection.prepareStatement("SELECT * FROM card_history")) {
            ResultSet resultSet = shownStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String cardId = resultSet.getString("card_id");
                String operation = resultSet.getString("operation");
                boolean result = resultSet.getBoolean("result");
                BigDecimal amount = resultSet.getBigDecimal("amount");
                String dateOfOperation = resultSet.getString("date_of_operation");
                BigDecimal balanceAfterOperation = resultSet.getBigDecimal("balance_after_operation");
                String terminalId = resultSet.getString("terminal_id");
                history.add(new CardHistory(cardId, operation,
                    result, amount, dateOfOperation, balanceAfterOperation, terminalId));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }

    public CardHistory getHistory(String cardID) {
        CardHistory history = null;
        try (PreparedStatement selectStatement = connection.prepareStatement(GET_HISTORY)) {
            selectStatement.setString(1, cardID);
            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String cardId = resultSet.getString("card_id");
                String operation = resultSet.getString("operation");
                boolean result = resultSet.getBoolean("result");
                BigDecimal amount = resultSet.getBigDecimal("amount");
                String dateOfOperation = resultSet.getString("date_of_operation");
                BigDecimal balanceAfterOperation = resultSet.getBigDecimal("balance_after_operation");
                String terminalId = resultSet.getString("terminal_id");
                return new CardHistory(id, cardId, operation, result,
                    amount, dateOfOperation, balanceAfterOperation, terminalId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }
}