import React, { useState, useEffect } from 'react';
import { convert, getAvailableUnits, areUnitsCompatible } from './conversionApi';

const UnitConverter = () => {
  const [value, setValue] = useState(1);
  const [fromUnit, setFromUnit] = useState('m');
  const [toUnit, setToUnit] = useState('cm');
  const [result, setResult] = useState(null);
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const [availableUnits] = useState(getAvailableUnits());

  // Auto-convert when inputs change
  useEffect(() => {
    if (value && fromUnit && toUnit && areUnitsCompatible(fromUnit, toUnit)) {
      handleConvert();
    } else {
      setResult(null);
      if (fromUnit && toUnit && !areUnitsCompatible(fromUnit, toUnit)) {
        setError('Units are not compatible for conversion');
      } else {
        setError('');
      }
    }
  }, [value, fromUnit, toUnit]);

  const handleConvert = async () => {
    if (!areUnitsCompatible(fromUnit, toUnit)) {
      setError('Units are not compatible for conversion');
      return;
    }

    setLoading(true);
    setError('');
    setResult(null);

    try {
      const conversionResult = await convert(value, fromUnit, toUnit);
      setResult(conversionResult);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  const handleSwapUnits = () => {
    setFromUnit(toUnit);
    setToUnit(fromUnit);
  };

  const handleQuickConvert = (preset) => {
    setValue(preset.value);
    setFromUnit(preset.from);
    setToUnit(preset.to);
  };

  // Common conversion presets
  const commonConversions = [
    { label: 'Length: 1m to cm', value: 1, from: 'm', to: 'cm' },
    { label: 'Length: 1km to m', value: 1, from: 'km', to: 'm' },
    { label: 'Weight: 1kg to g', value: 1, from: 'kg', to: 'g' },
    { label: 'Weight: 1lb to kg', value: 1, from: 'lb', to: 'kg' },
    { label: 'Volume: 1L to mL', value: 1, from: 'L', to: 'mL' },
    { label: 'Volume: 1gal to L', value: 1, from: 'gal', to: 'L' },
    { label: 'Temp: 0°C to F', value: 0, from: 'C', to: 'F' },
    { label: 'Temp: 100°C to F', value: 100, from: 'C', to: 'F' },
  ];

  return (
    <div className="converter-container">
      <div className="converter-header">
        <h2>Unit Converter</h2>
        <p>Convert between different units of measurement</p>
      </div>

      {/* Quick Conversion Presets */}
      <div className="presets-section">
        <h3>Quick Conversions</h3>
        <div className="presets-grid">
          {commonConversions.map((preset, index) => (
            <button
              key={index}
              className="preset-btn"
              onClick={() => handleQuickConvert(preset)}
            >
              {preset.label}
            </button>
          ))}
        </div>
      </div>

      {/* Main Converter */}
      <div className="converter-main">
        <div className="input-section">
          <div className="input-group">
            <label htmlFor="value">Value</label>
            <input
              id="value"
              type="number"
              value={value}
              onChange={(e) => setValue(parseFloat(e.target.value) || 0)}
              className="value-input"
              step="any"
            />
          </div>

          <div className="input-group">
            <label htmlFor="fromUnit">From</label>
            <select
              id="fromUnit"
              value={fromUnit}
              onChange={(e) => setFromUnit(e.target.value)}
              className="unit-select"
            >
              {Object.entries(availableUnits).map(([type, units]) => (
                <optgroup key={type} label={type}>
                  {units.map(unit => (
                    <option key={unit} value={unit}>
                      {unit}
                    </option>
                  ))}
                </optgroup>
              ))}
            </select>
          </div>

          <div className="swap-section">
            <button
              onClick={handleSwapUnits}
              className="swap-btn"
              title="Swap units"
            >
              <span className="swap-icon">Swap</span>
            </button>
          </div>

          <div className="input-group">
            <label htmlFor="toUnit">To</label>
            <select
              id="toUnit"
              value={toUnit}
              onChange={(e) => setToUnit(e.target.value)}
              className="unit-select"
            >
              {Object.entries(availableUnits).map(([type, units]) => (
                <optgroup key={type} label={type}>
                  {units.map(unit => (
                    <option key={unit} value={unit}>
                      {unit}
                    </option>
                  ))}
                </optgroup>
              ))}
            </select>
          </div>
        </div>

        {/* Loading State */}
        {loading && (
          <div className="loading">
            Converting...
          </div>
        )}

        {/* Error Display */}
        {error && (
          <div className="error-container">
            <strong>Error:</strong> {error}
          </div>
        )}

        {/* Result Display */}
        {result !== null && !loading && (
          <div className="result-container">
            <div className="result-main">
              <span className="result-value">{result.toFixed(6)}</span>
              <span className="result-unit">{toUnit}</span>
            </div>
            <div className="result-formula">
              {value} {fromUnit} = {result.toFixed(6)} {toUnit}
            </div>
          </div>
        )}

        {/* Conversion Info */}
        <div className="info-section">
          <h3>Supported Conversions</h3>
          <div className="conversion-types">
            {Object.entries(availableUnits).map(([type, units]) => (
              <div key={type} className="type-group">
                <strong>{type}:</strong> {units.join(', ')}
              </div>
            ))}
          </div>
          <p className="note">
            <strong>Note:</strong> You can only convert between units of the same type.
            Temperature conversions use special formulas for accuracy.
          </p>
        </div>
      </div>
    </div>
  );
};

export default UnitConverter;
