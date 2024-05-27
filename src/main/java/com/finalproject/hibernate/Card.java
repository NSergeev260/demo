package com.finalproject.hibernate;

import com.finalproject.card.CardType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name="transportCard")
public class Card {

    @Id
    @Column(name="cardId")
    protected String cardId;
    @Column(name="balance")
    private BigDecimal balance;
    @Column(name="typeOfCard")
    private CardType cardType;
    @Column(name="isBlocked")
    protected boolean blocked;
    @Column(name="documentId")
    private String documentId;

    public Card() {
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    @Override
    public String toString() {
        return "Card{" +
            "cardId='" + cardId + '\'' +
            ", balance=" + balance +
            ", cardType=" + cardType +
            ", blocked=" + blocked +
            ", documentId='" + documentId + '\'' +
            '}';
    }
}
