package com.app.quantitymeasurement.repository;

import java.util.List;
import java.util.Optional;
import com.app.quantitymeasurement.entities.Conversions;
import com.app.quantitymeasurement.entities.Unit;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ConversionRepository 
        extends JpaRepository<Conversions, Long> {
    Optional<Conversions> findByFromUnitAndToUnit(Unit fromUnit, Unit toUnit);

    // 2. Get all conversions from a unit
    List<Conversions> findByFromUnit(Unit fromUnit);
    List<Conversions> findByToUnit(Unit toUnit);
}