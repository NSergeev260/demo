package com.finalproject.services;

import com.finalproject.MockData;
import com.finalproject.card.CardType;
import com.finalproject.card.CreditCard;
import com.finalproject.card.DebitCard;
import com.finalproject.card.ICard;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.finalproject.config.CrudFactory;
import com.finalproject.crudmethods.ICardCrud;
import com.finalproject.crudmethods.IHistoryCrud;
import com.finalproject.history.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CardService {
    private final ICardCrud crudMethodsCard;
    private final IHistoryCrud crudMethodsHistory;
    private static final String MESSAGE = "Check cardId, please";
    private MockData mockData;

    public CardService(CrudFactory crudFactory) {
        this.crudMethodsCard = crudFactory.getICardCrud();
        this.crudMethodsHistory = crudFactory.getIHistoryCrud();
    }

    public Optional<ICard> findCardById(String cardId) {
        return Optional.ofNullable(crudMethodsCard.getCard(cardId));
    }

    public String block(String cardId, String terminalId) {
        Optional<ICard> cardById = findCardById(cardId);

        if (cardById.isPresent()) {
            ICard card = cardById.get();
            card.block();
            log.info("CardId: {}", cardId);
            log.info("Card is blocked! Time: {}", LocalDateTime.now());
            int resultOfUpdate = 0;
            String documentId = null;

            if (card.getType().equals(CardType.CREDIT)) {
                resultOfUpdate = crudMethodsCard.updateCard(new CreditCard(cardId, card.getBalance(), card.isBlocked(), ((CreditCard)card).getDocumentId()));
            } else {
                resultOfUpdate = crudMethodsCard.updateCard(new DebitCard(cardId, card.getBalance(), card.isBlocked()));
            }
            boolean result = resultOfUpdate > 0;
            crudMethodsHistory.insertHistory(card,
                Operation.BLOCK.toString(), result,
                null, terminalId);
            return "true";
        }

        log.info(MESSAGE);
        return MESSAGE;
    }

    public String unblock(String cardId, String terminalId) {
        Optional<ICard> cardById = findCardById(cardId);

        if (cardById.isPresent()) {
            ICard card = cardById.get();
            card.unblock();
            log.info("CardId: {}", cardId);
            log.info("Card is unblocked! Time: {}", LocalDateTime.now());
            int resultOfUpdate = crudMethodsCard.updateCard(card);
            boolean result = resultOfUpdate > 0;
            crudMethodsHistory.insertHistory(card,
                Operation.UNBLOCK.toString(), result,
                null, terminalId);
            return "true";
        }

        log.info(MESSAGE);
        return MESSAGE;
    }

    public String isBlocked(String cardId) {
        Optional<ICard> cardById = findCardById(cardId);

        if (cardById.isPresent()) {
            ICard card = cardById.get();
            log.info("CardId: {}", cardId);
            log.info("Card is blocked: {}, Time: {}", card.isBlocked(), LocalDateTime.now());
            return String.valueOf(card.isBlocked());
        }

        log.info(MESSAGE);
        return MESSAGE;
    }

    public List<String> getAllCards() {
        List<String> cardsId = new ArrayList<>();

        for (int i = 0; i < crudMethodsCard.getCards().size(); i++) {
            cardsId.add(crudMethodsCard.getCards().get(i).getCardId());
        }

        log.info("List of cards ID have been received: {}", cardsId);
        return cardsId;
    }

    public int updateCard(String cardId, BigDecimal balance, boolean isBlocked,
                          String documentId) {
        Optional<ICard> cardById = findCardById(cardId);

        if (cardById.isPresent()) {
            ICard card = cardById.get();
            crudMethodsCard.updateCard(card);
            log.info("Card is updated {}, Time: {}", crudMethodsCard.getCard(cardId), LocalDateTime.now());

            int resultOfUpdate = crudMethodsCard.updateCard(card);
            boolean result = resultOfUpdate > 0;
            crudMethodsHistory.insertHistory(card,
                String.valueOf(Operation.UPDATE), result,
                null, documentId);
            return 1;
        }
        log.info("Card with Id {} isn`t updated, Time: {}", cardId, LocalDateTime.now());
        return 0;
    }

    public boolean deleteCard(String cardId) {
        Optional<ICard> cardById = findCardById(cardId);

        if (cardById.isPresent()) {
            ICard card = cardById.get();
            crudMethodsHistory.deleteHistory(card.getCardId());
            crudMethodsCard.deleteCard(card.getCardId());
            log.info("Card {} is deleted, Time: {}", cardId, LocalDateTime.now());
            return true;
        } else {
            log.info("Wrong cardId {}. Can`t delete. Time: {}", cardId, LocalDateTime.now());
            return false;
        }
    }
}
