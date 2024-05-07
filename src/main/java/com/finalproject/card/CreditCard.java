package com.finalproject.card;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Slf4j
@Entity
public class CreditCard extends AbstractCard {

    @Column(name = "cutOffBankDept")
    private BigDecimal cutOffBankDept;

    @Column(name = "documentId")
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
