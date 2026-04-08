package com.app.quantitymeasurement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ArithmeticServiceTest {

    @Mock
    private ConversionService conversionService;

    @InjectMocks
    private ArithmeticService arithmeticService;

    public ArithmeticServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdd() {
        // Mock behavior
        when(conversionService.convert(1, "m", "cm")).thenReturn(100.0);

        double result = arithmeticService.add(100, "cm", 1, "m");

        assertEquals(200, result);
    }

    @Test
    void testSubtract() {
        when(conversionService.convert(1, "m", "cm")).thenReturn(100.0);

        double result = arithmeticService.subtract(200, "cm", 1, "m");

        assertEquals(100, result);
    }
}