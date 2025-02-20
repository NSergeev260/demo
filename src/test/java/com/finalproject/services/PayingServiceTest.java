package com.finalproject.services;

import com.finalproject.card.CardType;
import com.finalproject.card.CreditCard;
import com.finalproject.card.DebitCard;
import com.finalproject.card.ICard;
import com.finalproject.config.CrudFactory;
import com.finalproject.crudmethods.ICardCrud;
import com.finalproject.crudmethods.IHistoryCrud;
import com.finalproject.history.Operation;
import com.finalproject.jdbc.ConnectionToDB;
import com.finalproject.transport.Transport;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Optional;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class PayingServiceTest {

    static MockedStatic<ConnectionToDB> mockedStatic = mockStatic(ConnectionToDB.class);

    @Mock
    private CrudFactory crudFactory;
    @Mock
    private CardService cardService;
    @Mock
    private ICardCrud crudMethodsCard;
    @Mock
    private IHistoryCrud crudMethodsHistory;

    private PayingService payingService;

    @BeforeEach
    void setUp() {
        Mockito.when(crudFactory.getICardCrud()).thenReturn(crudMethodsCard);
        Mockito.when(crudFactory.getIHistoryCrud()).thenReturn(crudMethodsHistory);
        payingService = new PayingService(cardService, crudFactory);
    }

    @AfterAll
    static void tearDown() {
        mockedStatic.close();
    }

    @Test
    void methodShouldPayBlockedCardTest() {
        CreditCard creditCard = getCreditCard(true);
        Mockito.when(cardService.findCardById("1"))
            .thenReturn(Optional.of(creditCard));

        String balance = payingService.payMoney("1",
            Transport.SUBWAY, "Rabbit");
       Assertions.assertEquals("Check cardId, please", balance);
    }

    @Test
    void methodShouldPayTest() {
        CreditCard creditCard = getCreditCard(false);
        Mockito.when(cardService.findCardById("1"))
            .thenReturn(Optional.of(creditCard));
        BigDecimal balanceAfterPay = BigDecimal.valueOf(51);
        Mockito.when(crudMethodsCard.updateCard(creditCard.getCardId(),
            balanceAfterPay, creditCard.isBlocked(),
                creditCard.getDocumentId())).thenReturn(1);

        String balance = payingService.payMoney("1",
            Transport.SUBWAY, "Rabbit");

        Assertions.assertEquals(String.valueOf(balanceAfterPay),balance);

        Mockito.verify(crudMethodsHistory).insertHistory(creditCard,
            String.valueOf(Operation.PAY),
            true, Transport.SUBWAY.getTripCost(), "Rabbit");
    }

    @Test
    void methodDoNotShouldPayTest() {
        CreditCard creditCard = getPoorCreditCard(false);
        Mockito.when(cardService.findCardById("1"))
            .thenReturn(Optional.of(creditCard));
        Mockito.when(crudMethodsCard.updateCard(creditCard.getCardId(),
                creditCard.getBalance(), true, creditCard.getDocumentId()))
            .thenReturn(0);

        String balance = payingService.payMoney("1",
            Transport.SUBWAY, "Rabbit");
        Mockito.verify(crudMethodsHistory).insertHistory(creditCard,
            String.valueOf(Operation.PAY),
            false, Transport.SUBWAY.getTripCost(), "Rabbit");
    }
    @Test
    void methodShouldPutMoneyOnUnBlockedCardTest() {
        CreditCard creditCard = getCreditCard(false);
        Mockito.when(cardService.findCardById("1"))
            .thenReturn(Optional.of(creditCard));
        BigDecimal balanceAfterPut = BigDecimal.valueOf(400);
        Mockito.when(crudMethodsCard.updateCard(creditCard.getCardId(),
                balanceAfterPut, creditCard.isBlocked(), creditCard.getDocumentId())).
            thenReturn(1);

        BigDecimal addedMoney = BigDecimal.valueOf(300);
        String balance = payingService.putMoney(creditCard.getCardId(), addedMoney, "Rabbit");
        Assertions.assertEquals("400", balance);
        Mockito.verify(crudMethodsHistory).insertHistory(creditCard,
            String.valueOf(Operation.PUT),
            true, addedMoney, "Rabbit");
    }

    @Test
    void methodShouldPutMoneyOnBlockedCardTest() {
        CreditCard creditCard = getCreditCard(true);
        Mockito.when(cardService.findCardById("1"))
            .thenReturn(Optional.of(creditCard));
        BigDecimal balanceAfterPut = BigDecimal.valueOf(600);
        Mockito.when(crudMethodsCard.updateCard(creditCard.getCardId(),
            balanceAfterPut, false, creditCard.getDocumentId())).thenReturn(1);

        BigDecimal addedMoney = BigDecimal.valueOf(500);
        String balance = payingService.putMoney("1", addedMoney, "Rabbit");
        Assertions.assertEquals("600", balance);
        Mockito.verify(crudMethodsHistory).insertHistory(creditCard,
            String.valueOf(Operation.PUT),
            true, addedMoney, "Rabbit");
    }

    @Test
    void methodShouldGetBalanceTest() {
        CreditCard creditCard = getCreditCard(false);
        Mockito.when(cardService.findCardById("1"))
            .thenReturn(Optional.of(creditCard));

        String balance = payingService.getBalance("1", "Rabbit");
        Assertions.assertEquals("100", balance);
    }

    @Test
    void methodShouldGetInfoCreditCardTest() {
        CreditCard creditCard = getCreditCard(false);
        Mockito.when(cardService.findCardById("1"))
            .thenReturn(Optional.of(creditCard));

        ICard card = payingService.getCardInfo("1");
        Assertions.assertEquals(creditCard, card);
    }

    @Test
    void methodShouldGetInfoDebitCardTest() {
        DebitCard debitCard = getDebitCard(false);
        Mockito.when(cardService.findCardById("2"))
            .thenReturn(Optional.of(debitCard));

        ICard card = payingService.getCardInfo("2");
        Assertions.assertEquals(debitCard, card);
    }

    @Test
    void methodShouldInsertNewCreditCardTest() {
        ICard newCard = payingService.insertNewCard(CardType.CREDIT, "Rabbit");
        ArgumentCaptor<ICard> cardCaptor = ArgumentCaptor.forClass(ICard.class);
        Mockito.verify(crudMethodsCard).insertCard(cardCaptor.capture());
        cardCaptor.getAllValues().forEach(card -> {
            Assertions.assertNotNull(card);
            Assertions.assertNotNull(card.getCardId());
            Assertions.assertTrue(card.getBalance().signum() >= 0);
            Assertions.assertNotNull(card.getType());
            Assertions.assertFalse((card.isBlocked()));
        });
        Mockito.verify(crudMethodsHistory).insertHistory(newCard,
            String.valueOf(Operation.INSERT),
            false, new BigDecimal(0), "Rabbit");
    }

    @Test
    void methodShouldInsertNewDebitCardTest() {
        ICard newCard = payingService.insertNewCard(CardType.DEBIT, "Rabbit");
        ArgumentCaptor<ICard> cardCaptor = ArgumentCaptor.forClass(ICard.class);
        Mockito.verify(crudMethodsCard).insertCard(cardCaptor.capture());
        cardCaptor.getAllValues().forEach(card -> {
            Assertions.assertNotNull(card);
            Assertions.assertNotNull(card.getCardId());
            Assertions.assertTrue(card.getBalance().signum() >= 0);
            Assertions.assertNotNull(card.getType());
            Assertions.assertFalse((card.isBlocked()));
        });
        Mockito.verify(crudMethodsHistory).insertHistory(newCard,
            String.valueOf(Operation.INSERT),
            false, new BigDecimal(0), "Rabbit");
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
