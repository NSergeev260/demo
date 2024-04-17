package com.finalproject.card;

import java.math.BigDecimal;

public interface UsageCard {

    public BigDecimal pay(BigDecimal cost);

    public BigDecimal putMoney(BigDecimal money);

    public BigDecimal displayBalance();
}