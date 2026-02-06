USE `tecnostore_db`;

-- 1. Insertar Marcas
INSERT INTO `marcas` (`nombreMarca`) VALUES 
('Apple'),
('Samsung'),
('Xiaomi');

-- 2. Insertar Personas (Padres)
INSERT INTO `personas` (`nombre`, `identificacion`, `email`, `telefono`) VALUES 
('Juan Perez', '10102020', 'juan.perez@email.com', '3001234567'),
('Maria Lopez', '10203040', 'maria.l@email.com', '3109876543'),
('Carlos Ruiz', '10304050', 'cruiz@email.com', '3201112233');

-- 3. Insertar Clientes (Hijos)
INSERT INTO `clientes` (`persona_id`) VALUES 
(1), 
(2), 
(3);

-- 4. Insertar Celulares con precios en COP
INSERT INTO `celulares` (`marca_id`, `modelo`, `sistema_operativo`, `gama`, `precio`, `stock`) VALUES 
(1, 'iPhone 15 Pro', 'iOS', 'Alta', 5499900.00, 10),
(2, 'Galaxy S24 Ultra', 'Android', 'Alta', 4899000.00, 15),
(3, 'Redmi Note 13 Pro', 'Android', 'Media', 1450000.00, 25);

-- 5. Insertar una Venta de prueba (Juan compra el iPhone)
INSERT INTO `ventas` (`cliente_id`, `total`) VALUES 
(1, 5499900.00);

-- 6. Insertar el detalle de esa venta
INSERT INTO `detalle_ventas` (`venta_id`, `celular_id`, `cantidad`, `subtotal`) VALUES 
(1, 1, 1, 5499900.00);