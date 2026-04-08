package com.app.quantitymeasurement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.quantitymeasurement.entities.History;
import com.app.quantitymeasurement.entities.Unit;
import com.app.quantitymeasurement.repository.HistoryRepository;
import com.app.quantitymeasurement.repository.UnitRepository;

@Service
public class HistoryService {
	@Autowired 
	private HistoryRepository historyRepository;
	
	@Autowired
	private UnitRepository unitRepository;

    public List<History> getAllHistory() {
        return historyRepository.findAll();
    }
    
    public List<History> getHistoryByType(String type) {
        return historyRepository.findByFromUnit_TypeIgnoreCase(type);
    }
    
    /**
     * Save arithmetic operation history
     * For arithmetic operations, fromUnit and toUnit are the same (result unit)
     */
    public History saveArithmeticHistory(double value1, String unit1, 
                                        double value2, String unit2, 
                                        double result, String operation) {
        Unit unit = unitRepository.findBySymbolIgnoreCase(unit1)
                .orElseThrow(() -> new RuntimeException("Unit not found: " + unit1));
        
        History history = new History();
        history.setFromUnit(unit);
        history.setToUnit(unit); // Same unit for arithmetic operations
        history.setInputValue(value1); // Store first operand
        history.setResult(result);
        history.setAction(operation + " (" + value1 + " " + unit1 + " " + 
                         operation.toLowerCase() + " " + value2 + " " + unit2 + ")");
        
        return historyRepository.save(history);
    }
    
    /**
     * Save conversion operation history
     */
    public History saveConversionHistory(double inputValue, String fromUnit, 
                                       double result, String toUnit) {
        Unit from = unitRepository.findBySymbolIgnoreCase(fromUnit)
                .orElseThrow(() -> new RuntimeException("From unit not found: " + fromUnit));
        
        Unit to = unitRepository.findBySymbolIgnoreCase(toUnit)
                .orElseThrow(() -> new RuntimeException("To unit not found: " + toUnit));
        
        History history = new History();
        history.setFromUnit(from);
        history.setToUnit(to);
        history.setInputValue(inputValue);
        history.setResult(result);
        history.setAction("CONVERT");
        
        return historyRepository.save(history);
    }
}