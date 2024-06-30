package internal;

import java.sql.SQLException;

import db.MySQLConnector;

public class Usuario {
    private int id;
    private String nombre;
    private String password;
    private int departamento_id;
    private int rol = 1; // 1: Usuario, 2: Operador, 3: Administrador

    public Usuario(int id, String nombre, String password, int departamento_id, int rol) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.departamento_id = departamento_id;
        this.rol = rol;
    }

    public Usuario(String nombre, String password, int departamento_id, int rol) {
        this.id = -1;
        this.nombre = nombre;
        this.password = password;
        this.departamento_id = departamento_id;
        this.rol = rol;
    }

    //Elimina usuario de la db con el id dado
    public static void eliminarUsuario(int id) throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        connector.execute("DELETE FROM Usuario WHERE id = " + id);
    }

    //Inserta usuario en la db
    public static void crearUsuario(String nombre, String password, int rol) throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        connector.execute("INSERT INTO Usuario (nombre, password, rol) VALUES ('" + nombre + "', '" + password + "', " + rol + ")");
    }

    //Edita usuario en la db
    public static void editarUsuario(int id, String nombre, String password, int rol) throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        connector.execute("UPDATE Usuario SET nombre = '" + nombre + "', password = '" + password + "', rol = " + rol + " WHERE id = " + id);
    }

    //getters y setters, justo esta clase no tiene mucho misterio

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

    public int getRol() {
        return rol;
    }

    public boolean esAdmin() {
        return this.rol == 3;
    }
}