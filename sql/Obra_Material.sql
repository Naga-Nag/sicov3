-- Esta tabla va a ser la que defina que materiales usa cada obra.

CREATE TABLE Obra_Material (
  obra_id INTEGER NOT NULL,
  material_id INTEGER NOT NULL,
  cantidad_reservada INTEGER NOT NULL,
  PRIMARY KEY (obra_id, material_id),
  FOREIGN KEY (obra_id) REFERENCES Obra(id),
  FOREIGN KEY (material_id) REFERENCES Material(id));

INSERT INTO Obra_Material (obra_id, material_id, cantidad_reservada) VALUES
(1, 1, 10);

SELECT * FROM Obra_Material;

DELETE FROM Obra_Material WHERE obra_id = 1 AND material_id = 3;
DROP TABLE Obra_Material;

