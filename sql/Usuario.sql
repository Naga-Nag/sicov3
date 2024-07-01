-- Active: 1716762502093@@127.0.0.1@3306@sico
CREATE TABLE Usuario (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    departamento_id INTEGER,
    rol INT DEFAULT 1 -- 1: Usuario, 2: Operador, 3: Administrador
)

--INSERT INTO Usuario (nombre, password, departamento_id, rol) VALUES('admin', 'admin', 1, 3) -- Creo el superadmin por defecto


-- DROP TABLE Usuario