package internal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        ResultSet rs = connector.query("SELECT * FROM Material WHERE id = " + id + ";");
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

    public void actualizarMaterial() throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        connector.execute("UPDATE Material SET nomenclatura = '"+nomenclatura+"', descripcion = '"+descripcion+"', precio = "+precio+", stock = "+stock+" WHERE id = "+id);
    }



    // Para buscar un material por su nomenclatura
    public Material buscarIdNomenclatura(String nomenclatura) throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        ResultSet rs = connector.query("SELECT * FROM Material WHERE nomenclatura = '" + nomenclatura + "';");
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
        return this;
    }

    // Para verificar si existe el material con el id ingresado
    public static boolean existeMaterial(int id) throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        ResultSet rs = connector.query("SELECT * FROM Material WHERE id = " + id + ";");
        try {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Devuelve un ArrayList con todos los materiales que se encuentren en la base de datos
    public static ArrayList<Material> todosLosMateriales() throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        ResultSet rs = connector.query("SELECT * FROM Material");
        ArrayList<Material> materiales = new ArrayList<Material>();
        try {
            while (rs.next()) {
                Material material = new Material(rs.getInt("id"));
                materiales.add(material);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materiales;
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

    public double getStock() {
        return stock;
    }
    public void setStock(double stock) {
        this.stock = stock;
    }
    
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
}