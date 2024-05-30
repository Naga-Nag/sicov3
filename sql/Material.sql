CREATE TABLE Material (
    id INT NOT NULL AUTO_INCREMENT,
    nomenclatura VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    precio DOUBLE NOT NULL,
    stock INT NOT NULL,
    PRIMARY KEY (id)
)