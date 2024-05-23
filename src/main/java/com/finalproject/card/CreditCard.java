package com.finalproject.card;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Slf4j
public class CreditCard extends AbstractCard {

    public static final BigDecimal CUT_OFF_BANK_DEPT = new BigDecimal(100);
    private String documentId;

    public CreditCard(String cardId, BigDecimal balance, boolean isBlocked, String documentId) {
        super(cardId, balance, CardType.CREDIT, isBlocked);
        this.documentId = documentId;
    }

    public CreditCard(BigDecimal balance, boolean isBlocked, String documentId) {
        super(balance, CardType.CREDIT, isBlocked);
        cardId = UUID.randomUUID().toString();
        this.documentId = documentId;
    }

    @Override
    public CardType getType() {
        return CardType.CREDIT;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
            "cardId= " + getCardId() + ", " +
            "balance= " + getBalance() + ", " +
            "cardType= " + getType() + ", " +
            "isBlocked= " + isBlocked() + ", " +
            "documentId= " + documentId + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditCard that = (CreditCard) o;

        return Objects.equals(documentId, that.documentId);
    }

    @Override
    public int hashCode() {
        return documentId != null ? documentId.hashCode() : 0;
    }
}
