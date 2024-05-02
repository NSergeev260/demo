package com.finalproject.card;

import java.math.BigDecimal;
public interface ICard {

    String getCardId();

    BigDecimal getBalance();

    void setBalance(BigDecimal balance);

    CardType getType();

    boolean isBlocked();

    boolean block();

    boolean unblock();
}