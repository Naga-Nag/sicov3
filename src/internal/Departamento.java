package internal;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.MySQLConnector;

public class Departamento {
    private int id;
    private String cuatrigrama;
    private String nombre;

    public Departamento(int id, String cuatrigrama, String nombre) {
        this.id = id;
        this.cuatrigrama = cuatrigrama;
        this.nombre = nombre;
    }


    //Directamente busca en la base de datos el departamento con la ID
    public Departamento(int id) throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        ResultSet rs = connector.query("SELECT * FROM Departamento WHERE id = " + id);
        if (rs.next()) {
            this.id = rs.getInt("id");
            this.cuatrigrama = rs.getString("cuatrigrama");
            this.nombre = rs.getString("nombre");
        } else {
            throw new RuntimeException("No existe Departamento con ID " + id);
        }
        rs.close();
    }

    //Busca en la base de datos el departamento con el cuatrigrma
    public Departamento(String cuatrigrama) throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        ResultSet rs = connector.query("SELECT * FROM Departamento WHERE cuatrigrama = '"+cuatrigrama+"';");
        if (rs.next()) {
            this.id = rs.getInt("id");
            this.cuatrigrama = rs.getString("cuatrigrama");
            this.nombre = rs.getString("nombre");
        } else {
            throw new RuntimeException("No existe Departamento con cuatrigrama " + cuatrigrama);
        }
        rs.close();
    }

    public static void eliminarDepartamento(int id) throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        connector.execute("DELETE FROM Departamento WHERE id = " + id);

    }

    public static void crearDepartamento(String cuatrigrama, String nombre) throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        connector.execute("INSERT INTO Departamento (nombre, cuatrigrama) VALUES ('" + nombre + "', '" + cuatrigrama + "');");
    }

    public static void editarDepartamento(int id, String nombre, String cuatrigrama) throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        connector.execute("UPDATE Departamento SET nombre = '" + nombre + "', cuatrigrama = '" + cuatrigrama + "' WHERE id = " + id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCuatrigrama() {
        return cuatrigrama;
    }

    public void setCuatrigrama(String cuatrigrama) {
        this.cuatrigrama = cuatrigrama;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Esto deberia dar como resultado IFAP, id: 1
    public static void main(String[] args) {
        try {
            Departamento d = new Departamento(1); //IFAP
            System.out.println("ID: " + d.getId());
            System.out.println("Cuatrigrama: " + d.getCuatrigrama());
            System.out.println("Nombre: " + d.getNombre());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
