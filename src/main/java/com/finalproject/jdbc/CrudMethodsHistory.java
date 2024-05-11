package com.finalproject.jdbc;

import com.finalproject.card.CardHistory;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CrudMethodsHistory {

    private static String GET_HISTORY = "SELECT * FROM cardHistory WHERE cardId = ?";
    private static String INSERT_HISTORY = "INSERT INTO cardHistory(id, cardId, operation, result, amount, dateOfOperation, balanceAfterOperation) VALUES (?, ?, ?, ?, ?, ?, ?)";

    public static List<CardHistory> getHistoryData(Connection connection) {
        List<CardHistory> history = new ArrayList<>();
        try {
            PreparedStatement shownStatement = connection.prepareStatement("SELECT * FROM Students");
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

    public static List<CardHistory> insertHistory(Connection connection, CardHistory history) {
        try {
            PreparedStatement insertedStatement = connection.prepareStatement(INSERT_HISTORY);
            insertedStatement.setInt(1, history.getId());
            insertedStatement.setString(2, history.getCardId());
            insertedStatement.setString(3, history.getOperation());
            insertedStatement.setBoolean(4, history.isResult());
            insertedStatement.setBigDecimal(5, history.getAmount());
            insertedStatement.setString(6, history.getDateOfOperation());
            insertedStatement.setBigDecimal(7, history.getBalanceAfterOperation());
            insertedStatement.executeUpdate();
            return getHistoryData(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static CardHistory getHistory(Connection connection, String cardID) {
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
