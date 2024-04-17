package com.finalproject.transport;

import java.math.BigDecimal;

public class TestTransport {
    public static void main(String[] args) {
        Transport bus = new Bus(BigDecimal.valueOf(20));
        Transport tram = new Tramway(BigDecimal.valueOf(30));
        Transport sub = new Subway(BigDecimal.valueOf(50));
        Transport trol = new Trolleybus(BigDecimal.valueOf(60));

        System.out.println("BUS " + bus.getTravelCost());
        System.out.println("TRAM " + tram.getTravelCost());
        System.out.println("SUB " + sub.getTravelCost());
        System.out.println("TROL " + trol.getTravelCost());


    }
}
