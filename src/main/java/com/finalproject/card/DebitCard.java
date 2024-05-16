package com.finalproject.card;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class DebitCard extends AbstractCard {

    public DebitCard(String cardId, BigDecimal balance, CardType typeOfCard, boolean isBlocked) {
        super(cardId, balance, typeOfCard, isBlocked);
//        log.info("cardId: {}", cardId);
    }

    public DebitCard(BigDecimal balance, CardType typeOfCard, boolean isBlocked) {
        super(balance, typeOfCard, isBlocked);
        cardId = UUID.randomUUID().toString();
//        log.info("cardId: {}", cardId);
    }

    @Override
    public CardType getType() {
        return CardType.DEBIT;
    }

    @Override
    public String toString() {
        return "DebitCard{" +
            "cardId=" + getCardId() + ", " + '\'' +
            "balance= " + getBalance() + ", " + '\'' +
            "cardType= " + getType() + ", "  + '\'' +
            "isBlocked= " + isBlocked() + ", "  + '\'' +
            '}';
    }
}
