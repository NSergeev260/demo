package com.finalproject.services;

import com.finalproject.card.ICard;

import java.time.LocalDateTime;
import java.util.Optional;
import com.finalproject.history.Operation;
import com.finalproject.jdbc.CrudMethodsCard;
import com.finalproject.jdbc.CrudMethodsHistory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class CardService {

    private CrudMethodsCard crudMethodsCard;
    private CrudMethodsHistory crudMethodsHistory;
    private static final String MESSAGE = "Check cardId, please";

    public Optional<ICard> findCardById(String cardId) {
        return Optional.ofNullable(crudMethodsCard.getCard(cardId));
    }

    public String block(String cardId) {
        Optional<ICard> cardById = findCardById(cardId);
        if (cardById.isPresent()) {
            ICard card = cardById.get();
            card.block();
            log.info("CardId: {}", cardId);
            log.info("Card is blocked! Time: {}", LocalDateTime.now());
            boolean result = crudMethodsCard.updateCard(card);
            crudMethodsHistory.insertHistory(card, String.valueOf(Operation.BLOCK), result, null);
            return "true";
        }
        log.info(MESSAGE);
        return MESSAGE;
    }

    public String unblock(String cardId) {
        Optional<ICard> cardById = findCardById(cardId);
        if (cardById.isPresent()) {
            ICard card = cardById.get();
            card.unblock();
            log.info("CardId: {}", cardId);
            log.info("Card is unblocked! Time: {}", LocalDateTime.now());
            boolean result = crudMethodsCard.updateCard(card);
            crudMethodsHistory.insertHistory(card, String.valueOf(Operation.UNBLOCK), result, null);
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
}
