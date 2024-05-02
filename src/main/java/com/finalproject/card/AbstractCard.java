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

    public abstract CardType getType();

    public boolean block() {
        return blocked = true;
    }

    public boolean unblock() {
        return blocked = false;
    }
}
