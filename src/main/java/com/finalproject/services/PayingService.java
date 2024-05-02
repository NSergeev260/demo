package com.finalproject.services;

import com.finalproject.card.CardType;
import com.finalproject.card.ICard;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.finalproject.transport.TransportEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class PayingService {

    private static BigDecimal cutOffBankDept = new BigDecimal(100);
    private CardService cardService;

    public String payMoney(String cardId, TransportEnum typeOfTransport, String terminalId) {
        log.info("Terminal ID: {}, Time: {}, Card ID: {}", terminalId, LocalDateTime.now(), cardId);
        List<TransportEnum> transport = List.of(TransportEnum.values());
        if (transport.contains(typeOfTransport)) {
            BigDecimal cost = typeOfTransport.getTripCost();
            Optional<ICard> cardById = cardService.findCardById(cardId);
            if (cardById.isPresent()) {
                ICard card = cardById.get();
                if (!card.isBlocked()) {
                    if (card.getType().equals(CardType.CREDIT) ) {
                        BigDecimal payByCard = new BigDecimal(String.valueOf(card.getBalance())).subtract(cost);
                        BigDecimal payByCreditCard = payByCard.add(cutOffBankDept);
                        card.setBalance(payByCreditCard);
                    } else if (card.getType().equals(CardType.DEBIT) && card.getBalance() >= ) {
                        card.setBalance(new BigDecimal(String.valueOf(card.getBalance()))
                            .subtract(cost));
                    }
                    log.info("You pay successful: {}", cost);
                    log.info("Your balance is {}", card.getBalance());
                    return card.getBalance().toString();
                }
                log.info("Not enough for traveling. Put money on card, please");
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