package com.finalproject.user;

import com.finalproject.MockData;
import com.finalproject.card.CreditCard;
import com.finalproject.card.ICard;
import com.finalproject.jdbc.CrudMethodsCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Random;

@ExtendWith(MockitoExtension.class)
public class MockDataTest {

    @Mock
    private MockData mockData;

    @Mock
    private Random rnd = new Random();

    @Mock
    private CrudMethodsCard crudMethodsCard;

    @Test
    public void classShouldGenerateCardTest() {
        CreditCard creditCard = new CreditCard("1", BigDecimal.valueOf(100), false, "1a");
        Mockito.doNothing().when(mockData).generateMockData(1);
        mockData.generateMockData(1);
        Mockito.verify(mockData).generateMockData(1);

    }
}
