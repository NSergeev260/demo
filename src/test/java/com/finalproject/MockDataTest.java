package com.finalproject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MockDataTest {

    @Mock
    private MockData mockData;

    @Test
    void classShouldGenerateOneCardTest() {
        Mockito.doNothing().when(mockData).generateMockData(1);
        mockData.generateMockData(1);
        Mockito.verify(mockData).generateMockData(1);
    }

    @Test
    void classShouldGenerateOneHundredCardTest() {
        Mockito.doNothing().when(mockData).generateMockData(100);
        mockData.generateMockData(100);
        Mockito.verify(mockData).generateMockData(100);
    }
}
