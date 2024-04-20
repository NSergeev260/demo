package com.finalproject.transport;

import java.math.BigDecimal;

public enum TransportCost {

    SUBWAY(BigDecimal.valueOf(49)),
    BUS(BigDecimal.valueOf(44)),
    TROLLEYBUS(BigDecimal.valueOf(44)),
    TRAMWAY(BigDecimal.valueOf(44));

    private BigDecimal travelCost;

    TransportCost(BigDecimal travelCost) {
        this.travelCost = travelCost;
    }

    public BigDecimal getTravelCOst() {
        return travelCost;
    }
}

