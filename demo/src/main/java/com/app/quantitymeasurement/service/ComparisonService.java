package com.app.quantitymeasurement.service;

import org.springframework.stereotype.Service;

@Service
public class ComparisonService {

    private final ConversionService conversionService;

    public ComparisonService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public String compare(double value1, String unit1,
                          double value2, String unit2) {

        // Convert value2 → unit1
        double convertedValue2 = conversionService.convert(value2, unit2, unit1);

        double diff = Math.abs(value1 - convertedValue2);

        if (diff < 0.0001) return "EQUAL";
        else if (value1 > convertedValue2) return "GREATER";
        else return "LESS";
    }
}