CREATE TABLE Departamento (
    id INT NOT NULL AUTO_INCREMENT,
    cuatrigrama VARCHAR(50) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (nombre, cuatrigrama)
);

INSERT INTO Departamento (cuatrigrama, nombre) VALUES
('IFAP', 'INFORMATICA'),
('PPAP', 'PROGRAMAS Y PRESUPUESTOS'),
('SUAP', 'SUMINISTROS')


DROP TABLE Departamento;