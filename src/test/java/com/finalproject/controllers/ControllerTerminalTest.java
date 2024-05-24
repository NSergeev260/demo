package com.finalproject.controllers;

import com.finalproject.card.CardType;
import com.finalproject.card.CreditCard;
import com.finalproject.card.DebitCard;
import com.finalproject.card.ICard;
import com.finalproject.jdbc.ConnectionToDB;
import com.finalproject.services.PayingService;
import com.finalproject.transport.Transport;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
public class ControllerTerminalTest {

    static MockedStatic<ConnectionToDB> mockedStatic = mockStatic(ConnectionToDB.class);

    @InjectMocks
    private ControllerTerminal controllerTerminal;
    @Mock
    private PayingService payingService;

    @AfterAll
    static void tearDown() {
        mockedStatic.close();
    }

    @Test
    void methodShouldPayTest() {
        CreditCard creditCard = getCreditCard(false);
        Mockito.when(payingService.payMoney("1", Transport.SUBWAY, "Rabbit")).thenReturn("51");
        String balance = controllerTerminal.pay("1", Transport.SUBWAY, "Rabbit");
        Assertions.assertEquals("51", balance);
        Mockito.verify(payingService).payMoney("1", Transport.SUBWAY, "Rabbit");
    }

    @Test
    void methodShouldPutMoneyTest() {
        CreditCard creditCard = getCreditCard(false);
        Mockito.when(payingService.putMoney("1", BigDecimal.valueOf(100), "Rabbit")).thenReturn("200");
        String balance = controllerTerminal.put("1", BigDecimal.valueOf(100), "Rabbit");
        Assertions.assertEquals("200", balance);
        Mockito.verify(payingService).putMoney("1", BigDecimal.valueOf(100), "Rabbit");
    }

    @Test
    void methodShouldGetBalanceTest() {
        CreditCard creditCard = getCreditCard(false);
        Mockito.when(payingService.getBalance("1", "Rabbit")).thenReturn("100");
        String balance = controllerTerminal.balanceOfMoney("1", "Rabbit");
        Assertions.assertEquals("100", balance);
        Mockito.verify(payingService).getBalance("1", "Rabbit");
    }

    @Test
    void methodShouldGetCardInfoTest() {
        CreditCard creditCard = getCreditCard(false);
        Mockito.when(payingService.getCardInfo("1")).thenReturn(creditCard);
        ICard card = controllerTerminal.getInfo("1");
        Assertions.assertEquals(creditCard, card);
        Mockito.verify(payingService).getCardInfo("1");
    }

    @Test
    void methodShouldInsertCardTest() {
        ICard newCard = controllerTerminal.activateCard(CardType.CREDIT, "Rabbit");
        ArgumentCaptor<ICard> cardCaptor = ArgumentCaptor.forClass(ICard.class);
        cardCaptor.getAllValues().forEach(card -> {
            Assertions.assertNotNull(card);
            Assertions.assertNotNull(card.getCardId());
            Assertions.assertTrue(card.getBalance().signum() >= 0);
            Assertions.assertNotNull(card.getType());
            Assertions.assertFalse((card.isBlocked()));
        });
        Mockito.verify(payingService).insertNewCard(CardType.CREDIT, "Rabbit");
    }

    private static CreditCard getCreditCard(boolean isBlocked) {
        return new CreditCard("1", BigDecimal.valueOf(100), isBlocked, "1a");
    }

    private static CreditCard getPoorCreditCard(boolean isBlocked) {
        return new CreditCard("1", BigDecimal.valueOf(-52), isBlocked, "1a");
    }

    private static DebitCard getDebitCard(boolean isBlocked) {
        return new DebitCard("2", BigDecimal.valueOf(200), isBlocked);
    }
}
