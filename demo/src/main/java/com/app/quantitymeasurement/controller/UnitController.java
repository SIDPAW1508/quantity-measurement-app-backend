package com.app.quantitymeasurement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.app.quantitymeasurement.entities.Unit;
import com.app.quantitymeasurement.service.UnitService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/units")
public class UnitController {

    private UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    // ✅ GET all units
    @GetMapping
    public List<Unit> getAllUnits() {
        return unitService.getAllUnits();
    }
    @GetMapping("/type/{type}")
    public List<Unit> getUnitsByType(@PathVariable String type) {
        return unitService.getUnitsByType(type);
    }
}