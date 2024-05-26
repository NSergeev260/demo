package com.finalproject.card;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

class CreditCardTest {

    @Test
    void twoCardsEquals() {
        CreditCard creditCard = getCreditCard(BigDecimal.valueOf(200));
        CreditCard anotherCreditCard = getCreditCard(BigDecimal.valueOf(200));
        boolean isTheSame = creditCard.equals(anotherCreditCard);
        Assertions.assertTrue(isTheSame);
    }

    @Test
    void twoCardsEqualsByHashCode() {
        CreditCard creditCard = getCreditCard(BigDecimal.valueOf(200));
        CreditCard anotherCreditCard = getCreditCard(BigDecimal.valueOf(200));
        int resultCreditCard  = creditCard.hashCode();
        int resultAnotherCreditCard = anotherCreditCard.hashCode();
        boolean isTheSame = (resultCreditCard == resultAnotherCreditCard);
        Assertions.assertTrue(isTheSame);
    }

    @Test
    void twoCardsNotEquals() {
        CreditCard creditCard = getCreditCard(BigDecimal.valueOf(200));
        CreditCard anotherCreditCard = getCreditCard(BigDecimal.valueOf(100_000));
        boolean isTheSame = creditCard.equals(anotherCreditCard);
        Assertions.assertFalse(isTheSame);
    }

    @Test
    void twoCardsNotEqualsByHashCode() {
        CreditCard creditCard = getCreditCard(BigDecimal.valueOf(200));
        CreditCard anotherCreditCard = getCreditCard(BigDecimal.valueOf(100_000));
        int resultCreditCard  = creditCard.hashCode();
        int resultAnotherCreditCard = anotherCreditCard.hashCode();
        boolean isTheSame = (resultCreditCard == resultAnotherCreditCard);
        Assertions.assertFalse(isTheSame);
    }

    private static CreditCard getCreditCard(BigDecimal balance) {
        CreditCard creditCard = new CreditCard("1", balance, false, "1a");
        return creditCard;
    }
}
