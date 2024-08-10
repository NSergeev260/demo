package com.finalproject.jdbc;

import com.finalproject.crudmethods.IHistoryCrud;
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
    private static final String DELETE_HISTORY = "DELETE FROM card_history WHERE card_id = ?";
    private final Connection connection;

    public CrudMethodsHistoryJDBC(ConnectionToDB connectionToDB) {
        connection = connectionToDB.getConnection();
    }

    public void insertHistory(ICard card, String operation, boolean result,
                              BigDecimal amount, String terminalId) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_HISTORY)) {
            statement.setString(1, card.getCardId());
            statement.setString(2, operation);
            statement.setString(3, String.valueOf(result));
            statement.setBigDecimal(4, amount);
            statement.setString(5, String.valueOf(LocalDateTime.now()));
            statement.setBigDecimal(6, card.getBalance());
            statement.setString(7, terminalId);
            statement.executeUpdate();
            log.info("History record for card with id {} is added successfully", card.getCardId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CardHistory> getHistory() {
        List<CardHistory> history = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM card_history")) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String cardId = resultSet.getString("card_id");
                String operation = resultSet.getString("operation");
                boolean result = Boolean.parseBoolean(resultSet.getString("result"));
                BigDecimal amount = resultSet.getBigDecimal("amount");
                String dateOfOperation = resultSet.getString("date_of_operation");
                BigDecimal balanceAfterOperation = resultSet.getBigDecimal("balance_after_operation");
                String terminalId = resultSet.getString("terminal_id");
                history.add(new CardHistory(cardId, operation,
                    result, amount, dateOfOperation, balanceAfterOperation, terminalId));
            }

            return history;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public CardHistory getHistory(String cardID) {
        CardHistory history = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_HISTORY)) {
            statement.setString(1, cardID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String cardId = resultSet.getString("card_id");
                String operation = resultSet.getString("operation");
                boolean result = Boolean.parseBoolean(resultSet.getString("result"));
                BigDecimal amount = resultSet.getBigDecimal("amount");
                String dateOfOperation = resultSet.getString("date_of_operation");
                BigDecimal balanceAfterOperation = resultSet.getBigDecimal("balance_after_operation");
                String terminalId = resultSet.getString("terminal_id");
                return new CardHistory(id, cardId, operation, result,
                    amount, dateOfOperation, balanceAfterOperation, terminalId);
            }

            return history;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteHistory(String cardId) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_HISTORY)) {
            statement.setString(1, cardId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}