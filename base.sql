CREATE TABLE ventas(
fecha date PRIMARY KEY,
capturista varchar(64) NOT NULL,
ventabruta real
);

INSERT INTO ventas(fecha,capturista,ventabruta) VALUES('2016-03-01','OMAR',12000.05);
