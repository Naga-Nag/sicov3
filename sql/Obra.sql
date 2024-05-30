CREATE TABLE Obra (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(255) NOT NULL,
  descripcion TEXT,
  departamento_id INTEGER REFERENCES Departamento(id),
  FOREIGN KEY(departamento_id) REFERENCES Departamento(id),
  UNIQUE (nombre,departamento_id)
);