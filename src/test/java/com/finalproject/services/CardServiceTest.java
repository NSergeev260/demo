package com.finalproject.services;

import com.finalproject.card.CreditCard;
import com.finalproject.card.DebitCard;
import com.finalproject.card.ICard;
import com.finalproject.config.ICardCrudFactory;
import com.finalproject.hibernate.CrudMethodsCardHibernate;
import com.finalproject.hibernate.CrudMethodsHistoryHibernate;
import com.finalproject.hibernate.ICardCrud;
import com.finalproject.hibernate.IHistoryCrud;
import com.finalproject.history.Operation;
import com.finalproject.jdbc.ConnectionToDB;
import com.finalproject.jdbc.CrudMethodsCardJDBC;
import com.finalproject.jdbc.CrudMethodsHistoryJDBC;
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
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class CardServiceTest {

    static MockedStatic<ConnectionToDB> mockedStatic = mockStatic(ConnectionToDB.class);

    @Mock
    private ICardCrud crudMethodsCard;
    @Mock
    private IHistoryCrud crudMethodsHistory;
    @InjectMocks
    private CardService cardService;

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
        DebitCard debitCard = getDebitCard(false);
        Mockito.when(crudMethodsCard.getCard("2")).thenReturn(debitCard);

        Optional<ICard> card = cardService.findCardById("2");
        Assertions.assertTrue(card.isPresent());
        Assertions.assertEquals(debitCard, card.get());
    }

    @Test
    void methodShouldGetBlockedStatusTrueTest() {
        CreditCard creditCard = getCreditCard(true);
        Mockito.when(crudMethodsCard.getCard("1")).thenReturn(creditCard);

        String isBlocked = cardService.isBlocked("1");
        Assertions.assertEquals("true", isBlocked);
    }

    @Test
    void methodShouldGetBlockedStatusFalseTest() {
        CreditCard creditCard = getCreditCard(false);
        Mockito.when(crudMethodsCard.getCard("1")).thenReturn(creditCard);

        String isBlocked = cardService.isBlocked("1");
        Assertions.assertEquals("false", isBlocked);
    }

    @Test
    void cardShouldBeBlockedTest() {
        CreditCard creditCard = getCreditCard(false);
        Mockito.when(crudMethodsCard.getCard("1")).thenReturn(creditCard);
        Mockito.when(crudMethodsCard.updateCard(creditCard)).thenReturn(1);

        String blocked = cardService.block("1", "Rabbit");
        Assertions.assertEquals("true", blocked);
        Mockito.verify(crudMethodsHistory).insertHistory(creditCard,
            String.valueOf(Operation.BLOCK), true, null, "Rabbit");
    }

    @Test
    void cardShouldBeUnBlockedTest() {
        DebitCard debitCard = getDebitCard(true);
        Mockito.when(crudMethodsCard.getCard("2")).thenReturn(debitCard);
        Mockito.when(crudMethodsCard.updateCard(debitCard)).thenReturn(1);

        String unblocked = cardService.unblock("2", "Rabbit");
        Assertions.assertEquals("true", unblocked);
        Mockito.verify(crudMethodsHistory).insertHistory(debitCard,
            String.valueOf(Operation.UNBLOCK), true, null, "Rabbit");

    }

    private static CreditCard getCreditCard(boolean isBlocked) {
        return new CreditCard("1", BigDecimal.valueOf(100), isBlocked, "1a");
    }

    private static DebitCard getDebitCard(boolean isBlocked) {
        return new DebitCard("2", BigDecimal.valueOf(200), isBlocked);
    }
}
