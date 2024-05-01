package com.finalproject.services;

import com.finalproject.card.ICard;

import java.math.BigDecimal;
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
//    TransportEnum transportEnum;

    public BigDecimal payMoney(String cardId, TransportEnum typeOfTransport) {
        List<TransportEnum> transport = List.of(TransportEnum.values());
        if (transport.contains(typeOfTransport)) {
            BigDecimal cost = typeOfTransport.getTripCost();
            Optional<ICard> cardById = cardService.findCardById(cardId);
            if (cardById.isPresent()) {
                ICard card = cardById.get();
                if (!card.isBlocked()) {
                    card.setBalance(new BigDecimal(String.valueOf(card.getBalance())).subtract(cost));
                    log.info("You pay successful: " + cost);
                    log.info("Your balance is : " + card.getBalance());
                    return card.getBalance();
                }
                log.info("Not enough for traveling. Put money on card, please");
            }
        }
        return null;
    }

    public BigDecimal putMoney(String cardId, BigDecimal money) {
        Optional<ICard> cardById = cardService.findCardById(cardId);
        if (cardById.isPresent()) {
            ICard card = cardById.get();
            card.setBalance(new BigDecimal(String.valueOf(card.getBalance())).add(money));
            log.info("You put: " + money);
            log.info("Your balance is : " + card.getBalance());
            return card.getBalance();
        }
        log.info("Check cardId, please");
        return null;
    }

    public BigDecimal getBalance(String cardId) {
        Optional<ICard> cardById = cardService.findCardById(cardId);
        if (cardById.isPresent()) {
            BigDecimal balance = cardById.get().getBalance();
            log.info("Your balance is " + balance);
            return balance;
        }
        log.info("Check cardId, please");
        return null;
    }
}