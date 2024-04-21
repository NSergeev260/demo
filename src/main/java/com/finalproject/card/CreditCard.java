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

    private String cardId;
    private BigDecimal balance;
    private BigDecimal cutOffBankDept;

    public CreditCard() {
        this.cardId = getCardId();
    }

    @Override
    public String getCardId() {
        cardId = UUID.randomUUID().toString();
        return cardId;
    }

    @Override
    public BigDecimal getBalance() {
        log.info("Your balance is " + getBalance());
        return balance;
    }
}
