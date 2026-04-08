-- SQL Script to Add Volume Conversions
-- Insert conversion factors between volume units
-- All factors are based on converting from 'from_unit' to 'to_unit'

-- Liter to Milliliter (1 L = 1000 mL)
INSERT INTO conversions (from_unit_id, to_unit_id, factor, formula) 
SELECT u1.id, u2.id, 1000.0, 'multiply by 1000'
FROM units u1, units u2 
WHERE u1.symbol = 'L' AND u2.symbol = 'mL';

-- Milliliter to Liter (1 mL = 0.001 L)
INSERT INTO conversions (from_unit_id, to_unit_id, factor, formula) 
SELECT u1.id, u2.id, 0.001, 'divide by 1000'
FROM units u1, units u2 
WHERE u1.symbol = 'mL' AND u2.symbol = 'L';

-- Cubic Meter to Liter (1 m³ = 1000 L)
INSERT INTO conversions (from_unit_id, to_unit_id, factor, formula) 
SELECT u1.id, u2.id, 1000.0, 'multiply by 1000'
FROM units u1, units u2 
WHERE u1.symbol = 'm³' AND u2.symbol = 'L';

-- Liter to Cubic Meter (1 L = 0.001 m³)
INSERT INTO conversions (from_unit_id, to_unit_id, factor, formula) 
SELECT u1.id, u2.id, 0.001, 'divide by 1000'
FROM units u1, units u2 
WHERE u1.symbol = 'L' AND u2.symbol = 'm³';

-- Cubic Meter to Milliliter (1 m³ = 1000000 mL)
INSERT INTO conversions (from_unit_id, to_unit_id, factor, formula) 
SELECT u1.id, u2.id, 1000000.0, 'multiply by 1000000'
FROM units u1, units u2 
WHERE u1.symbol = 'm³' AND u2.symbol = 'mL';

-- Milliliter to Cubic Meter (1 mL = 0.000001 m³)
INSERT INTO conversions (from_unit_id, to_unit_id, factor, formula) 
SELECT u1.id, u2.id, 0.000001, 'divide by 1000000'
FROM units u1, units u2 
WHERE u1.symbol = 'mL' AND u2.symbol = 'm³';

-- Cubic Centimeter to Milliliter (1 cm³ = 1 mL)
INSERT INTO conversions (from_unit_id, to_unit_id, factor, formula) 
SELECT u1.id, u2.id, 1.0, '1 cm³ = 1 mL'
FROM units u1, units u2 
WHERE u1.symbol = 'cm³' AND u2.symbol = 'mL';

-- Milliliter to Cubic Centimeter (1 mL = 1 cm³)
INSERT INTO conversions (from_unit_id, to_unit_id, factor, formula) 
SELECT u1.id, u2.id, 1.0, '1 mL = 1 cm³'
FROM units u1, units u2 
WHERE u1.symbol = 'mL' AND u2.symbol = 'cm³';

-- Kiloliter to Liter (1 kL = 1000 L)
INSERT INTO conversions (from_unit_id, to_unit_id, factor, formula) 
SELECT u1.id, u2.id, 1000.0, 'multiply by 1000'
FROM units u1, units u2 
WHERE u1.symbol = 'kL' AND u2.symbol = 'L';

-- Liter to Kiloliter (1 L = 0.001 kL)
INSERT INTO conversions (from_unit_id, to_unit_id, factor, formula) 
SELECT u1.id, u2.id, 0.001, 'divide by 1000'
FROM units u1, units u2 
WHERE u1.symbol = 'L' AND u2.symbol = 'kL';

-- Gallon to Liter (1 US gal = 3.78541 L)
INSERT INTO conversions (from_unit_id, to_unit_id, factor, formula) 
SELECT u1.id, u2.id, 3.78541, 'multiply by 3.78541'
FROM units u1, units u2 
WHERE u1.symbol = 'gal' AND u2.symbol = 'L';

-- Liter to Gallon (1 L = 0.264172 US gal)
INSERT INTO conversions (from_unit_id, to_unit_id, factor, formula) 
SELECT u1.id, u2.id, 0.264172, 'multiply by 0.264172'
FROM units u1, units u2 
WHERE u1.symbol = 'L' AND u2.symbol = 'gal';

-- Fluid Ounce to Milliliter (1 US fl oz = 29.5735 mL)
INSERT INTO conversions (from_unit_id, to_unit_id, factor, formula) 
SELECT u1.id, u2.id, 29.5735, 'multiply by 29.5735'
FROM units u1, units u2 
WHERE u1.symbol = 'fl oz' AND u2.symbol = 'mL';

-- Milliliter to Fluid Ounce (1 mL = 0.033814 US fl oz)
INSERT INTO conversions (from_unit_id, to_unit_id, factor, formula) 
SELECT u1.id, u2.id, 0.033814, 'multiply by 0.033814'
FROM units u1, units u2 
WHERE u1.symbol = 'mL' AND u2.symbol = 'fl oz';
