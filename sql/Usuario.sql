CREATE TABLE Usuario (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    pass VARCHAR(50) NOT NULL,
    departamento_id INTEGER,
    FOREIGN KEY (departamento_id) REFERENCES Departamento(id)
)