package com.finalproject.services;

import com.finalproject.card.CreditCard;
import com.finalproject.card.DebitCard;
import com.finalproject.card.ICard;
import com.finalproject.jdbc.CrudMethodsCard;
import com.finalproject.jdbc.CrudMethodsHistory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class CardServiceTest {

    @InjectMocks
    private CardService cardService;
    @Mock
    private CrudMethodsCard crudMethodsCard;
    @Mock
    private CrudMethodsHistory crudMethodsHistory;

    @Test
    void CreditCardShouldBeFindByIdTest() {
        CreditCard creditCard = new CreditCard("1", BigDecimal.valueOf(100), false, "1a");
        Mockito.when(crudMethodsCard.getCard("1")).thenReturn(creditCard);
        ICard card = crudMethodsCard.getCard("1");
        Assertions.assertEquals(creditCard, card);
        System.out.println(card);
    }

    @Test
    void DebitCardShouldBeFindByIdTest() {
        DebitCard debitCard = new DebitCard("2", BigDecimal.valueOf(200), false);
        Mockito.when(crudMethodsCard.getCard("2")).thenReturn(debitCard);
        ICard card = crudMethodsCard.getCard("2");
        Assertions.assertEquals(debitCard, card);
        System.out.println(card);
    }

    @Test
    void methodShouldGetIsBlocked() {
        CreditCard creditCard = new CreditCard("1", BigDecimal.valueOf(100), true, "1a");
        Mockito.when(crudMethodsCard.getCard("1")).thenReturn(creditCard);
        ICard card = crudMethodsCard.getCard("1");
        Assertions.assertEquals(creditCard, card);
        System.out.println(card);

        Mockito.when(card.isBlocked()).thenReturn(true);
        boolean isBlocked = card.isBlocked();
        Assertions.assertEquals(true, isBlocked);
        System.out.println(isBlocked);
    }


    @Test
    void cardShouldBeBlockedTest() {
        CreditCard creditCard = new CreditCard("1", BigDecimal.valueOf(100), false, "1a");
        Mockito.when(crudMethodsCard.getCard("1")).thenReturn(creditCard);
        ICard card = crudMethodsCard.getCard("1");
        Assertions.assertEquals(creditCard, card);
        Mockito.when(cardService.block("1", "Rabbit")).thenReturn("true");
        Assertions.assertEquals("true", "true");

        Mockito.doNothing().when(crudMethodsHistory).insertHistory(card, "BLOCK", true, null, "Rabbit");
//        crudMethodsHistory.insertHistory(creditCard, "BLOCK", true, null, "Rabbit");
        Mockito.verify(crudMethodsHistory).insertHistory(card, "BLOCK", true, null, "Rabbit");
    }
}
