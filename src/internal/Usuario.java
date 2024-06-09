package internal;
import java.sql.SQLException;

import db.MySQLConnector;

public class Usuario {
    private int id;
    private String nombre;
    private String password;
    private int departamento_id;
    private boolean admin = false;

    public Usuario(int id, String nombre, String password, int departamento_id, boolean admin) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.departamento_id = departamento_id;
        this.admin = admin;
    }

    public Usuario(String nombre, String password, int departamento_id, boolean admin) {
        this.id = -1;
        this.nombre = nombre;
        this.password = password;
        this.departamento_id = departamento_id;
        this.admin = admin;
    }

    public boolean save() throws SQLException {
        String query;
        if (this.id > 0) {
            //Si el id "existe", es una actualización
            query = "UPDATE Usuario SET nombre = ?, password = ?, departamento_id = ? WHERE id = ?";
        } else {
            //Si el id "no existe", es una inserción
            query = "INSERT INTO Usuario (nombre, password, departamento_id) VALUES (?, ?, ?)";
        }

        try (var preparedStatement = MySQLConnector.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, this.nombre);
            preparedStatement.setString(2, this.password);
            preparedStatement.setInt(3, this.departamento_id);
            if (this.id > 0) {
                preparedStatement.setInt(4, this.id);
            }
            return preparedStatement.executeUpdate() > 0;
        }
    }

    //getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDepartamento_id() {
        return departamento_id;
    }

    public void setDepartamento_id(int departamento_id) {
        this.departamento_id = departamento_id;
    }

    public boolean esAdmin() {
        return admin;
    }
}