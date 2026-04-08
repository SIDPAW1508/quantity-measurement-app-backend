package com.app.quantitymeasurement.service;

import org.springframework.stereotype.Service;

@Service
public class ArithmeticService {

    private final ConversionService conversionService;
    private final HistoryService historyService;
    
    public ArithmeticService(ConversionService conversionService, HistoryService historyService) {
        this.conversionService = conversionService;
        this.historyService = historyService;
    }
    
    /**
     * Validates that both units are of the same type (LENGTH, WEIGHT, VOLUME)
     * or both are TEMPERATURE units for valid operations.
     */
    private void validateUnitCompatibility(String unit1, String unit2) {
        String type1 = conversionService.getUnitType(unit1);
        String type2 = conversionService.getUnitType(unit2);
        
        if (!type1.equals(type2)) {
            throw new IllegalArgumentException(
                "Cannot perform arithmetic operations between different unit types: " + 
                type1 + " (" + unit1 + ") and " + type2 + " (" + unit2 + ")"
            );
        }
    }
    
    /**
     * Checks if the operation is valid for temperature units.
     * Temperature supports addition/subtraction but not multiplication/division.
     */
    private void validateTemperatureOperation(String operation, String unit1, String unit2) {
        boolean isTemp1 = conversionService.isTemperature(unit1);
        boolean isTemp2 = conversionService.isTemperature(unit2);
        
        if ((isTemp1 || isTemp2) && 
            ("MULTIPLY".equals(operation) || "DIVIDE".equals(operation))) {
            throw new IllegalArgumentException(
                "Temperature units do not support " + operation.toLowerCase() + " operations. " +
                "Only addition and subtraction are allowed for temperature."
            );
        }
    }
    
    public double add(double value1, String unit1,
                      double value2, String unit2) {
        validateUnitCompatibility(unit1, unit2);
        validateTemperatureOperation("ADD", unit1, unit2);
        
        double converted = conversionService.convert(value2, unit2, unit1);
        double result = value1 + converted;
        
        // Save to history
        historyService.saveArithmeticHistory(value1, unit1, value2, unit2, result, "ADD");
        
        return result;
    }

    public double subtract(double value1, String unit1,
                           double value2, String unit2) {
        validateUnitCompatibility(unit1, unit2);
        validateTemperatureOperation("SUBTRACT", unit1, unit2);
        
        double converted = conversionService.convert(value2, unit2, unit1);
        double result = value1 - converted;
        
        // Save to history
        historyService.saveArithmeticHistory(value1, unit1, value2, unit2, result, "SUBTRACT");
        
        return result;
    }
    
    /**
     * Multiply two values of the same unit type.
     * Converts value2 to unit1 before multiplication.
     * Not supported for temperature units.
     */
    public double multiply(double value1, String unit1,
                          double value2, String unit2) {
        validateUnitCompatibility(unit1, unit2);
        validateTemperatureOperation("MULTIPLY", unit1, unit2);
        
        double converted = conversionService.convert(value2, unit2, unit1);
        double result = value1 * converted;
        
        // Save to history
        historyService.saveArithmeticHistory(value1, unit1, value2, unit2, result, "MULTIPLY");
        
        return result;
    }
    
    /**
     * Divide two values of the same unit type.
     * Converts value2 to unit1 before division.
     * Not supported for temperature units.
     * Throws ArithmeticException for division by zero.
     */
    public double divide(double value1, String unit1,
                        double value2, String unit2) {
        validateUnitCompatibility(unit1, unit2);
        validateTemperatureOperation("DIVIDE", unit1, unit2);
        
        double converted = conversionService.convert(value2, unit2, unit1);
        
        if (converted == 0.0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        
        double result = value1 / converted;
        
        // Save to history
        historyService.saveArithmeticHistory(value1, unit1, value2, unit2, result, "DIVIDE");
        
        return result;
    }
}