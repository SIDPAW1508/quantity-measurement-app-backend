package com.app.quantitymeasurement.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "conversions")
public class Conversions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_unit_id", nullable = false)
    private Unit fromUnit;

    @ManyToOne
    @JoinColumn(name = "to_unit_id", nullable = false)
    private Unit toUnit;

    @Column(nullable = false)
    private Double factor;

    private String formula;

    public Conversions() {
    }

    public Conversions(Unit fromUnit, Unit toUnit, Double factor, String formula) {
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
        this.factor = factor;
        this.formula = formula;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Unit getFromUnit() {
        return fromUnit;
    }

    public Unit getToUnit() {
        return toUnit;
    }

    public Double getFactor() {
        return factor;
    }

    public String getFormula() {
        return formula;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setFromUnit(Unit fromUnit) {
        this.fromUnit = fromUnit;
    }

    public void setToUnit(Unit toUnit) {
        this.toUnit = toUnit;
    }

    public void setFactor(Double factor) {
        this.factor = factor;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
}
