package internal;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.MySQLConnector;

public class Material {
    private int id;
    private String nomenclatura;
    private String descripcion;
    private double precio;
    private double stock;

    public Material(int id, String nomenclatura, String descripcion, double precio, double stock) {
        this.id = id;
        this.nomenclatura = nomenclatura;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    public Material(int id) throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        ResultSet rs = connector.query("SELECT * FROM Material WHERE id = " + id);
        try {
            rs.next();
            this.id = rs.getInt("id");
            this.nomenclatura = rs.getString("nomenclatura");
            this.descripcion = rs.getString("descripcion");
            this.precio = rs.getDouble("precio");
            this.stock = rs.getDouble("stock");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void mostrarMateriales() throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        ResultSet rs = connector.query("SELECT * FROM Material");
        try {
            while (rs.next()) {
                System.out.print("ID: " + rs.getInt("id") + " || ");
                System.out.print("Nomenclatura: " + rs.getString("nomenclatura") + " || ");
                System.out.print("Descripción: " + rs.getString("descripcion") + " || ");
                System.out.print("Precio: $" + rs.getInt("precio") + " || ");
                System.out.print("Stock: " + rs.getDouble("stock") + " ||");
                System.out.println();
            }    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void agregarMaterial(String nomenclatura, String descripcion, double precio, double stock) throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        connector.execute("INSERT INTO Material (nomenclatura, descripcion, precio, stock) VALUES ('"+nomenclatura+"', '"+descripcion+"', "+precio+", "+stock+")");
    }

    public static void editarMaterial(int id, String nomenclatura, String descripcion, double precio, double stock) throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        connector.execute("UPDATE Material SET nomenclatura = '"+nomenclatura+"', descripcion = '"+descripcion+"', precio = "+precio+", stock = "+stock+" WHERE id = "+id);
    }

    public static void eliminarMaterial(int id) throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        connector.execute("DELETE FROM Material WHERE id = "+id);
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNomenclatura() {
        return nomenclatura;
    }
    public void setNomenclatura(String nomenclatura) {
        this.nomenclatura = nomenclatura;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
}