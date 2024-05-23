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

    public DebitCard(String cardId, BigDecimal balance, boolean isBlocked) {
        super(cardId, balance, CardType.DEBIT, isBlocked);
    }

    public DebitCard(BigDecimal balance, boolean isBlocked) {
        super(balance, CardType.DEBIT, isBlocked);
        cardId = UUID.randomUUID().toString();
    }

    @Override
    public CardType getType() {
        return CardType.DEBIT;
    }

    @Override
    public String toString() {
        return "DebitCard{" +
            "cardId= " + getCardId() + ", " +
            "balance= " + getBalance() + ", " +
            "cardType= " + getType() + ", " +
            "isBlocked= " + isBlocked() + '}';
    }


}
