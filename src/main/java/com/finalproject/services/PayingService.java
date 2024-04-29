package com.finalproject.services;

import com.finalproject.card.ICard;
import java.math.BigDecimal;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class PayingService {

    private static BigDecimal cutOffBankDept = new BigDecimal(100);
    private CardService cardService;

    public boolean payMoney(String cardId, BigDecimal cost) {
        Optional<ICard> cardById = cardService.findCardById(cardId);
        if (cardById.isPresent()) {
            ICard card = cardById.get();
            if (!card.isBlocked()) {
                card.setBalance(new BigDecimal(String.valueOf(card.getBalance())).subtract(cost));
                log.info("You pay successful: " + cost);
                log.info("Your balance is : " + card.getBalance());
                return true;
            }
            log.info("Not enough for traveling. Put money on card, please");
        }
        return false;
    }

    public boolean putMoney(String cardId, BigDecimal money) {
        Optional<ICard> cardById = cardService.findCardById(cardId);
        if (cardById.isPresent()) {
            ICard card = cardById.get();
            card.setBalance(new BigDecimal(String.valueOf(card.getBalance())).add(money));
            log.info("You put: " + money);
            log.info("Your balance is : " + card.getBalance());
            return true;
        }
        log.info("Check cardId, please");
        return false;
    }

    public boolean getBalance(String cardId) {
        Optional<ICard> cardById = cardService.findCardById(cardId);
        if (cardById.isPresent()) {
            BigDecimal balance = cardById.get().getBalance();
            log.info("Your balance is " + balance);
            return true;
        }
        return false;
    }
}