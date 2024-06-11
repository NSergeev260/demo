package com.finalproject;

import com.finalproject.card.ICard;
import com.finalproject.config.CrudFactory;
import com.finalproject.hibernate.ICardCrud;
import com.finalproject.jdbc.ConnectionToDB;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class MockDataTest {

    static MockedStatic<ConnectionToDB> mockedStatic = mockStatic(ConnectionToDB.class);

    @Mock
    private CrudFactory crudFactory;

    @Mock
    private ICardCrud crudMethodsCard;

    private MockData mockData;

    @BeforeEach
    void setUp() {
        Mockito.when(crudFactory.getICardCrud()).thenReturn(crudMethodsCard);
        mockData = new MockData(crudFactory);
    }

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
        Mockito.verify(crudMethodsCard, times(n))
            .insertCard(cardCaptor.capture());

        cardCaptor.getAllValues().forEach(card -> {
            Assertions.assertNotNull(card);
            Assertions.assertNotNull(card.getCardId());
            Assertions.assertTrue(card.getBalance().signum() >= 0);
            Assertions.assertNotNull(card.getType());
        });
    }
}
