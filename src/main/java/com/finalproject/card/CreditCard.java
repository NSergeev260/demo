package com.finalproject.card;

import com.finalproject.transport.Transport;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Slf4j
public class CreditCard implements UsageCard {

    private String cardID;
    private boolean cardActive;
    private BigDecimal balance;
    private boolean cutOffBankDept;
    Transport transport;

    public CreditCard(String cardID) {
        this.cardID = cardID;
    }

    public void cardID() {
        cardID = UUID.randomUUID().toString();
    }

    @Override
    public BigDecimal pay(BigDecimal cost) {
        testOfStatusCard();
        if(cardActive) {
            setBalance(new BigDecimal(String.valueOf(getBalance())).subtract(cost));
            log.info("You pay successful: " + cost);
            log.info("Your balance is : " + displayBalance());
            return balance;
        } else {
            log.info("Not enough for traveling. Put money on card, please");
        }
        return balance;
    }

    @Override
    public BigDecimal putMoney(BigDecimal money) {
        setBalance(new BigDecimal(String.valueOf(getBalance())).add(money));
        log.info("You put: " + money);
        log.info("Your balance is : " + displayBalance());
        return balance;
    }

    @Override
    public BigDecimal displayBalance() {
        log.info("Your balance is " + getBalance());
        return getBalance();
    }

    public void testOfStatusCard() {
        if((balance.compareTo(BigDecimal.ZERO) == 0) ||
            (balance.compareTo(BigDecimal.ZERO) < 0)) {
            cardActive = false;
        } else {
            cardActive = true;
        }
    }
}
