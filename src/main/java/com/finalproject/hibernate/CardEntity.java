package com.finalproject.hibernate;

import com.finalproject.card.CardType;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name="transportCard")
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name="cardId")
    private UUID cardId;
    @Column(name="balance")
    private BigDecimal balance;
    @Column(name="typeOfCard")
    @Enumerated(EnumType.STRING)
    private CardType cardType;
    @Column(name="isBlocked")
    private boolean blocked;
    @Column(name="documentId")
    private String documentId;

    public CardEntity() {
    }

    public CardEntity(String cardId, BigDecimal balance, CardType cardType, boolean blocked, String documentId) {
        this.cardId = UUID.fromString(cardId);
        this.balance = balance;
        this.cardType = cardType;
        this.blocked = blocked;
        this.documentId = documentId;
    }

    public String getCardId() {
        return cardId.toString();
    }

    public void setCardId(String cardId) {
        this.cardId = UUID.fromString(cardId);
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
