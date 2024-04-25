package com.finalproject.card;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public interface ICard {

    String getCardId();

    BigDecimal getBalance();

    void setBalance(BigDecimal balance);

    CardType getType();

    boolean isBlocked();

    void block();

    void unblock();
}