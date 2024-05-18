package com.finalproject.jdbc;

import com.finalproject.card.CardType;
import com.finalproject.card.CreditCard;
import com.finalproject.card.DebitCard;
import com.finalproject.card.ICard;
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
public class CrudMethodsCard {

    private static String GET_CARD = "SELECT * FROM transportCard WHERE cardId = ?";
    private static String INSERT_CARD = "INSERT INTO transportCard(cardId, balance, typeOfCard, isBlocked , documentId) VALUES (?, ?, ?, ?, ?)";
    private static String UPDATE_CARD = "UPDATE transportCard SET balance = ?, isBlocked = ?, documentId = ? WHERE cardId = ?";
    private static String DELETE_CARD = "DELETE FROM transportCard WHERE cardId = ?";
    private static Connection connection = ConnectionToDB.getConnection();
    public static boolean result;

    public List<ICard> getCards() {
        List<ICard> transportCards = new ArrayList<>();
        try (PreparedStatement shownStatement = connection.prepareStatement("SELECT * FROM transportCard")) {
            ResultSet resultSet = shownStatement.executeQuery();

            while (resultSet.next()) {
                String cardId = resultSet.getString("cardId");
                BigDecimal balance = resultSet.getBigDecimal("balance");
                CardType typeOfCard = CardType.valueOf(resultSet.getString(("typeOfCard")));
                boolean isBlocked = resultSet.getBoolean("isBlocked");
                String documentId = resultSet.getString("documentId");

                if (typeOfCard.equals(CardType.CREDIT)) {
                    transportCards.add(new CreditCard(cardId, balance, isBlocked, documentId));
                } else if (typeOfCard.equals(CardType.DEBIT)) {
                    transportCards.add(new DebitCard(cardId, balance, isBlocked));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transportCards;
    }

    public boolean insertCard(ICard card) {
        try (PreparedStatement insertedStatement = connection.prepareStatement(INSERT_CARD)) {
            insertedStatement.setString(1, card.getCardId());
            insertedStatement.setBigDecimal(2, card.getBalance());
            insertedStatement.setString(3, String.valueOf(card.getType()));
            insertedStatement.setBoolean(4, card.isBlocked());
            if (card.getType().equals(CardType.CREDIT)) {
                insertedStatement.setString(5, ((CreditCard) card).getDocumentId());
            } else {
                insertedStatement.setString(5, null);
            }
            insertedStatement.executeUpdate();
            log.info("{} with id {} is added successfully",card.getType(), card.getCardId());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCard(ICard updateCard, String id) {
        ICard card = updateCard;
        try (PreparedStatement updatedStatement = connection.prepareStatement(UPDATE_CARD)) {
            updatedStatement.setBigDecimal(1, card.getBalance());
            updatedStatement.setBoolean(2, card.isBlocked());
            if (card.getType().equals(CardType.CREDIT)) {
                updatedStatement.setString(3, ((CreditCard) card).getDocumentId());
            } else {
                updatedStatement.setString(3, null);
            }
            updatedStatement.setString(4, card.getCardId());
            updatedStatement.executeUpdate();
            log.info("{} with id {} is updated successfully", card.getType(), card.getCardId());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteCard(String id) {
        try (PreparedStatement deletedStatement = connection.prepareStatement(DELETE_CARD)) {
            deletedStatement.setString(1, id);
            deletedStatement.executeUpdate();
            log.info("Card with id {} is deleted successfully", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ICard getCard(String id) {
        ICard card = null;
        try (PreparedStatement selectStatement = connection.prepareStatement(GET_CARD)) {
            selectStatement.setString(1, id);
            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                String cardId = resultSet.getString("cardId");
                BigDecimal balance = resultSet.getBigDecimal("balance");
                CardType typeOfCard = CardType.valueOf(resultSet.getString("typeOfCard"));
                boolean isBlocked = resultSet.getBoolean("isBlocked");
                String documentId = resultSet.getString("documentId");
                if (typeOfCard.equals(CardType.CREDIT)) {
                    return new CreditCard(cardId, balance, isBlocked, documentId);
                } else {
                    return new DebitCard(cardId, balance, isBlocked);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return card;
    }
}