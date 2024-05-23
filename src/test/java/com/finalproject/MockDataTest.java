package com.finalproject;

import static org.mockito.Mockito.mockStatic;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import com.finalproject.card.ICard;
import com.finalproject.jdbc.ConnectionToDB;
import com.finalproject.jdbc.CrudMethodsCard;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MockDataTest {

    static MockedStatic<ConnectionToDB> mockedStatic = mockStatic(ConnectionToDB.class);

    @Mock
    CrudMethodsCard crudMethodsCard;

    @InjectMocks
    MockData mockData;

    @AfterAll
    static void tearDown() {
        mockedStatic.close();
    }

    @Test
    void classShouldGenerateOneCardTest() {
        ArgumentCaptor<ICard> cardCaptor = ArgumentCaptor.forClass(ICard.class);
        mockData.generateMockData(1);
        Mockito.verify(crudMethodsCard).insertCard(cardCaptor.capture());
        Assertions.assertNotNull(cardCaptor.getValue());
    }

    @Test
    void classShouldGenerateNCardTest() {
        int n = 100;
        ArgumentCaptor<ICard> cardCaptor = ArgumentCaptor.forClass(ICard.class);
        mockData.generateMockData(n);
        Mockito.verify(crudMethodsCard, times(n)).insertCard(cardCaptor.capture());

        cardCaptor.getAllValues().forEach(card -> {
            Assertions.assertNotNull(card);
            Assertions.assertNotNull(card.getCardId());
            Assertions.assertTrue(card.getBalance().signum() >= 0);
            Assertions.assertNotNull(card.getType());
        });
    }
}
