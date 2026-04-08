package com.app.quantitymeasurement.service;

import org.springframework.stereotype.Service;

import com.app.quantitymeasurement.entities.Conversions;
import com.app.quantitymeasurement.entities.Unit;
import com.app.quantitymeasurement.repository.ConversionRepository;
import com.app.quantitymeasurement.repository.UnitRepository;

@Service
public class ConversionService {

    private final ConversionRepository conversionRepository;
    private final UnitRepository unitRepository;

    public ConversionService(ConversionRepository conversionRepository,
                             UnitRepository unitRepository) {
        this.conversionRepository = conversionRepository;
        this.unitRepository = unitRepository;
    }
    public boolean isTemperature(String unit) {
        return unit.equalsIgnoreCase("C") ||
               unit.equalsIgnoreCase("F") ||
               unit.equalsIgnoreCase("K");
    }
    
    /**
     * Gets the unit type (LENGTH, WEIGHT, TEMPERATURE, VOLUME) for a given unit symbol.
     * Used for validation in arithmetic operations.
     */
    public String getUnitType(String unit) {
        Unit unitEntity = unitRepository.findBySymbolIgnoreCase(unit)
                .orElseThrow(() -> new RuntimeException("Unit not found: " + unit));
        return unitEntity.getType();
    }
    private double convertTemperature(double value, String from, String to) {

        if (from.equalsIgnoreCase(to)) return value;

        // Convert everything to Celsius first
        double celsius;

        switch (from.toUpperCase()) {
            case "F":
                celsius = (value - 32) * 5/9;
                break;
            case "K":
                celsius = value - 273.15;
                break;
            default: // C
                celsius = value;
        }

        // Convert Celsius to target
        switch (to.toUpperCase()) {
            case "F":
                return (celsius * 9/5) + 32;
            case "K":
                return celsius + 273.15;
            default: // C
                return celsius;
        }
    }

    public double convert(double value, String from, String to) {

        // ✅ FIRST: Handle temperature
        if (isTemperature(from) && isTemperature(to)) {
            return convertTemperature(value, from, to);
        }

        // ✅ THEN handle same unit
        if (from.equalsIgnoreCase(to)) {
            return value;
        }

        // ✅ THEN DB lookup
        Unit fromUnit = unitRepository.findBySymbolIgnoreCase(from)
                .orElseThrow(() -> new RuntimeException("From unit not found"));

        Unit toUnit = unitRepository.findBySymbolIgnoreCase(to)
                .orElseThrow(() -> new RuntimeException("To unit not found"));

        Conversions conversion = conversionRepository
                .findByFromUnitAndToUnit(fromUnit, toUnit)
                .orElseThrow(() -> new RuntimeException(
                        "Conversion not found: " + from + " → " + to
                ));

        return value * conversion.getFactor();
    }
}