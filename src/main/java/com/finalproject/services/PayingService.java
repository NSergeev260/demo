package com.finalproject.services;

import com.finalproject.card.AbstractCard;
import com.finalproject.card.ICard;

import java.math.BigDecimal;
import java.util.Optional;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Getter
@Setter
@Service
@Component
public class PayingService {

    private static BigDecimal cutOffBankDept = new BigDecimal(100);
    private CardService cardService;
    AbstractCard card;

    public boolean pay(String cardId, BigDecimal cost) {
        if (!card.isBlocked() && card.getCardId() == cardId) {
            card.setBalance(new BigDecimal(String.valueOf(card.getBalance())).subtract(cost));
            log.info("You pay successful: " + cost);
            log.info("Your balance is : " + card.getBalance());
            return true;
        }
        log.info("Not enough for traveling. Put money on card, please");
        return false;
    }

    public boolean putMoney(String cardId, BigDecimal money) {
        if(card.getCardId() == cardId) {
            card.setBalance(new BigDecimal(String.valueOf(card.getBalance())).add(money));
            log.info("You put: " + money);
            log.info("Your balance is : " + card.getBalance());
            return true;
        }
        return false;
    }
}