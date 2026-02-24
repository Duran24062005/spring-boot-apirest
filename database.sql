DROP DATABASE sales_db;

CREATE DATABASE IF NOT EXISTS sales_db;
USE sales_db;

-- =========================
-- TABLA PRODUCTOS
-- =========================
CREATE TABLE IF NOT EXISTS product(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    description TEXT,
    unitary_price DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL DEFAULT 0,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- =========================
-- TABLA VENTAS
-- =========================
CREATE TABLE IF NOT EXISTS sale(
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(250) NOT NULL,
    description TEXT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- =========================
-- DETALLE DE VENTAS
-- =========================
CREATE TABLE IF NOT EXISTS sale_detail(
    id INT AUTO_INCREMENT PRIMARY KEY,
    sale_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (sale_id) REFERENCES sale(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);



-- PRODUCTOS
INSERT INTO product (name, description, unitary_price, stock) VALUES
('Laptop Lenovo', 'Laptop 16GB RAM', 3200.00, 10),
('Mouse Logitech', 'Mouse inalámbrico', 80.00, 50),
('Teclado Mecánico', 'RGB switch blue', 250.00, 20);

-- VENTAS
INSERT INTO sale (customer_name, description, created_at) VALUES
('Juan Perez', 'Compra de equipos', '2026-02-24'),
('Maria Gomez', 'Compra oficina', '2026-02-24');

-- DETALLES DE VENTA
INSERT INTO sale_detail (sale_id, product_id, quantity, unit_price) VALUES
(1, 1, 1, 3200.00),
(1, 2, 2, 80.00),
(2, 3, 1, 250.00);