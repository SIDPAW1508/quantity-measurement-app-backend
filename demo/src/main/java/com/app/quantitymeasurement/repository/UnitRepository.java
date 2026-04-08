package com.app.quantitymeasurement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.quantitymeasurement.entities.Unit;

public interface UnitRepository extends JpaRepository<Unit, Long> {

    // Find ONE unit using symbol (m, cm, kg)
	Optional<Unit> findBySymbolIgnoreCase(String symbol);
    List<Unit> findByType(String type);
}