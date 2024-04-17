package com.finalproject.transport;

import java.math.BigDecimal;

public abstract class Transport {

    private BigDecimal travelCost;

    public Transport(BigDecimal travelCost) {
        this.travelCost = travelCost;
    }

    public void setTravelCost(BigDecimal travelCost) {
        this.travelCost = travelCost;
    }

    public BigDecimal getTravelCost() {
        return travelCost;
    }
}