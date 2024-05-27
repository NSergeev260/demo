package com.finalproject.card;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

class DebitCardTest {

    @Test
    void twoCardsEquals() {
        Assertions.assertEquals(getDebitCard(200), getDebitCard(200));
    }

    @Test
    void twoCardsEqualsByHashCode() {
        Assertions.assertTrue((getDebitCard(200).hashCode() == getDebitCard(200).hashCode()));
    }

    @Test
    void twoCardsNotEquals() {
        Assertions.assertNotEquals(getDebitCard(200), getDebitCard(100_000));
    }

    @Test
    void twoCardsNotEqualsByHashCode() {
        Assertions.assertFalse((getDebitCard(200).hashCode() == getDebitCard(100_000).hashCode()));
    }

    private static DebitCard getDebitCard(int val) {
        return new DebitCard("1", BigDecimal.valueOf(val), false);
    }
}
