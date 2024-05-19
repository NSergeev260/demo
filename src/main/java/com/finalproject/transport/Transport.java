package com.finalproject.transport;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public enum Transport {

    SUBWAY(49),
    BUS(44),
    TROLLEYBUS(43),
    TRAMWAY(42);

    private final BigDecimal tripCost;

    Transport(int tripCost) {
        this.tripCost = BigDecimal.valueOf(tripCost);
    }
}