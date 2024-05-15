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

    public static final BigDecimal CUT_OFF_BANK_DEPT = new BigDecimal(100);
    private String documentId;

    public CreditCard(String cardId, BigDecimal balance, CardType typeOfCard, boolean isBlocked, String documentId) {
        super(cardId, balance, typeOfCard, isBlocked);
        this.documentId = documentId;
//        log.info("cardId: {}", cardId);
    }

    public CreditCard(BigDecimal balance, CardType typeOfCard, boolean isBlocked, String documentId) {
        super(balance, typeOfCard, isBlocked);
        cardId = UUID.randomUUID().toString();
        this.documentId = documentId;
//        log.info("cardId: {}", cardId);
    }

    @Override
    public CardType getType() {
        return CardType.CREDIT;
    }

}
