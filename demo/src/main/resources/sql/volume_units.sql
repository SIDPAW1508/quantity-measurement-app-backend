-- SQL Script to Add Volume Units
-- Insert volume units into the units table

INSERT INTO units (label, symbol, type) VALUES 
('Liter', 'L', 'VOLUME'),
('Milliliter', 'mL', 'VOLUME'),
('Cubic Meter', 'm³', 'VOLUME'),
('Cubic Centimeter', 'cm³', 'VOLUME'),
('Cubic Millimeter', 'mm³', 'VOLUME'),
('Kiloliter', 'kL', 'VOLUME'),
('Gallon', 'gal', 'VOLUME'),
('Fluid Ounce', 'fl oz', 'VOLUME');
