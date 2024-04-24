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
//    private String name;
//    private String surname;

    public CreditCard() {
        this.cardId = getCardId();
    }

//    public CreditCard(String name, String surname) {
//        this.cardId = getCardId();
//        this.name = name;
//        this.surname = surname;
//    }

    @Override
    public String getCardId() {
        cardId = UUID.randomUUID().toString() + "-C";
        return cardId;
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }
}
