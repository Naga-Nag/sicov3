package internal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    /* Devuelve todas las obras en un ArrayList */
    public static ArrayList<Obra> todasLasObras() throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        ResultSet rs = connector.query("SELECT * FROM Obra;");
        ArrayList<Obra> obras = new ArrayList<Obra>();
        try {
            while (rs.next()) {
                obras.add(new Obra(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"),
                        rs.getInt("departamento_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obras;
    }

    /*
     * Si necesito crear la obra en base de datos y que se asigne el id
     * automaticamente
     */
    public static Obra crearObra(String nombre, String descripcion, String departamento_cuatri) throws SQLException {
        if (!existeNombre(nombre)) {
            // Es mas simple usar el cuatrigrama para crear las obras
            Departamento d = new Departamento(departamento_cuatri);

            MySQLConnector connector = new MySQLConnector();
            connector.execute("INSERT INTO Obra (nombre, descripcion, departamento_id) VALUES ('" + nombre + "', '"
                    + descripcion + "', '" + d.getId() + "');");

            // El nombre de la obra es unica por estos casos
            ResultSet rs = connector.query("SELECT id FROM Obra WHERE nombre = '" + nombre + "';");
            rs.next();
            int id = rs.getInt("id");
            return new Obra(id);
        } else {
            throw new SQLException("Ya existe una Obra con ese nombre");
        }
    }

    // Edita la obra de la base de datos
    public static void editarObra(int id, String nombre, String descrip, String depto) throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        connector.execute("UPDATE Obra SET nombre = '" + nombre + "', descripcion = '" + descrip + "', depto = '"
                + depto + "' WHERE id = " + id + ";");
    }

    // Elimina la obra de la base de datos con el id dado
    public static void eliminarObra(int id) throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        connector.execute("DELETE FROM Obra WHERE id = " + id + ";");
        
    }

    public boolean existe() throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        ResultSet rs = connector.query("SELECT * FROM Obra WHERE id = " + this.id);
        //next() devuelve true o false si hay o no un registro
        return rs.next();
    }

    // Verifica si existe la obra Con el nombre dado
    public static boolean existeNombre(String nombre) throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        ResultSet rs = connector.query("SELECT * FROM Obra WHERE nombre = '" + nombre + "';");
        return rs.next();
    }

    // Verifica si existe la obra Con el id dado
    public static boolean existeObra(int idObra) throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        ResultSet rs = connector.query("SELECT * FROM Obra WHERE id = " + idObra);
        try {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void actualizarObra() throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        connector.execute("UPDATE Obra SET nombre = '" + this.nombre + "', descripcion = '" + this.descripcion
                + "', depto = '" + this.departamento_id + "' WHERE id = " + this.id + ";");
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

    public String getDepartamento() throws SQLException {
        //Busco el departamento con el id correspondiente
        Departamento d = new Departamento(departamento_id);

        //Devuelvo el nombre
        String departamento = d.getNombre();
        return departamento;
    }

    public int getDepartamento_id() {
        return departamento_id;
    }

    public void setDepartamento_id(int departamento_id) {
        this.departamento_id = departamento_id;
    }

    public void setDepartamento(String depto) throws SQLException {
        Departamento d = new Departamento(depto);
        this.departamento_id = d.getId();
    }

}