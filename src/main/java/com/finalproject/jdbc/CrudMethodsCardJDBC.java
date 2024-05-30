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
public class CrudMethodsCardJDBC {

    private static final String GET_CARD = "SELECT * FROM transport_card WHERE card_id = ?";
    private static final String INSERT_CARD = "INSERT INTO transport_card(card_id, balance, type_of_card, is_blocked , document_id) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_CARD = "UPDATE transport_card SET balance = ?, is_blocked = ?, document_id = ? WHERE card_id = ?";
    private static final String DELETE_CARD = "DELETE FROM transport_card WHERE card_id = ?";
    private static final Connection CONNECTION = ConnectionToDB.getConnection();

    public boolean insertCard(ICard card) {
        try (PreparedStatement insertedStatement = CONNECTION.prepareStatement(INSERT_CARD)) {
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
            log.info("{} with id {} is added successfully", card.getType(), card.getCardId());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ICard> getCards() {
        List<ICard> transportCards = new ArrayList<>();
        try (PreparedStatement shownStatement = CONNECTION.prepareStatement("SELECT * FROM transport_card")) {
            ResultSet resultSet = shownStatement.executeQuery();

            while (resultSet.next()) {
                String cardId = resultSet.getString("card_id");
                BigDecimal balance = resultSet.getBigDecimal("balance");
                CardType typeOfCard = CardType.valueOf(resultSet.getString(("type_of_card")));
                boolean isBlocked = resultSet.getBoolean("is_blocked");
                String documentId = resultSet.getString("document_id");

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

    public ICard getCard(String id) {
        ICard card = null;
        try (PreparedStatement selectStatement = CONNECTION.prepareStatement(GET_CARD)) {
            selectStatement.setString(1, id);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                String cardId = resultSet.getString("card_id");
                BigDecimal balance = resultSet.getBigDecimal("balance");
                CardType typeOfCard = CardType.valueOf(resultSet.getString("type_of_card"));
                boolean isBlocked = resultSet.getBoolean("is_blocked");
                String documentId = resultSet.getString("document_id");

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

    public boolean updateCard(ICard card) {
        try (PreparedStatement updatedStatement = CONNECTION.prepareStatement(UPDATE_CARD)) {
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

    public boolean deleteCard(String id) {
        try (PreparedStatement deletedStatement = CONNECTION.prepareStatement(DELETE_CARD)) {
            deletedStatement.setString(1, id);
            deletedStatement.executeUpdate();
            log.info("Card with id {} is deleted successfully", id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}