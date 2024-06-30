package internal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.MySQLConnector;

public class Obra_Material {

    private int obra_id;
    private int material_id;
    private int cantidad_reservada;

    public Obra_Material(int id_obra, int id_material, int cantidad_reservada) {
        this.obra_id = id_obra;
        this.material_id = id_material;
        this.cantidad_reservada = cantidad_reservada;
    }

    public void guardar() throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        connector.execute("INSERT INTO Obra_Material (id_obra, id_material, cantidad_reservada) VALUES (" + this.obra_id
                + ", " + this.material_id + ", " + this.cantidad_reservada + ");");
    }

    /* Elimina el material_obra ya cargado */
    public void eliminarMaterialObra() throws SQLException {
        // Elimina el material_obra
        MySQLConnector connector = new MySQLConnector();
        connector.execute("DELETE FROM Obra_Material WHERE id_obra = " + this.obra_id + " AND id_material = "
                + this.material_id + ";");

        // Actualiza el stock del material
        Material material = new Material(this.material_id);
        material.setStock(cantidad_reservada + material.getStock());

        material.actualizarMaterial();
    }

    public static void mostrarObrasMateriales() throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        connector.execute("SELECT * FROM Obra_Material;");

        ResultSet rs = connector.query("SELECT * FROM Obra_Material;");
        try {
            while (rs.next()) {
                var obra_nombre = new Obra(rs.getInt("obra_id")).getNombre();
                System.out.print("Nombre de Obra: " + obra_nombre + " || ");
                System.out.print("ID Obra: " + rs.getInt("obra_id") + " || ");

                var material_nombre = new Material(rs.getInt("material_id")).getNomenclatura();
                System.out.print("Nombre de Material: " + material_nombre + " || ");
                System.out.print("ID Material: " + rs.getInt("material_id") + " || ");

                System.out.print("Cantidad Reservada: " + rs.getInt("cantidad_reservada") + " || ");
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* Devuelve todos los materiales de una obra en un ArrayList */
    public static ArrayList<Material> materialesDeObra(int id) throws SQLException {
        ArrayList<Material> materiales = new ArrayList<Material>();
        MySQLConnector connector = new MySQLConnector();
        ResultSet rs = connector.query("SELECT * FROM Obra_Material WHERE obra_id = " + id + ";");
        try {
            while (rs.next()) {
                var material = rs.getInt("material_id");
                materiales.add(new Material(material));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materiales;
    }

    public int getId_obra() {
        return obra_id;
    }

    public void setId_obra(int id_obra) {
        this.obra_id = id_obra;
    }

    public int getId_material() {
        return material_id;
    }

    public void setId_material(int id_material) {
        this.material_id = id_material;
    }

    public int getCantidad_reservada() {
        return cantidad_reservada;
    }

    public void setCantidad_reservada(int cantidad_reservada) {
        this.cantidad_reservada = cantidad_reservada;
    }

    public static void main(String[] args) {
        try {
            materialesDeObra(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void asignarMaterial(int idObra, int idMaterial, int cantidad) throws SQLException {
        // Existe la obra?
        if (!Obra.existeObra(idObra)) {
            throw new SQLException("Error: La obra con ID " + idObra + " no existe.");
        }

        // Existe el material?
        if (!Material.existeMaterial(idMaterial)) {
            throw new SQLException("Error: El material con id " + idMaterial + " no existe.");
        }

        // Veo si el material esta asignado a la obra
        if (Obra_Material.materialEstaEnObra(idObra, idMaterial)) {
            throw new SQLException("Error: El material ya está asignado a la obra.");
        }

        // Inserto la asociación entre obra y material
        MySQLConnector connector = new MySQLConnector();
        connector.execute
        (
            "INSERT INTO obra_material (obra_id, material_id, cantidad_reservada) VALUES (" + idObra
            + ", " + idMaterial + ", " + cantidad + ")"
        );

        // Actualizo el stock del material
        Material material = new Material(idMaterial);
        material.setStock(material.getStock() - cantidad);
        material.actualizarMaterial();
    }

    public static double cantidadMaterialAsignada(int idObra, int idMaterial) throws SQLException {
        // Si la obra no existe tiro error
        if (!Obra.existeObra(idObra)) {
            throw new SQLException("Error: La obra con ID " + idObra + " no existe.");
        }

        Material material = new Material(idMaterial);

        return material.getStock();
    }

    public static void quitarMaterial(int idObra, int idMaterial) throws SQLException {
        // Ver si la obra existe
        if (!Obra.existeObra(idObra)) {
            throw new SQLException("Error: La obra con ID " + idObra + " no existe.");
        }

        // Si existe el material
        if (!Material.existeMaterial(idMaterial)) {
            throw new SQLException("Error: El material con id " + idMaterial + " no existe.");
        }

        // Si el material esta asignado a la obra
        if (!Obra_Material.materialEstaEnObra(idObra, idMaterial)) {
            throw new SQLException("Error: El material no está asignado a la obra.");
        }

        //Tomamos el stock del material asignado
        var stockAsignado = Obra_Material.cantidadMaterialAsignada(idObra, idMaterial);

        // Ahora si removemos el material de la obra
        MySQLConnector connector = new MySQLConnector();
        connector.execute
        (
            "DELETE FROM Obra_Material WHERE obra_id = " + idObra + " AND material_id = " + idMaterial + ";"
        );

        // Actualizamos el stock del material
        Material material = new Material(idMaterial);
        material.setStock(stockAsignado + material.getStock());
        material.actualizarMaterial();
        
    }  

    
    public static boolean materialEstaEnObra(int idObra, int idMaterial) throws SQLException {
        MySQLConnector connector = new MySQLConnector();
        ResultSet rs = connector.query("SELECT * FROM Obra_Material WHERE obra_id = " + idObra
                + " AND material_id = " + idMaterial + ";");
        try {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
