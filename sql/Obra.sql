CREATE TABLE Obra (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(255) NOT NULL,
  descripcion TEXT,
  departamento_id INTEGER,
  UNIQUE (nombre,departamento_id)
);

DROP TABLE Obra;