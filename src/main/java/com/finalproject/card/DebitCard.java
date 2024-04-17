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
    Transport transport;

    public DebitCard(String cardID) {
        this.cardID = cardID;
    }

    public void cardID() {
        cardID = UUID.randomUUID().toString();
    }

    @Override
    public BigDecimal pay(BigDecimal cost) {
        balance = new BigDecimal(String.valueOf(getBalance())).subtract(cost);
        log.info("You pay successful: " + cost);
        return balance;
    }

    @Override
    public BigDecimal putMoney(BigDecimal money) {
        balance = new BigDecimal(String.valueOf(getBalance())).add(money);
        log.info("You balance is : " + money);
        return balance;
    }

    @Override
    public BigDecimal displayBalance() {
        log.info("Yours balance is " + getBalance());
        return getBalance();
    }
}
