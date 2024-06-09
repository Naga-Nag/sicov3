-- Active: 1716762502093@@127.0.0.1@3306@sico
CREATE TABLE Usuario (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    departamento_id INTEGER,
    admin BOOLEAN DEFAULT false
)

INSERT INTO Usuario (nombre, password, departamento_id, admin) VALUES
('admin', 'admin', 1, true)




DROP TABLE Usuario