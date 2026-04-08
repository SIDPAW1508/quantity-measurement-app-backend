package com.app.quantitymeasurement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ComparisonServiceTest {

    @Mock
    private ConversionService conversionService;

    @InjectMocks
    private ComparisonService comparisonService;

    public ComparisonServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGreater() {
        when(conversionService.convert(1, "m", "cm")).thenReturn(100.0);

        String result = comparisonService.compare(200, "cm", 1, "m");

        assertEquals("GREATER", result);
    }

    @Test
    void testLess() {
        when(conversionService.convert(3, "m", "cm")).thenReturn(300.0);

        String result = comparisonService.compare(200, "cm", 3, "m");

        assertEquals("LESS", result);
    }

    @Test
    void testEqual() {
        when(conversionService.convert(2, "m", "cm")).thenReturn(200.0);

        String result = comparisonService.compare(200, "cm", 2, "m");

        assertEquals("EQUAL", result);
    }
}