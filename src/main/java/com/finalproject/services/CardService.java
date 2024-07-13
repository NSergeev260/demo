package com.finalproject.services;

import com.finalproject.card.ICard;
import java.time.LocalDateTime;
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
            int resultOfUpdate = crudMethodsCard.updateCard(card);
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
}
