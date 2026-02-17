-- Creación de la base de datos
DROP SCHEMA IF EXISTS `tecnostore_db`;
CREATE SCHEMA IF NOT EXISTS `tecnostore_db` DEFAULT CHARACTER SET utf8;
USE `tecnostore_db`;

-- 1. Tabla Marcas
CREATE TABLE IF NOT EXISTS `marcas` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `nombreMarca` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `nombreMarca_UNIQUE` (`nombreMarca` ASC)
) ENGINE = InnoDB;

-- 2. Tabla Personas (Padre)
CREATE TABLE IF NOT EXISTS `personas` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(100) NOT NULL,
    `identificacion` VARCHAR(20) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `telefono` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `identificacion_UNIQUE` (`identificacion` ASC),
    UNIQUE INDEX `email_UNIQUE` (`email` ASC)
) ENGINE = InnoDB;

-- 3. Tabla Clientes (Hija - Herencia)
CREATE TABLE IF NOT EXISTS `clientes` (
    `persona_id` INT NOT NULL, 
    PRIMARY KEY (`persona_id`),
    CONSTRAINT `fk_clientes_personas`
    FOREIGN KEY (`persona_id`)
    REFERENCES `personas` (`id`)
    ON DELETE CASCADE 
    ON UPDATE CASCADE
) ENGINE = InnoDB;

-- 4. Tabla Celulares
CREATE TABLE IF NOT EXISTS `celulares` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `marca_id` INT NOT NULL,
    `modelo` VARCHAR(50) NOT NULL,
    `sistema_operativo` ENUM('iOS', 'Android') NOT NULL,
    `gama` ENUM('Alta', 'Media', 'Baja') NOT NULL,
    `precio` DECIMAL(20,2) NOT NULL,
    `stock` INT NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `chk_precio_positivo` CHECK (`precio` > 0),
    CONSTRAINT `chk_stock_no_negativo` CHECK (`stock` >= 0),
    CONSTRAINT `fk_celulares_marca`
    FOREIGN KEY (`marca_id`)
    REFERENCES `marcas` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
) ENGINE = InnoDB;

-- 5. Tabla Ventas
CREATE TABLE IF NOT EXISTS `ventas` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `cliente_id` INT NOT NULL,
    `fecha` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `total` DECIMAL(20,2) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `chk_total_positivo` CHECK (`total` >= 0),
    CONSTRAINT `fk_ventas_clientes`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `clientes` (`persona_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
) ENGINE = InnoDB;

-- 6. Tabla Detalle_Ventas
CREATE TABLE IF NOT EXISTS `detalle_ventas` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `venta_id` INT NOT NULL,
    `celular_id` INT NOT NULL,
    `cantidad` INT NOT NULL,
    `subtotal` DECIMAL(20,2) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `chk_cantidad_positiva` CHECK (`cantidad` > 0),
    CONSTRAINT `chk_subtotal_positivo` CHECK (`subtotal` > 0),
    CONSTRAINT `fk_detalle_venta`
    FOREIGN KEY (`venta_id`)
    REFERENCES `ventas` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_detalle_celular`
    FOREIGN KEY (`celular_id`)
    REFERENCES `celulares` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
) ENGINE = InnoDB;