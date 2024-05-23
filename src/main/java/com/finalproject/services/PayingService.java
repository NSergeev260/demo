package com.finalproject.services;

import com.finalproject.card.CardType;
import com.finalproject.card.CreditCard;
import com.finalproject.card.DebitCard;
import com.finalproject.card.ICard;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.finalproject.history.Operation;
import com.finalproject.jdbc.CrudMethodsCard;
import com.finalproject.jdbc.CrudMethodsHistory;
import com.finalproject.transport.Transport;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class PayingService {

    private CardService cardService;
    private CrudMethodsCard crudMethodsCard;
    private CrudMethodsHistory crudMethodsHistory;
    public static final String CHECK_CARD_ID_PLEASE = "Check cardId, please";
    public static final String DETAIL_INFO = "Terminal ID: {}, Time: {}, Card ID: {}";

    public String payMoney(String cardId, Transport typeOfTransport, String terminalId) {
        log.info(DETAIL_INFO, terminalId, LocalDateTime.now(), cardId);
        BigDecimal cost = typeOfTransport.getTripCost();
        Optional<ICard> cardById = cardService.findCardById(cardId);
        if (cardById.isPresent()) {
            ICard card = cardById.get();
            if (!card.isBlocked()) {
                BigDecimal payByCard = new BigDecimal(String.valueOf(card.getBalance()))
                    .subtract(cost);
                return returnBalance(terminalId, card, cost, payByCard);
            }
            log.info("Card is blocked!");
        }
        return "Not enough for traveling. Put money on card, please";
    }

    private String returnBalance(String terminalId, ICard card, BigDecimal cost, BigDecimal payByCard) {
        boolean result = true;
        if ((card.getType().equals(CardType.CREDIT)) && ((card.getBalance()
            .add(CreditCard.CUT_OFF_BANK_DEPT)).compareTo(cost) >= 0)) {
            card.setBalance(payByCard);
        } else if ((card.getType().equals(CardType.DEBIT)) && (card.getBalance().compareTo(cost) >= 0)) {
            card.setBalance(payByCard);
        } else {
            log.info("Not enough money for trip");
            card.block();
            result = false;
            crudMethodsCard.updateCard(card);
        }

        log.info("Trip cost is: {}", cost);
        log.info("Your balance is {}", card.getBalance());
        crudMethodsCard.updateCard(card);
        crudMethodsHistory.insertHistory(card, String.valueOf(Operation.PAY), result, cost, terminalId);
        return card.getBalance().toString();
    }

    public String putMoney(String cardId, BigDecimal money, String terminalId) {
        log.info(DETAIL_INFO, terminalId, LocalDateTime.now(), cardId);
        Optional<ICard> cardById = cardService.findCardById(cardId);
        if (cardById.isPresent()) {
            ICard card = cardById.get();
            card.setBalance(new BigDecimal(String.valueOf(card.getBalance())).add(money));
            log.info("You put: {}", money);
            log.info("Your balance is {}", card.getBalance());
            if (card.isBlocked()) {
                card.unblock();
            }
            boolean result = crudMethodsCard.updateCard(card);
            crudMethodsHistory.insertHistory(card, String.valueOf(Operation.PUT), result, money, terminalId);
            return card.getBalance().toString();
        }
        log.info(CHECK_CARD_ID_PLEASE);
        return CHECK_CARD_ID_PLEASE;
    }

    public String getBalance(String cardId, String terminalId) {
        log.info(DETAIL_INFO, terminalId, LocalDateTime.now(), cardId);
        Optional<ICard> cardById = cardService.findCardById(cardId);
        if (cardById.isPresent()) {
            BigDecimal balance = cardById.get().getBalance();
            log.info("Your balance is {}", balance);
            return balance.toString();
        }
        log.info(CHECK_CARD_ID_PLEASE);
        return CHECK_CARD_ID_PLEASE;
    }

    public ICard getCardInfo(String cardId) {
        Optional<ICard> cardById = cardService.findCardById(cardId);
        if (cardById.isPresent()) {
            ICard card = cardById.get();
            log.info("Info card: {}, Time: {}", card, LocalDateTime.now());
            return card;
        }
        log.info(CHECK_CARD_ID_PLEASE);
        return null;
    }

    public String insertNewCard(CardType cardType, String terminalId) {
        log.info("Insert new {} card, Time: {}", cardType, LocalDateTime.now());
        String cardId = UUID.randomUUID().toString();
        BigDecimal balance = new BigDecimal(200);
        boolean blocked = false;
        BigDecimal amount = new BigDecimal(0);
        String documentId = UUID.randomUUID().toString();
        ICard card;
        if (cardType.equals(CardType.CREDIT)) {
            card = new CreditCard(balance, blocked, documentId);
        } else {
            card = new DebitCard(balance, blocked);
        }
        boolean result = crudMethodsCard.insertCard(card);
        crudMethodsHistory.insertHistory(card, String.valueOf(Operation.INSERT), result,
            amount, terminalId);
        log.info("New card with id {} was created", card.getCardId());
        return "New card was created";
    }
}