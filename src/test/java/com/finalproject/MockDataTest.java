package com.finalproject;

import com.finalproject.card.ICard;
import com.finalproject.jdbc.CrudMethodsCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class MockDataTest {

    @Mock
    private CrudMethodsCard crudMethodsCard;
    @InjectMocks
    private MockData mockData;

    @Test
    void classShouldGenerateOneCardTest() {
        ArgumentCaptor<ICard> cardCaptor = ArgumentCaptor.forClass(ICard.class);
        mockData.generateMockData(1);
        Mockito.verify(crudMethodsCard).insertCard(any());
        ICard card = cardCaptor.capture();
        Assertions.assertNotNull(card);
    }

    @Test
    void classShouldGenerateOneHundredCardTest() {
        Mockito.doNothing().when(mockData).generateMockData(100);
        mockData.generateMockData(100);
        Mockito.verify(mockData).generateMockData(100);
    }




}
