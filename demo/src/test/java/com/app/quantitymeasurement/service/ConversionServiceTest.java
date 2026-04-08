package com.app.quantitymeasurement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.app.quantitymeasurement.entities.Conversions;
import com.app.quantitymeasurement.entities.Unit;
import com.app.quantitymeasurement.repository.ConversionRepository;
import com.app.quantitymeasurement.repository.HistoryRepository;
import com.app.quantitymeasurement.repository.UnitRepository;

class ConversionServiceTest {

    @Mock
    private UnitRepository unitRepository;

    @Mock
    private ConversionRepository conversionRepository;

    @Mock
    private HistoryRepository historyRepository;

    @InjectMocks
    private ConversionService conversionService;

    public ConversionServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConvert() {

        Unit from = new Unit();
        from.setSymbol("m");

        Unit to = new Unit();
        to.setSymbol("cm");

        Conversions conversion = new Conversions();
        conversion.setFactor(100.0);

        when(unitRepository.findBySymbol("m")).thenReturn(Optional.of(from));
        when(unitRepository.findBySymbol("cm")).thenReturn(Optional.of(to));
        when(conversionRepository.findByFromUnitAndToUnit(from, to))
                .thenReturn(Optional.of(conversion));

        double result = conversionService.convert(1, "m", "cm");

        assertEquals(100.0, result);
    }
}