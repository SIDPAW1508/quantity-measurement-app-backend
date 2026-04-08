package com.app.quantitymeasurement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.quantitymeasurement.entities.Unit;
import com.app.quantitymeasurement.repository.UnitRepository;

@Service
public class UnitService {

    private UnitRepository unitRepository;

    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public List<Unit> getAllUnits() {
        List<Unit> list = unitRepository.findAll();
        System.out.println("Units from DB: " + list.size());
        System.out.println(list);
        return list;
    }

    public List<Unit> getUnitsByType(String type) {
        return unitRepository.findByType(type);
    }
    
   
}