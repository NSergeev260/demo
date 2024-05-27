package com.finalproject.card;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

class CreditCardTest {

    @Test
    void twoCardsEquals() {
        Assertions.assertEquals(getCreditCard(200), getCreditCard(200));
    }

    @Test
    void twoCardsEqualsByHashCode() {
        Assertions.assertTrue((getCreditCard(200).hashCode() == getCreditCard(200).hashCode()));
    }

    @Test
    void twoCardsNotEquals() {
        Assertions.assertNotEquals(getCreditCard(100_000), getCreditCard(200));
    }

    @Test
    void twoCardsNotEqualsByHashCode() {
        Assertions.assertFalse((getCreditCard(200).hashCode() == getCreditCard(100_000).hashCode()));
    }

    private static CreditCard getCreditCard(int val) {
        return new CreditCard("1", BigDecimal.valueOf(val), false, "1a");
    }
}
