package com.finalproject.services;

import static org.mockito.Mockito.mockStatic;

import com.finalproject.card.CreditCard;
import com.finalproject.card.DebitCard;
import com.finalproject.card.ICard;
import com.finalproject.jdbc.ConnectionToDB;
import com.finalproject.jdbc.CrudMethodsCard;
import com.finalproject.jdbc.CrudMethodsHistory;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CardServiceTest {

    static MockedStatic<ConnectionToDB> mockedStatic = mockStatic(ConnectionToDB.class);

    @InjectMocks
    private CardService cardService;
    @Mock
    private CrudMethodsCard crudMethodsCard;
    @Mock
    private CrudMethodsHistory crudMethodsHistory;

    @AfterAll
    static void tearDown() {
        mockedStatic.close();
    }

    @Test
    void creditCardShouldBeFoundByIdTest() {
        CreditCard creditCard = getCreditCard(false);
        Mockito.when(crudMethodsCard.getCard("1")).thenReturn(creditCard);
        Optional<ICard> card = cardService.findCardById("1");
        Assertions.assertTrue(card.isPresent());
        Assertions.assertEquals(creditCard, card.get());
    }

    @Test
    void debitCardShouldBeFoundByIdTest() {
        DebitCard debitCard = new DebitCard("2", BigDecimal.valueOf(200), false);
        Mockito.when(crudMethodsCard.getCard("2")).thenReturn(debitCard);
        Optional<ICard> card = cardService.findCardById("2");
        Assertions.assertTrue(card.isPresent());
        Assertions.assertEquals(debitCard, card.get());
    }

    @Test
    void methodShouldGetBlockedStatusTest() {
        CreditCard creditCard = getCreditCard(true);
        Mockito.when(crudMethodsCard.getCard("1")).thenReturn(creditCard);
        String isBlocked = cardService.isBlocked("1");
        Assertions.assertEquals("true", isBlocked);
    }


    @Test
    void cardShouldBeBlockedTest() {
        CreditCard creditCard = getCreditCard(false);
        Mockito.when(crudMethodsCard.getCard("1")).thenReturn(creditCard);
        ICard card = crudMethodsCard.getCard("1");
        Assertions.assertEquals(creditCard, card);

        Mockito.doReturn(true).when(crudMethodsCard.getCard("1")).block();
//        Mockito.when(cardService.block("1", "Rabbit")).thenReturn("true");
        boolean blocked = crudMethodsCard.getCard("1").isBlocked();
        Assertions.assertEquals(true, blocked);

//        Mockito.verify(crudMethodsHistory).insertHistory(card, "BLOCK", true, null, "Rabbit");
    }


    private static CreditCard getCreditCard(boolean isBlocked) {
        return new CreditCard("1", BigDecimal.valueOf(100), isBlocked, "1a");
    }
}
