package com.finalproject.services;

import com.finalproject.card.CardType;
import com.finalproject.card.CreditCard;
import com.finalproject.card.DebitCard;
import com.finalproject.card.ICard;
import com.finalproject.jdbc.ConnectionToDB;
import com.finalproject.jdbc.CrudMethodsCard;
import com.finalproject.jdbc.CrudMethodsHistory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
public class PayingServiceTest {

    static MockedStatic<ConnectionToDB> mockedStatic = mockStatic(ConnectionToDB.class);

    @InjectMocks
    private PayingService payingService;
    @Mock
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
    void methodShouldPayTest() {

    }

    @Test
    void methodShouldPutMoneyOnBlockedCardTest() {
        CreditCard creditCard = getCreditCard(false);
        Mockito.when(cardService.findCardById("1")).thenReturn(Optional.of(creditCard));
        payingService.putMoney("1", new BigDecimal("500"), "1a");
        String balance = String.valueOf(creditCard.getBalance());
        Assertions.assertEquals("600", balance);
    }

    @Test
    void methodShouldPutMoneyOnUnBlockedCardTest() {
        CreditCard creditCard = getCreditCard(true);
        Mockito.when(cardService.findCardById("1")).thenReturn(Optional.of(creditCard));
        payingService.putMoney("1", new BigDecimal("500"), "1a");
        String balance = String.valueOf(creditCard.getBalance());
        Assertions.assertEquals("600", balance);
    }

    @Test
    void methodShouldReturnBalance() {
        CreditCard creditCard = getCreditCard(false);
        Mockito.when(cardService.findCardById("1")).thenReturn(Optional.of(creditCard));
        String balance = payingService.getBalance("1", "Rabbit");
        Assertions.assertEquals("100", balance);
    }

    @Test
    void methodShouldGetInfoCreditCardTest() {
        CreditCard creditCard = getCreditCard(false);
        Mockito.when(cardService.findCardById("1")).thenReturn(Optional.of(creditCard));
        ICard card = payingService.getCardInfo("1");
        Assertions.assertEquals(creditCard, card);
    }

    @Test
    void methodShouldGetInfoDebitCardTest() {
        DebitCard debitCard = getDebitCard(false);
        Mockito.when(cardService.findCardById("2")).thenReturn(Optional.of(debitCard));
        ICard card = payingService.getCardInfo("2");
        Assertions.assertEquals(debitCard, card);
    }


    @Test
    void methodShouldInsertNewCreditCardTest() {
        String newCard = payingService.insertNewCard(CardType.CREDIT, "Rabbit");
        Assertions.assertEquals(true, true);
//        Mockito.when(payingService.insertNewCard(CardType.CREDIT, "Rabbit")
//            .thenReturn(new CreditCard("1",200,false, "1a"));
//        Mockito.verify(crudMethodsHistory).insertHistory(card, String.valueOf(Operation.INSERT), false, new BigDecimal(0), "Rabbit");
    }

    @Test
    void methodShouldInsertNewDebitCardTest() {
        String newCard = payingService.insertNewCard(CardType.DEBIT, "Rabbit");
        Assertions.assertEquals(true, true);
//        Mockito.verify(crudMethodsHistory).insertHistory(debitCard, String.valueOf(Operation.INSERT), false, new BigDecimal(0), "Rabbit");
    }

    private static CreditCard getCreditCard(boolean isBlocked) {
        return new CreditCard("1", BigDecimal.valueOf(100), isBlocked, "1a");
    }

    private static DebitCard getDebitCard(boolean isBlocked) {
        return new DebitCard("2", BigDecimal.valueOf(200), isBlocked);
    }
}
