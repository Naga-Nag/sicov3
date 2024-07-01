CREATE TABLE Material (
    id INT NOT NULL AUTO_INCREMENT,
    nomenclatura VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    precio DOUBLE NOT NULL,
    stock INT NOT NULL,
    PRIMARY KEY (id)
)

--INSERT INTO Material (nomenclatura, descripcion, precio, stock) VALUES ("Material 1", "Material de prueba 1", 100, 10);

--SELECT * FROM Material;

--DELETE FROM Material;

--DROP TABLE Material