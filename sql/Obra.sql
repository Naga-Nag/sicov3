CREATE TABLE Obra (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(255) NOT NULL,
  descripcion TEXT,
  departamento_id INTEGER,
  UNIQUE (nombre,departamento_id)
);

-- TODO: Cada departamento tiene una obra fija e inherente para mantenimiento del mismo "Mantenimiento"
-- TODO: Tambien tienen una para libreria, limpieza y elementos esenciales pero podria dejar todo en mantenimiento.

-- DROP TABLE Obra;