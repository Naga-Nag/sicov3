package internal;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.MySQLConnector;

public class Obra {
    private int id;
    private String nombre;
    private String descripcion;
    private int departamento_id;


    public Obra(int id, String nombre, String descripcion, int departamento_id) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.departamento_id = departamento_id;
    }

    public Obra(int id) throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        ResultSet rs = connector.query("SELECT * FROM Obra WHERE id = " + id);
        try {
            rs.next();
            this.id = rs.getInt("id");
            this.nombre = rs.getString("nombre");
            this.descripcion = rs.getString("descripcion");
            this.departamento_id = rs.getInt("departamento_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void mostrarObras() throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        ResultSet rs = connector.query("SELECT * FROM Obra");
        try {
            while (rs.next()) {
                System.out.print("ID: " + rs.getInt("id") + " || ");
                System.out.print("Nombre: " + rs.getString("nombre") + " || ");
                System.out.print("Descripción: " + rs.getString("descripcion") + " || ");
                int departamento_id = rs.getInt("departamento_id");
                Departamento d = new Departamento(departamento_id);
                System.out.print("Departamento: " + d.getNombre() + " || ");
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void crearObra(String nombre, String descripcion, String departamento_cuatri) throws SQLException {
        Departamento d = new Departamento(departamento_cuatri);
        
        MySQLConnector connector = new MySQLConnector();
        connector.execute("INSERT INTO Obra (nombre, descripcion, departamento_id) VALUES ('" + nombre + "', '" + descripcion + "', '" + d.getId()+ "');");
    }

    public static void editarObra(int id, String nombre, String descrip, String depto) throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        connector.execute("UPDATE Obra SET nombre = '" + nombre + "', descripcion = '" + descrip + "', depto = '" + depto + "' WHERE id = " + id + ";");
    }

    public static void eliminarObra(int id) throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        connector.execute("DELETE FROM Obra WHERE id = " + id + ";");
    }
    

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDepartamento_id() {
        return departamento_id;
    }

    public void setDepartamento_id(int departamento_id) {
        this.departamento_id = departamento_id;
    }

    public static void main(String[] args) {
        try {
            Obra.mostrarObras();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Obra.crearObra("Obra 1", "Descripción 1", "IFAP");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}