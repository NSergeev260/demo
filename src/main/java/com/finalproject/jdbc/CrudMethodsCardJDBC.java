package com.finalproject.jdbc;

import com.finalproject.card.CardType;
import com.finalproject.card.CreditCard;
import com.finalproject.card.DebitCard;
import com.finalproject.card.ICard;
import com.finalproject.crudmethods.ICardCrud;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CrudMethodsCardJDBC implements ICardCrud {

    private static final String GET_CARD = "SELECT * FROM transport_card WHERE card_id = ?";
    private static final String INSERT_CARD = "INSERT INTO transport_card(card_id, balance, type_of_card, is_blocked , document_id) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_CARD = "UPDATE transport_card SET balance = ?, is_blocked = ?, document_id = ? WHERE card_id = ?";
    private static final String DELETE_CARD = "DELETE FROM transport_card WHERE card_id = ?";
    private final Connection connection;

    public CrudMethodsCardJDBC(ConnectionToDB connectionToDB) {
        connection = connectionToDB.getConnection();
    }

    public boolean insertCard(ICard card) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_CARD)) {
            statement.setString(1, card.getCardId());
            statement.setBigDecimal(2, card.getBalance());
            statement.setString(3, String.valueOf(card.getType()));
            statement.setString(4, String.valueOf(card.isBlocked()));

            if (card.getType().equals(CardType.CREDIT)) {
                statement.setString(5, ((CreditCard) card).getDocumentId());
            } else {
                statement.setString(5, null);
            }

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ICard> getCards() {
        List<ICard> cardList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM transport_card")) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ResultSetRecords resultRecords = getResultSetRecords(resultSet);

                if (resultRecords.typeOfCard().equals(CardType.CREDIT)) {
                    cardList.add(new CreditCard(resultRecords.cardId(), resultRecords.balance(),
                        resultRecords.isBlocked(), resultRecords.documentId()));
                } else {
                    cardList.add(new DebitCard(resultRecords.cardId(), resultRecords.balance(),
                        resultRecords.isBlocked()));
                }
            }

            return cardList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ICard getCard(String id) {
        ICard card = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_CARD)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                ResultSetRecords resultRecords = getResultSetRecords(resultSet);

                if (resultRecords.typeOfCard().equals(CardType.CREDIT)) {
                    return new CreditCard(resultRecords.cardId(), resultRecords.balance(),
                        resultRecords.isBlocked(), resultRecords.documentId());
                } else {
                    return new DebitCard(resultRecords.cardId(), resultRecords.balance(),
                        resultRecords.isBlocked());
                }
            }

            return card;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int updateCard(String cardId, BigDecimal balance, boolean isBlocked,
                          String documentId) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_CARD)) {
            statement.setBigDecimal(1, balance);
            statement.setString(2, String.valueOf(isBlocked));

            if (getCard(cardId).getType().equals(CardType.CREDIT)) {
                statement.setString(3, documentId);
            } else {
                statement.setString(3, null);
            }

            statement.setString(4, cardId);
            statement.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean deleteCard(String id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_CARD)) {
            statement.setString(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static ResultSetRecords getResultSetRecords(ResultSet resultSet) throws SQLException {
        String cardId = resultSet.getString("card_id");
        BigDecimal balance = resultSet.getBigDecimal("balance");
        CardType typeOfCard = CardType.valueOf(resultSet.getString("type_of_card"));
        boolean isBlocked = resultSet.getBoolean("is_blocked");
        String documentId = resultSet.getString("document_id");
        ResultSetRecords resultRecords = new ResultSetRecords(cardId, balance, typeOfCard, isBlocked, documentId);
        return resultRecords;
    }

    private record ResultSetRecords(String cardId, BigDecimal balance, CardType typeOfCard,
                                    boolean isBlocked, String documentId) {
    }
}