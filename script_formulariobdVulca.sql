-- Creación de la base de datos
DROP DATABASE IF EXISTS dbVulcanizadora;
CREATE DATABASE dbVulcanizadora DEFAULT CHARACTER SET utf8mb4;
USE dbVulcanizadora;
-- Creación de tablas principales

CREATE TABLE Cliente (
    id_cliente int  NOT NULL,
    nombre varchar(100)  NOT NULL,
    apellido varchar(60)  NOT NULL,
    dirección  varchar(255)  NOT NULL,
    celular char(9)  NOT NULL,
    correo_electrónico varchar(255)  NOT NULL,
    tipo_de_documento char(3)  NOT NULL,
    numero_de_documento varchar(20)  NOT NULL,
    fecha date  NOT NULL,
    estado boolean  NOT NULL,
    CONSTRAINT Cliente_pk PRIMARY KEY (id_cliente)
);

CREATE TABLE Detalle_de_venta (
    id_detalle_venta int  NOT NULL,
    id_venta int  NOT NULL,
    id_producto int  NOT NULL,
    cantidad int  NOT NULL,
    precio_unitario decimal(10,2)  NOT NULL,
    Producto_id_producto int  NOT NULL,
    Venta_id_venta int  NOT NULL,
    CONSTRAINT Detalle_de_venta_pk PRIMARY KEY (id_detalle_venta)
);


CREATE TABLE Orden_de_Servicio (
    id_orden int  NOT NULL,
    id_cliente int  NOT NULL,
    id_producto int  NOT NULL,
    fecha timestamp  NOT NULL,
    estado char(1)  NOT NULL,
    Cliente_id_cliente int  NOT NULL,
    Producto_id_producto int  NOT NULL,
    servicio_id_servicio int  NOT NULL,
    CONSTRAINT Orden_de_Servicio_pk PRIMARY KEY (id_orden)
);


CREATE TABLE Producto (
    id_producto int  NOT NULL,
    nombre varchar(60)  NOT NULL,
    tipo varchar(50)  NOT NULL,
    marca varchar(50)  NOT NULL,
    precio decimal(10,2)  NOT NULL,
    stock int  NOT NULL,
    CONSTRAINT Producto_pk PRIMARY KEY (id_producto)
);

-- Table: Vehículo
CREATE TABLE Vehículo (
    id_vehículo int  NOT NULL,
    id_cliente int  NOT NULL,
    marca varchar(50)  NOT NULL,
    tipo varchar(50)  NOT NULL,
    placa varchar(20)  NOT NULL,
    Cliente_id_cliente int  NOT NULL,
    CONSTRAINT Vehículo_pk PRIMARY KEY (id_vehículo)
);

-- Table: Venta
CREATE TABLE Venta (
    id_venta int  NOT NULL,
    id_cliente int  NOT NULL,
    fecha timestamp  NOT NULL,
    total decimal(10,2)  NOT NULL,
    Cliente_id_cliente int  NOT NULL,
    CONSTRAINT Venta_pk PRIMARY KEY (id_venta)
);

-- Table: servicio
CREATE TABLE servicio (
    id_servicio INT  AUTO_INCREMENT,
    fecha date  NOT NULL,
    tipoServicio varchar(50)  NOT NULL,
    comentario longtext  NOT NULL,
    urgencia  VARCHAR(10) NOT NULL,
    precio numeric(10,2)  NOT NULL,
    CONSTRAINT servicio_pk PRIMARY KEY (id_servicio)
);
ALTER TABLE servicio ADD COLUMN activo BOOLEAN DEFAULT TRUE;
ALTER TABLE servicio MODIFY COLUMN fecha DATE;
select * from servicio;
-- foreign keys
-- Reference: Detalle_de_venta_Producto (table: Detalle_de_venta)
ALTER TABLE Detalle_de_venta
ADD CONSTRAINT FK_Detalle_de_venta_Producto FOREIGN KEY (id_producto)
REFERENCES Producto (id_producto);

-- Reference: Detalle_de_venta_Venta (table: Detalle_de_venta)
ALTER TABLE Detalle_de_venta
ADD CONSTRAINT FK_Detalle_de_venta_Venta FOREIGN KEY (id_venta)
REFERENCES Venta (id_venta);

-- Reference: Orden_de_Servicio_Cliente (table: Orden_de_Servicio)
ALTER TABLE Orden_de_Servicio
ADD CONSTRAINT FK_Orden_de_Servicio_Cliente FOREIGN KEY (id_cliente)
REFERENCES Cliente (id_cliente);

-- Reference: Orden_de_Servicio_Producto (table: Orden_de_Servicio)
ALTER TABLE Orden_de_Servicio
ADD CONSTRAINT FK_Orden_de_Servicio_Producto FOREIGN KEY (id_producto)
REFERENCES Producto (id_producto);

-- Reference: Orden_de_Servicio_servicio (table: Orden_de_Servicio)
ALTER TABLE Orden_de_Servicio
ADD CONSTRAINT FK_Orden_de_Servicio_servicio FOREIGN KEY (servicio_id_servicio)
REFERENCES servicio (id_servicio);

-- Reference: Vehículo_Cliente (table: Vehículo)
ALTER TABLE Vehículo
ADD CONSTRAINT FK_Vehiculo_Cliente FOREIGN KEY (id_cliente)
REFERENCES Cliente (id_cliente);

-- Reference: Venta_Cliente (table: Venta)
ALTER TABLE Venta
ADD CONSTRAINT FK_Venta_Cliente FOREIGN KEY (id_cliente)
REFERENCES Cliente (id_cliente);

-- Inserción de datos de ejemplo

INSERT INTO cliente VALUES (1,'Juan','Pérez','Av. Central 123','700100200','juan@email.com','DNI','12345678'),(2,'Ana','Gómez','Calle 5 #456','711122233','ana@email.com','DNI','87654321');

INSERT INTO `Supplier` VALUES (1,'Neumáticos S.A.','Av. Industrial 55','712345678','contacto@neumaticos.com'),(2,'Llantas Express','Calle Comercio 99','722233344','ventas@llantas.com');

INSERT INTO producto VALUES (1,1,'Llanta 14"','Llanta',120.00,10),(2,2,'Válvula','Accesorio',5.00,50);

INSERT INTO vehiculo VALUES (1,1,'Toyota','Corolla','ABC-123'),(2,2,'Hyundai','Elantra','XYZ-789');

INSERT INTO venta VALUES (1,1,'2025-04-17 12:00:00',245.00),(2,2,'2025-04-17 13:30:00',125.00);

INSERT INTO detalle_venta VALUES (1,1,1,2,120.00),(2,1,2,1,5.00),(3,2,1,1,120.00),(4,2,2,1,5.00);

INSERT INTO servicio VALUES (1,'Cambio de llanta','Cambio de llanta delantera derecha',30.00,'Operativo'),(2,'Balanceo','Balanceo de las 4 ruedas',50.00,'Vibrando');

INSERT INTO orden_servicio VALUES (1,1,1,1,'2025-04-17 10:00:00','C'),(2,2,2,2,'2025-04-17 11:30:00','P'); 

 