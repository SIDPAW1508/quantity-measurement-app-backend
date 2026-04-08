import React, { useState } from 'react';
import { add, subtract, multiply, divide } from './arithmeticApi';

const ArithmeticCalculator = () => {
  const [value1, setValue1] = useState(1);
  const [unit1, setUnit1] = useState('m');
  const [value2, setValue2] = useState(2);
  const [unit2, setUnit2] = useState('m');
  const [result, setResult] = useState(null);
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const handleOperation = async (operation) => {
    setLoading(true);
    setError('');
    setResult(null);

    try {
      let operationResult;
      
      switch (operation) {
        case 'add':
          operationResult = await add(value1, unit1, value2, unit2);
          break;
        case 'subtract':
          operationResult = await subtract(value1, unit1, value2, unit2);
          break;
        case 'multiply':
          operationResult = await multiply(value1, unit1, value2, unit2);
          break;
        case 'divide':
          operationResult = await divide(value1, unit1, value2, unit2);
          break;
        default:
          throw new Error('Unknown operation');
      }
      
      setResult(operationResult);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ padding: '20px', maxWidth: '500px', margin: '0 auto' }}>
      <h2>Arithmetic Calculator</h2>
      
      <div style={{ marginBottom: '15px' }}>
        <label>
          Value 1:
          <input
            type="number"
            value={value1}
            onChange={(e) => setValue1(parseFloat(e.target.value) || 0)}
            style={{ marginLeft: '10px', marginRight: '10px', padding: '5px' }}
          />
        </label>
        
        <label>
          Unit 1:
          <select
            value={unit1}
            onChange={(e) => setUnit1(e.target.value)}
            style={{ marginLeft: '10px', padding: '5px' }}
          >
            <option value="m">m</option>
            <option value="cm">cm</option>
            <option value="km">km</option>
            <option value="kg">kg</option>
            <option value="g">g</option>
            <option value="L">L</option>
            <option value="mL">mL</option>
            <option value="m³">m³</option>
            <option value="C">°C</option>
            <option value="F">°F</option>
            <option value="K">K</option>
          </select>
        </label>
      </div>

      <div style={{ marginBottom: '15px' }}>
        <label>
          Value 2:
          <input
            type="number"
            value={value2}
            onChange={(e) => setValue2(parseFloat(e.target.value) || 0)}
            style={{ marginLeft: '10px', marginRight: '10px', padding: '5px' }}
          />
        </label>
        
        <label>
          Unit 2:
          <select
            value={unit2}
            onChange={(e) => setUnit2(e.target.value)}
            style={{ marginLeft: '10px', padding: '5px' }}
          >
            <option value="m">m</option>
            <option value="cm">cm</option>
            <option value="km">km</option>
            <option value="kg">kg</option>
            <option value="g">g</option>
            <option value="L">L</option>
            <option value="mL">mL</option>
            <option value="m³">m³</option>
            <option value="C">°C</option>
            <option value="F">°F</option>
            <option value="K">K</option>
          </select>
        </label>
      </div>

      <div style={{ marginBottom: '15px' }}>
        <button 
          onClick={() => handleOperation('add')}
          disabled={loading}
          style={{ margin: '5px', padding: '10px 15px' }}
        >
          Add (+)
        </button>
        
        <button 
          onClick={() => handleOperation('subtract')}
          disabled={loading}
          style={{ margin: '5px', padding: '10px 15px' }}
        >
          Subtract (-)
        </button>
        
        <button 
          onClick={() => handleOperation('multiply')}
          disabled={loading}
          style={{ margin: '5px', padding: '10px 15px' }}
        >
          Multiply (×)
        </button>
        
        <button 
          onClick={() => handleOperation('divide')}
          disabled={loading}
          style={{ margin: '5px', padding: '10px 15px' }}
        >
          Divide (÷)
        </button>
      </div>

      {loading && (
        <div style={{ color: 'blue', fontWeight: 'bold' }}>
          Calculating...
        </div>
      )}

      {error && (
        <div style={{ color: 'red', backgroundColor: '#ffebee', padding: '10px', borderRadius: '4px', marginBottom: '10px' }}>
          <strong>Error:</strong> {error}
        </div>
      )}

      {result !== null && (
        <div style={{ 
          backgroundColor: '#e8f5e8', 
          padding: '15px', 
          borderRadius: '4px',
          fontSize: '18px',
          fontWeight: 'bold'
        }}>
          Result: {result} {unit1}
        </div>
      )}

      <div style={{ marginTop: '20px', fontSize: '12px', color: '#666' }}>
        <p><strong>Note:</strong></p>
        <ul>
          <li>Temperature units (°C, °F, K) only support addition and subtraction</li>
          <li>Cross-type operations (e.g., kg + m) are not allowed</li>
          <li>Division by zero will throw an error</li>
        </ul>
      </div>
    </div>
  );
};

export default ArithmeticCalculator;
