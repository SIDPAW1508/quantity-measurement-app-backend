package com.app.quantitymeasurement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.quantitymeasurement.entities.History;

public interface HistoryRepository extends JpaRepository<History, Long> {

    // No need to write findAll() manually
	List<History> findByFromUnit_TypeIgnoreCase(String type);
}