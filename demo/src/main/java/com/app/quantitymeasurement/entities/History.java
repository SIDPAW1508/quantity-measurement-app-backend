package com.app.quantitymeasurement.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fromunitid")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Unit fromUnit;

    @ManyToOne
    @JoinColumn(name = "tounitid")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Unit toUnit;

    private Double inputValue;
    private Double result;
    private String action;
    private LocalDateTime timestamp;

    public History() {
    }  
    public History(Unit fromUnit, Unit toUnit, Double inputValue, Double result, String action) {
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
        this.inputValue = inputValue;
        this.result = result;
        this.action = action;
    }

    public Long getId() {
        return id;
    }

    public Unit getFromUnit() {
        return fromUnit;
    }

    public void setFromUnit(Unit fromUnit) {
        this.fromUnit = fromUnit;
    }

    public Unit getToUnit() {
        return toUnit;
    }

    public void setToUnit(Unit toUnit) {
        this.toUnit = toUnit;
    }

    public Double getInputValue() {
        return inputValue;
    }

    public void setInputValue(Double inputValue) {
        this.inputValue = inputValue;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    // ✅ Auto-set timestamp before insert
    @PrePersist
    public void onCreate() {
        this.timestamp = LocalDateTime.now();
    }
}