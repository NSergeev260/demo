package com.finalproject.controllers;

import com.finalproject.MockData;
import com.finalproject.card.CreditCard;
import com.finalproject.card.DebitCard;
import com.finalproject.jdbc.ConnectionToDB;
import com.finalproject.services.CardService;
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

import static org.mockito.Mockito.mockStatic;


@ExtendWith(MockitoExtension.class)
public class ControllerAdminTest {

    static MockedStatic<ConnectionToDB> mockedStatic = mockStatic(ConnectionToDB.class);

    @InjectMocks
    private ControllerAdmin controllerAdmin;
    @Mock
    private CardService cardService;
    @Mock
    private MockData mockData;

    @AfterAll
    static void tearDown() {
        mockedStatic.close();
    }

    @Test
    void methodShouldBlockCardTest() {
        CreditCard creditCard = getCreditCard(false);
        Mockito.when(cardService.block("1", "Rabbit")).thenReturn("true");
        String blocked = controllerAdmin.block("1", "Rabbit");
        Assertions.assertEquals("true", blocked);
        Mockito.verify(cardService).block("1", "Rabbit");
    }

    @Test
    void methodShouldUnBlockCardTest() {
        DebitCard debitCard = getDebitCard(true);
        Mockito.when(cardService.unblock("2", "Rabbit")).thenReturn("true");
        String unBlocked = controllerAdmin.unblock("2", "Rabbit");
        Assertions.assertEquals("true", unBlocked);
        Mockito.verify(cardService).unblock("2", "Rabbit");
    }

    @Test
    void getStatusIsBlockedTest() {
        DebitCard debitCard = getDebitCard(true);
        Mockito.when(cardService.isBlocked("2")).thenReturn("true");
        String isBlocked = controllerAdmin.isBlocked("2");
        Assertions.assertEquals("true", isBlocked);
        Mockito.verify(cardService).isBlocked("2");
    }

    @Test
    void methodShouldGenerateMockDataTest() {
        int n = 5;
        String generate = controllerAdmin.generate(n);
        Assertions.assertEquals("Data was generated", generate);
        Mockito.verify(mockData).generateMockData(n);
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
