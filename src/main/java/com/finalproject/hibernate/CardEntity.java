package com.finalproject.hibernate;

import com.finalproject.card.CardType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "transportCard")
public class CardEntity {

    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "cardId")
    private UUID cardId;
    @Column(name = "balance")
    private BigDecimal balance;
    @Column(name = "typeOfCard")
    @Enumerated(EnumType.STRING)
    private CardType cardType;
    @Column(name = "isBlocked")
    private String isBlocked;
    @Column(name = "documentId")
    private String documentId;

    public CardEntity() {
    }

    public CardEntity(String cardId, BigDecimal balance, CardType cardType,
                      boolean isBlocked, String documentId) {
        this.cardId = UUID.fromString(cardId);
        this.balance = balance;
        this.cardType = cardType;
        this.isBlocked = String.valueOf(isBlocked);
        this.documentId = documentId;
    }

    public String getCardId() {
        return cardId.toString();
    }

    public void setCardId(String cardId) {
        this.cardId = UUID.fromString(cardId);
    }

    @Override
    public String toString() {
        return "Card{" +
            "cardId='" + cardId +
            ", balance=" + balance +
            ", cardType=" + cardType +
            ", blocked=" + isBlocked +
            ", documentId='" + documentId + '}';
    }
}
