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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractCard that = (AbstractCard) o;

        if (blocked != that.blocked) return false;
        if (!cardId.equals(that.cardId)) return false;
        if (!balance.equals(that.balance)) return false;
        return cardType == that.cardType;
    }

    @Override
    public int hashCode() {
        int result = cardId.hashCode();
        result = 31 * result + balance.hashCode();
        result = 31 * result + (blocked ? 1 : 0);
        result = 31 * result + cardType.hashCode();
        return result;
    }
}