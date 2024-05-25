package com.finalproject.card;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class DebitCardTest {
    @Test
    void twoCardsEquals() {
        DebitCard debitCard = new DebitCard("1", BigDecimal.valueOf(200), false);
        DebitCard anotherDebitCard = new DebitCard("1", BigDecimal.valueOf(200), false);
        boolean isTheSame = debitCard.equals(anotherDebitCard);
        Assertions.assertEquals(true, isTheSame);
    }

    @Test
    void twoCardsNotEquals() {
        DebitCard debitCard = new DebitCard("1", BigDecimal.valueOf(200), false);
        DebitCard anotherDebitCard = new DebitCard("1", BigDecimal.valueOf(100000), false);
        boolean isTheSame = debitCard.equals(anotherDebitCard);
        Assertions.assertEquals(false, isTheSame);
    }
}
