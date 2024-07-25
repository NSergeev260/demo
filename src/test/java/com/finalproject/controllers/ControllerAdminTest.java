package com.finalproject.controllers;

import com.finalproject.MockData;
import com.finalproject.services.CardService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ControllerAdminTest {

    @Mock
    private CardService cardService;
    @Mock
    private MockData mockData;
    @InjectMocks
    private ControllerAdmin controllerAdmin;

    @Test
    void methodShouldBlockCardTest() {
        Mockito.when(cardService.block("1", "Rabbit")).thenReturn("true");

        String blocked = controllerAdmin.block("1", "Rabbit");
        Assertions.assertEquals("true", blocked);
        Mockito.verify(cardService).block("1", "Rabbit");
    }

    @Test
    void methodShouldUnBlockCardTest() {
        Mockito.when(cardService.unblock("2", "Rabbit")).thenReturn("true");

        String unBlocked = controllerAdmin.unblock("2", "Rabbit");
        Assertions.assertEquals("true", unBlocked);
        Mockito.verify(cardService).unblock("2", "Rabbit");
    }

    @Test
    void getStatusIsBlockedTest() {
        Mockito.when(cardService.isBlocked("2")).thenReturn("true");

        String isBlocked = controllerAdmin.isBlocked("2");
        Assertions.assertEquals("true", isBlocked);
        Mockito.verify(cardService).isBlocked("2");
    }

    @Test
    void methodShouldGenerateMockDataTest() {
        int n = 5;
        String generate = controllerAdmin.generate(n);
        Assertions.assertEquals("Cards was generated: " + n, generate);
        Mockito.verify(mockData).generateMockData(n);
    }
}
