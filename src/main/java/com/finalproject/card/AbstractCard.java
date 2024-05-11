package com.finalproject.card;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
@Getter
public abstract class AbstractCard implements ICard {

    protected String cardId;
    @Setter
    private BigDecimal balance;
    private CardType typeOfCard;
    protected boolean blocked;

    public AbstractCard(String cardId, BigDecimal balance, CardType typeOfCard, boolean blocked) {
        this.cardId = cardId;
        this.balance = balance;
        this.typeOfCard = typeOfCard;
        this.blocked = blocked;
    }

    public AbstractCard(BigDecimal balance, CardType typeOfCard, boolean blocked) {
        this.balance = balance;
        this.typeOfCard = typeOfCard;
        this.blocked = blocked;
    }

    public abstract CardType getType();

    public void block() {
        blocked = true;
    }

    public void unblock() {
        blocked = false;
    }
}
