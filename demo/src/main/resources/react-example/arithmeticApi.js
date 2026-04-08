// Arithmetic API Service
// Handles all arithmetic operations with proper POST requests to Spring Boot backend

const API_BASE_URL = 'http://localhost:8080/arithmetic';

/**
 * Generic function to call arithmetic endpoints
 * @param {string} operation - 'add', 'subtract', 'multiply', 'divide'
 * @param {number} value1 - First value
 * @param {string} unit1 - First unit (e.g., 'm', 'kg', 'L')
 * @param {number} value2 - Second value
 * @param {string} unit2 - Second unit (e.g., 'm', 'kg', 'L')
 * @returns {Promise<number>} The result of the arithmetic operation
 */
export const performArithmeticOperation = async (operation, value1, unit1, value2, unit2) => {
  try {
    const response = await fetch(`${API_BASE_URL}/${operation}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        value1: value1,
        unit1: unit1,
        value2: value2,
        unit2: unit2
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
    console.error(`Error in ${operation} operation:`, error);
    throw error; // Re-throw to let caller handle
  }
};

/**
 * Convenience functions for each arithmetic operation
 */
export const add = (value1, unit1, value2, unit2) => 
  performArithmeticOperation('add', value1, unit1, value2, unit2);

export const subtract = (value1, unit1, value2, unit2) => 
  performArithmeticOperation('subtract', value1, unit1, value2, unit2);

export const multiply = (value1, unit1, value2, unit2) => 
  performArithmeticOperation('multiply', value1, unit1, value2, unit2);

export const divide = (value1, unit1, value2, unit2) => 
  performArithmeticOperation('divide', value1, unit1, value2, unit2);

/**
 * Example usage in React component:
 * 
 * import { add, subtract, multiply, divide } from './arithmeticApi';
 * 
 * const handleAdd = async () => {
 *   try {
 *     const result = await add(5, 'm', 3, 'm');
 *     console.log('Result:', result); // 8.0
 *     setResult(result);
 *   } catch (error) {
 *     setError(error.message);
 *   }
 * };
 */
