package com.finalproject.transport;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public enum TransportEnum {

    SUBWAY(49),
    BUS(44),
    TROLLEYBUS(43),
    TRAMWAY(42);

    private final BigDecimal tripCost;

    TransportEnum(int tripCost) {
        this.tripCost = BigDecimal.valueOf(tripCost);
    }
}

