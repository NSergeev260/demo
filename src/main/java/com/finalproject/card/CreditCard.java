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

    public CreditCard() {
        cardId = UUID.randomUUID().toString();
        log.info("cardId: {}", cardId);
    }

    public CreditCard(String cardId, String documentId) {
        this.cardId = cardId;
        this.documentId = documentId;
        log.info("cardId: {}, documentId: {}", cardId, documentId);
    }

    @Override
    public CardType getType() {
        return CardType.CREDIT;
    }

}
