package com.finalproject.card;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Slf4j
public class CreditCard extends AbstractCard {

    private BigDecimal cutOffBankDept;

    //    private String name;
//    private String surname;

    public CreditCard() {
        cardId = UUID.randomUUID().toString();
        log.info("cardId: {}", cardId);
    }

    public CreditCard(String cardId) {
        this.cardId = cardId;
        log.info("cardId: {}", cardId);
    }

    @Override
    public CardType getType() {
        return CardType.CREDIT;
    }

//    public CreditCard(String name, String surname) {
//        this.cardId = getCardId();
//        this.name = name;
//        this.surname = surname;
//    }
}
