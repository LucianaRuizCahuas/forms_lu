DROP DATABASE IF EXISTS dbCrud;
CREATE DATABASE IF NOT EXISTS  dbCrud
CHARACTER SET utf8mb4;
USE  dbCrud;

CREATE TABLE IF NOT EXISTS servicios_registrados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha_servicio DATE NOT NULL,
    vehiculo VARCHAR(255) NOT NULL,
    descripcion TEXT,
    tipo_servicio VARCHAR(100),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
select * from servicios_registrados;

CREATE TABLE IF NOT EXISTS contactos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    asunto VARCHAR(255),
    mensaje TEXT,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
