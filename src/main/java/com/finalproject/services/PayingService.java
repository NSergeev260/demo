package com.finalproject.services;

import com.finalproject.card.CardType;
import com.finalproject.card.CreditCard;
import com.finalproject.card.ICard;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public String payMoney(String cardId, Transport typeOfTransport, String terminalId) {
        log.info("Terminal ID: {}, Time: {}, Card ID: {}", terminalId, LocalDateTime.now(), cardId);
        List<Transport> transport = List.of(Transport.values());
        boolean result;
        if (transport.contains(typeOfTransport)) {
            BigDecimal cost = typeOfTransport.getTripCost();
            Optional<ICard> cardById = cardService.findCardById(cardId);
            if (cardById.isPresent()) {
                ICard card = cardById.get();
                if (!card.isBlocked()) {
                    BigDecimal payByCard = new BigDecimal(String.valueOf(card.getBalance()))
                        .subtract(cost);

                    if ((card.getType().equals(CardType.CREDIT)) && (card.getBalance()
                        .add(CreditCard.CUT_OFF_BANK_DEPT).compareTo(cost) >= 0)) {
                        card.setBalance(payByCard);
                        result = true;
                    } else if ((card.getType().equals(CardType.DEBIT)) && (card.getBalance().compareTo(cost) >= 0)) {
                        card.setBalance(payByCard);
                        result = true;
                    } else {
                        log.info("Not enough money for trip");
                        card.block();
                        result = false;
                        crudMethodsCard.updateCard(card);
                    }

                    log.info("Trip cost is: {}", cost);
                    log.info("Your balance is {}", card.getBalance());
                    crudMethodsCard.updateCard(card);
                    crudMethodsHistory.insertHistory(card, String.valueOf(Operation.PAY), result, cost);
                    return card.getBalance().toString();
                }
                log.info("Card is blocked!");
            }
        }
        return "Not enough for traveling. Put money on card, please";
    }

    public String putMoney(String cardId, BigDecimal money, String terminalId) {
        log.info("Terminal ID: {}, Time: {}, Card ID: {}", terminalId, LocalDateTime.now(), cardId);
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
            crudMethodsHistory.insertHistory(card, String.valueOf(Operation.PUT), result, money);
            return card.getBalance().toString();
        }
        log.info("Check cardId, please");
        return "Check cardId, please";
    }

    public String getBalance(String cardId, String terminalId) {
        log.info("Terminal ID: {}, Time: {}, Card ID: {}", terminalId, LocalDateTime.now(), cardId);
        Optional<ICard> cardById = cardService.findCardById(cardId);
        if (cardById.isPresent()) {
            BigDecimal balance = cardById.get().getBalance();
            log.info("Your balance is {}", balance);
            return balance.toString();
        }
        log.info("Check cardId, please");
        return "Check cardId, please";
    }
}