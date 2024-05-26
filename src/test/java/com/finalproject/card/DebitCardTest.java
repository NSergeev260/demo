package com.finalproject.card;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

class DebitCardTest {

    @Test
    void twoCardsEquals() {
        DebitCard debitCard = getDebitCard(BigDecimal.valueOf(200));
        DebitCard anotherDebitCard = getDebitCard(BigDecimal.valueOf(200));
        boolean isTheSame = debitCard.equals(anotherDebitCard);
        Assertions.assertTrue(isTheSame);
    }

    @Test
    void twoCardsEqualsByHashCode() {
        DebitCard debitCard = getDebitCard(BigDecimal.valueOf(200));
        DebitCard anotherDebitCard = getDebitCard(BigDecimal.valueOf(200));
        int resultDebitCard  = debitCard.hashCode();
        int resultAnotherDebitCard = anotherDebitCard.hashCode();
        boolean isTheSame = (resultDebitCard == resultAnotherDebitCard);
        Assertions.assertTrue(isTheSame);
    }

    @Test
    void twoCardsNotEquals() {
        DebitCard debitCard = getDebitCard(BigDecimal.valueOf(200));
        DebitCard anotherDebitCard = getDebitCard(BigDecimal.valueOf(100_000));
        boolean isTheSame = debitCard.equals(anotherDebitCard);
        Assertions.assertFalse(isTheSame);
    }

    @Test
    void twoCardsNotEqualsByHashCode() {
        DebitCard debitCard = getDebitCard(BigDecimal.valueOf(200));
        DebitCard anotherDebitCard = getDebitCard(BigDecimal.valueOf(100_000));
        int resultDebitCard  = debitCard.hashCode();
        int resultAnotherDebitCard = anotherDebitCard.hashCode();
        boolean isTheSame = (resultDebitCard == resultAnotherDebitCard);
        Assertions.assertFalse(isTheSame);
    }

    private static DebitCard getDebitCard(BigDecimal balance) {
        DebitCard debitCard = new DebitCard("1", balance, false);
        return debitCard;
    }
}
