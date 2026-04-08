// Conversion API Service
// Handles unit conversion operations with proper POST requests to Spring Boot backend

const API_BASE_URL = 'http://localhost:8080/arithmetic';

/**
 * Convert value from one unit to another
 * @param {number} value - The value to convert
 * @param {string} fromUnit - Source unit (e.g., 'm', 'kg', 'L', 'C')
 * @param {string} toUnit - Target unit (e.g., 'cm', 'g', 'mL', 'F')
 * @returns {Promise<number>} The converted result
 */
export const convert = async (value, fromUnit, toUnit) => {
  try {
    const response = await fetch(`${API_BASE_URL}/convert`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        value: value,
        from: fromUnit,
        to: toUnit
      })
    });

    if (!response.ok) {
      // Handle HTTP errors
      const errorText = await response.text();
      let errorMessage;
      
      try {
        // Try to parse as JSON error response
        const errorJson = JSON.parse(errorText);
        errorMessage = errorJson.message || errorJson.error || errorText;
      } catch {
        // If not JSON, use raw text
        errorMessage = errorText || `HTTP ${response.status}: ${response.statusText}`;
      }
      
      throw new Error(errorMessage);
    }

    // Return the result (backend returns plain number)
    const result = await response.text();
    return parseFloat(result);
    
  } catch (error) {
    console.error('Error in conversion operation:', error);
    throw error; // Re-throw to let caller handle
  }
};

/**
 * Get available units by type (for dropdown population)
 * @returns {Object} Object with unit types as keys and arrays of units as values
 */
export const getAvailableUnits = () => {
  return {
    LENGTH: ['m', 'cm', 'km', 'mm'],
    WEIGHT: ['kg', 'g', 'mg', 'lb'],
    VOLUME: ['L', 'mL', 'm³', 'cm³', 'gal', 'fl oz'],
    TEMPERATURE: ['C', 'F', 'K']
  };
};

/**
 * Get unit type from unit symbol
 * @param {string} unit - Unit symbol (e.g., 'm', 'kg', 'L')
 * @returns {string} Unit type (LENGTH, WEIGHT, VOLUME, TEMPERATURE)
 */
export const getUnitType = (unit) => {
  const units = getAvailableUnits();
  
  for (const [type, unitList] of Object.entries(units)) {
    if (unitList.includes(unit)) {
      return type;
    }
  }
  
  return 'UNKNOWN';
};

/**
 * Check if two units are compatible for conversion
 * @param {string} fromUnit - Source unit
 * @param {string} toUnit - Target unit
 * @returns {boolean} True if units can be converted
 */
export const areUnitsCompatible = (fromUnit, toUnit) => {
  const fromType = getUnitType(fromUnit);
  const toType = getUnitType(toUnit);
  
  return fromType !== 'UNKNOWN' && toType !== 'UNKNOWN' && fromType === toType;
};

/**
 * Example usage in React component:
 * 
 * import { convert, getAvailableUnits, areUnitsCompatible } from './conversionApi';
 * 
 * const handleConvert = async () => {
 *   try {
 *     if (!areUnitsCompatible(fromUnit, toUnit)) {
 *       setError('Units are not compatible for conversion');
 *       return;
 *     }
 *     
 *     const result = await convert(value, fromUnit, toUnit);
 *     console.log('Converted result:', result);
 *     setResult(result);
 *   } catch (error) {
 *     setError(error.message);
 *   }
 * };
 */
