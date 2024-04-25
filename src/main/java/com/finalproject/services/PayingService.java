package com.finalproject.services;

import com.finalproject.card.ICard;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Setter
@Component
public class PayingService {

    private static BigDecimal cutOffBankDept = new BigDecimal(100);

    public boolean pay(ICard card, BigDecimal cost) {
        if (!card.isBlocked()) {
            card.setBalance(new BigDecimal(String.valueOf(card.getBalance())).subtract(cost));
            log.info("You pay successful: " + cost);
            log.info("Your balance is : " + card.getBalance());
            return true;
        }
        log.info("Not enough for traveling. Put money on card, please");
        return false;
    }

    public BigDecimal putMoney(ICard card, BigDecimal money) {
        card.setBalance(new BigDecimal(String.valueOf(card.getBalance())).add(money));
        log.info("You put: " + money);
        log.info("Your balance is : " + card.getBalance());
        return card.getBalance();
    }
}
