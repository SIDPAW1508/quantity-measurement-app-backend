package com.app.quantitymeasurement.dto;
 
public class MeasurementDTO {
	private double fromValue;
	private String fromUnit;
	private double toValue;
	private String toUnit;
	
	private String action;
	private String operator;
	
	
	 public double getFromValue() {
	        return fromValue;
	    }
 
	    public void setFromValue(double fromValue) {
	        this.fromValue = fromValue;
	    }
 
	    public String getFromUnit() {
	        return fromUnit;
	    }
 
	    public void setFromUnit(String fromUnit) {
	        this.fromUnit = fromUnit;
	    }
 
	    public double getToValue() {
	        return toValue;
	    }
 
	    public void setToValue(double toValue) {
	        this.toValue = toValue;
	    }
 
	    public String getToUnit() {
	        return toUnit;
	    }
 
	    public void setToUnit(String toUnit) {
	        this.toUnit = toUnit;
	    }
 
	    public String getAction() {
	        return action;
	    }
 
	    public void setAction(String action) {
	        this.action = action;
	    }
 
	    public String getOperator() {
	        return operator;
	    }
 
	    public void setOperator(String operator) {
	        this.operator = operator;
	    }
	
	
}