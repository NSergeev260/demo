package com.finalproject.card;

import com.finalproject.transport.Transport;
import com.finalproject.transport.TransportCost;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Slf4j
public class DebitCard implements UsageCard {

    private String cardId;
    private BigDecimal balance;

    public DebitCard() {
        this.cardId = getCardId();
    }

    @Override
    public String getCardId() {
        cardId = UUID.randomUUID().toString() + "-D";
        return cardId;
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }
}
