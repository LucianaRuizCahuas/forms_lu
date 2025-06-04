CREATE DATABASE dbMinimarket2025;
USE dbMinimarket2025;
CREATE TABLE CATEGORIA
(
    CODCAT char(6),
    NOMCAT varchar(60),
    CONSTRAINT CODCAT_PK PRIMARY KEY (CODCAT)
);
CREATE TABLE UBIGEO
(
    CODUBI char(6),
    DEPUBI varchar(80),
    PROVUBI varchar(80),
    DISTUBI varchar(80),
    CONSTRAINT CODUBI_PK PRIMARY KEY (CODUBI)
);
CREATE TABLE PRODUCTO
(
    CODPRO char(6),
    NOMPRO varchar(50),
    PREPRO decimal(8,2),
    STOCKPRO int,
    CODCAT char(6),
    CONSTRAINT CODPRO_PK PRIMARY KEY (CODPRO)
);
CREATE TABLE CLIENTE
(
    CODCLI char(6),
    DNICLI char(8),
    NOMCLI varchar(40),
    APECLI varchar(80),
    CELCLI char(9),
    EMACLI varchar(50),
    FECNACCLI date,
    UBICLI char(6),
    CONSTRAINT CODCLI_PK PRIMARY KEY (CODCLI)
);
CREATE TABLE VENDEDOR
(
    CODVEND char(6),
    NOMVEND varchar(50),
    APEVEND varchar(50),
    DNIVEND char(8),
    CELVEND char(9),
    DOMVEND varchar(80),
    UBIVEND char(6),
    CONSTRAINT CODVEND_PK PRIMARY KEY (CODVEND)
);


CREATE TABLE VENTA
(
    IDVENT int AUTO_INCREMENT,
    FECVENT date,
    CODVEND char(6),
    CODCLI char(6),
    TIPVENT char(1),
    CONSTRAINT IDVENT_PK PRIMARY KEY (IDVENT)
);

CREATE TABLE VENTA_DETALLE
(
    IDVENTDET int AUTO_INCREMENT,
    IDVENT int,
    CODPRO char(6),
    CANTVENTDET int,
    CONSTRAINT IDVENTDET_PK PRIMARY KEY (IDVENTDET)
);
ALTER TABLE CLIENTE
	ADD CONSTRAINT CLIENTE_UBIGEO FOREIGN KEY (UBICLI) REFERENCES UBIGEO (CODUBI);

ALTER TABLE VENDEDOR
	ADD CONSTRAINT VENDEDOR_UBIGEO FOREIGN KEY (UBIVEND) REFERENCES UBIGEO (CODUBI);
    
ALTER TABLE PRODUCTO
	ADD CONSTRAINT PRODUCTO_CATEGORIA FOREIGN KEY (CODCAT) REFERENCES CATEGORIA (CODCAT);
ALTER TABLE VENTA
	ADD CONSTRAINT VENTA_CLIENTE FOREIGN KEY (CODCLI) REFERENCES CLIENTE (CODCLI);
    
ALTER TABLE VENTA_DETALLE
	ADD CONSTRAINT VENTA_DETALLE_VENTA FOREIGN KEY (IDVENT) REFERENCES VENTA (IDVENT);
  
ALTER TABLE VENTA_DETALLE
	ADD CONSTRAINT VENTA_DETALLE_PRODUCTO FOREIGN KEY (CODPRO) REFERENCES PRODUCTO (CODPRO);
ALTER TABLE VENTA
    ADD CONSTRAINT VENTA_VENDEDOR FOREIGN KEY (CODVEND) REFERENCES VENDEDOR (CODVEND);
SHOW TABLES FROM dbMinimarket2025;

/* Listar relaciones */
SELECT 
  CONSTRAINT_NAME,
  `TABLE_NAME`,                            -- Foreign key table
  `COLUMN_NAME`,                           -- Foreign key column
  `REFERENCED_TABLE_NAME`,                 -- Origin key table
  `REFERENCED_COLUMN_NAME`                 -- Origin key column
FROM
  `INFORMATION_SCHEMA`.`KEY_COLUMN_USAGE`  -- Will fail if user don't have privilege
WHERE
  `TABLE_SCHEMA` = SCHEMA()                -- Detect current schema in USE 
  AND `REFERENCED_TABLE_NAME` IS NOT NULL; -- Only tables with foreign keys;
  
  DESCRIBE CATEGORIA;
  
  INSERT INTO CATEGORIA
  (CODCAT,NOMCAT)
  VALUES
  ('CA0001', 'Abarrotes'),
   ('CA0002', 'Carnes y pollo'),
    ('CA0003', 'làcteos y huevo'),
     ('CA0004', 'Higiene y limpieza');
	
SELECT * FROM CATEGORIA;

INSERT INTO UBIGEO
(CODUBI, DEPUBI, PROVUBI, DISTUBI)
VALUES
('140401', 'Lima', 'Cañete', 'San Vicente de Cañete'),
('140402', 'Lima', 'Cañete', 'Calango'),
('140403', 'Lima', 'Cañete', 'Cerro Azul'),
('140404', 'Lima', 'Cañete', 'Coayllo'),
('140405', 'Lima', 'Cañete', 'Chilca'),
('140406', 'Lima', 'Cañete', 'Imperial'),
('140407', 'Lima', 'Cañete', 'Lunahuaná'),
('140408', 'Lima', 'Cañete', 'Mala'),
('140409', 'Lima', 'Cañete', 'Nuevo Imperial'),
('140410', 'Lima', 'Cañete', 'Pacarán'),
('140411', 'Lima', 'Cañete', 'Quilmaná'),
('140412', 'Lima', 'Cañete', 'San Antonio'),
('140413', 'Lima', 'Cañete', 'San Luis'),
('140414', 'Lima', 'Cañete', 'Santa Cruz de Flores'),
('140415', 'Lima', 'Cañete', 'Zúñiga'),
('140416', 'Lima', 'Cañete', 'Asia');

SELECT * FROM UBIGEO;
/* Insertar registro tabla CLIENTE */
INSERT INTO CLIENTE
(CODCLI, DNICLI, NOMCLI, APECLI, CELCLI, EMACLI, FECNACCLI, UBICLI)
VALUES
('CL0001','45781233','Alicia','García Campos','929185236','agarcia@gmail.com','1977/01/01','140409'),
('CL0002','15487922','Juana','Ávila Chumpitaz','923568741','javila@gmail.com','1980/07/15','140402'),
('CL0003','15253588','Oscar','Coronado Vásquez','932615478','ocoronado@gmail.com','1975/06/12','140403'),
('CL0004','85213566','Luis','Barrios Palomino','932817645','lbarrios@outlook.com','1981/03/24','140409'),
('CL0005','15482566','María','Tarazona Mendoza','978400123','mtarazona@outlook.com','1975/08/16','140407'),
('CL0006','15428211','Pedro','Sánchez Dávila','941533268','psanchez@yahoo.com','1970/09/10','140402'),
('CL0007','47129533','Aldo','Torres Zavala','958866221','atorres@yahoo.com','1980/02/20','140405'),
('CL0008','48171533','Fiorella','Monteza Alzamora','992255441','fmonteza@gmail.com','1979/04/30','140408'),
('CL0009','15429866','Gloria','Linares Rodríguez','954415950','glinares@hotmail.com','1981/09/23','140403'),
('CL0010','85251592','Esperanza','Stark Parker','901133258','estark@yahoo.com','1978/10/24','140405');

SELECT * FROM UBIGEO;
DESCRIBE PRODUCTO;
/* Insertar registros tabla PRODUCTO */
INSERT INTO PRODUCTO
(CODPRO, NOMPRO, PREPRO, STOCKPRO, CODCAT)
VALUES
('P00001', 'Arroz', 4.50, 50, 'CA0001'),
('P00002', 'Azúcar', 3.50, 60, 'CA0001'),
('P00003', 'Fideos', 2.80, 50, 'CA0001'),
('P00004', 'Leche', 5.20, 90, 'CA0003'),
('P00005', 'Yogurt', 7.50, 30, 'CA0003'),
('P00006', 'Café', 2.00, 25, 'CA0001'),
('P00007', 'Aceite', 7.50, 90, 'CA0001'),
('P00008', 'Harina', 3.30, 45, 'CA0001'),
('P00009', 'Atún', 6.00, 35, 'CA0001'),
('P00010', 'Frejoles', 4.70, 40, 'CA0001');
SELECT * FROM PRODUCTO;
/* Listar registros tabla VENDEDOR */
INSERT INTO VENDEDOR
(CODVEND, NOMVEND, APEVEND, DNIVEND, CELVEND, DOMVEND, UBIVEND)
VALUES
('V00001','Alberto','Solano Pariona','77889955','99845632','Av. Miraflores','140405'),
('V00002','Ana','Enriquez Flores','22116633','978848551','Calle Los Libertadores','140407'),
('V00003','Carolina','Rojas Urrutia','66771144','916431258','Jr. José Olaya','140402');
 
 SELECT * FROM VENDEDOR;
 
 SELECT CURDATE();
 
 INSERT INTO VENTA
(FECVENT, CODVEND, CODCLI, TIPVENT)
VALUES
("2021/12/21","V00002","CL0001","D");
SELECT * FROM VENTA;

INSERT INTO VENTA_DETALLE
(IDVENT, CODPRO, CANTVENTDET)
VALUES
(1, 'P00001', 5);

 INSERT INTO VENTA
(FECVENT, CODVEND, CODCLI, TIPVENT)
VALUES
("2021/07/11","V00001","CL0003","R");
SELECT * FROM VENTA;

INSERT INTO VENTA_DETALLE
(IDVENT, CODPRO, CANTVENTDET)
VALUES
(2, 'P00003', 7),
(2, 'P00005', 3),
(2, 'P00007', 3),
(2, 'P00002', 6);