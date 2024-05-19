package com.finalproject.card;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
@Getter
public abstract class AbstractCard implements ICard {

    protected String cardId;
    @Setter
    private BigDecimal balance;
    protected boolean blocked;
    private final CardType cardType;

    protected AbstractCard(String cardId, BigDecimal balance, CardType cardType, boolean blocked) {
        this.cardId = cardId;
        this.balance = balance;
        this.cardType = cardType;
        this.blocked = blocked;
    }

    protected AbstractCard(BigDecimal balance, CardType cardType, boolean blocked) {
        this.balance = balance;
        this.cardType = cardType;
        this.blocked = blocked;
    }

    public void block() {
        blocked = true;
    }

    public void unblock() {
        blocked = false;
    }
}
