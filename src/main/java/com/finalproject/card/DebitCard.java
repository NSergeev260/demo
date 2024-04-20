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
public class DebitCard implements UsageCard {

    private String cardID;
    private BigDecimal balance;
    private boolean cardActive;
    Transport transport;

    public DebitCard(String cardID) {
        this.cardID = cardID;
    }

    public void cardID() {
        cardID = UUID.randomUUID().toString();
    }

    @Override
    public BigDecimal pay(BigDecimal cost) {
        if(!cardActive) {
            balance = new BigDecimal(String.valueOf(getBalance())).subtract(cost);
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
        balance = new BigDecimal(String.valueOf(getBalance())).add(money);
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
        if(BigDecimal.valueOf(balance) < BigDecimal.valueOf(0)) {
            cardActive = false;
        } else {
            cardActive = true;
        }
    }
}
