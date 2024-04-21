package com.finalproject;

import com.finalproject.card.CreditCard;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Getter
@Setter
public class Service {

    private String cardId;
    private boolean cardActive;
    private BigDecimal balance;
    private BigDecimal cutOffBankDept;

    public BigDecimal pay(BigDecimal cost) {
        testOfStatusCard();
        if(cardActive) {
            setBalance(new BigDecimal(String.valueOf(getBalance())).subtract(cost));
            log.info("You pay successful: " + cost);
            log.info("Your balance is : " + getBalance());
            return balance;
        } else {
            log.info("Not enough for traveling. Put money on card, please");
        }
        return balance;
    }


    public BigDecimal putMoney(BigDecimal money) {
        setBalance(new BigDecimal(String.valueOf(getBalance())).add(money));
        log.info("You put: " + money);
        log.info("Your balance is : " + getBalance());
        return balance;
    }


    public BigDecimal getBalance() {
        log.info("Your balance is " + getBalance());
        return getBalance();
    }

    public void testOfStatusCard() {
        BigDecimal creditMoney = new BigDecimal(String.valueOf(getBalance()))
            .add(setCutOffBankDept(String.valueOf(100)));
        if((balance.compareTo(BigDecimal.ZERO) == 0) ||
            (balance.compareTo(BigDecimal.ZERO) < 0)) {
            cardActive = false;
        } else {
            cardActive = true;
        }
    }
}
