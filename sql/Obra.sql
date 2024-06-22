CREATE TABLE Obra (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL,
  descripcion TEXT,
  departamento_id INTEGER,
  UNIQUE (nombre,departamento_id)
);

INSERT INTO Obra (nombre, descripcion, departamento_id) VALUES
('Mantenimiento y Limpieza', 'Para el Mantenimiento del Departamento', 1)

-- TODO: Cada departamento tiene una obra fija e inherente para mantenimiento del mismo "Mantenimiento"
-- TODO: Tambien tienen una para libreria, limpieza y elementos esenciales pero podria dejar todo en mantenimiento.

DROP TABLE Obra;