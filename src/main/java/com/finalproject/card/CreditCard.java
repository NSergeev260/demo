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
        if (!super.equals(o)) return false;
        if (!documentId.equals(that.documentId)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + documentId.hashCode();
        return result;
    }
}