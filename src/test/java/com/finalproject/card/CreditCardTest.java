package com.finalproject.card;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CreditCardTest {

    @Test
    void twoCardsEquals() {
        CreditCard creditCard = new CreditCard("1", BigDecimal.valueOf(200), false, "1a");
        CreditCard anotherCreditCard = new CreditCard("1", BigDecimal.valueOf(200), false, "1a");
        boolean isTheSame = creditCard.equals(anotherCreditCard);
        Assertions.assertEquals(true, isTheSame);
    }

    @Test
    void twoCardsNotEquals() {
        CreditCard creditCard = new CreditCard("1", BigDecimal.valueOf(200), false, "1a");
        CreditCard anotherCreditCard = new CreditCard("1", BigDecimal.valueOf(100000), false, "1a");
        boolean isTheSame = creditCard.equals(anotherCreditCard);
        Assertions.assertEquals(false, isTheSame);
    }

    @Test
    void twoCardsEqualsByHashCode() {
        CreditCard creditCard = new CreditCard("1", BigDecimal.valueOf(200), false, "1a");
        CreditCard anotherCreditCard = new CreditCard("1", BigDecimal.valueOf(200), false, "1a");
        int resultCreditCard  = creditCard.hashCode();
        int resultAnotherCreditCard = anotherCreditCard.hashCode();
        boolean isTheSame = (resultCreditCard == resultAnotherCreditCard);
        Assertions.assertEquals(true, isTheSame);

    }

    @Test
    void twoCardsNotEqualsByHashCode() {
        CreditCard creditCard = new CreditCard("1", BigDecimal.valueOf(200), false, "1a");
        CreditCard anotherCreditCard = new CreditCard("1", BigDecimal.valueOf(100000), false, "1a");
        int resultCreditCard  = creditCard.hashCode();
        int resultAnotherCreditCard = anotherCreditCard.hashCode();
        boolean isTheSame = (resultCreditCard == resultAnotherCreditCard);
        Assertions.assertEquals(false, isTheSame);

    }
}
