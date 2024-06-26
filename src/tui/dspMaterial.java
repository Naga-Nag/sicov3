package tui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import db.MySQLConnector;
import internal.Material;
import internal.Usuario;


public class dspMaterial {
    public static void menu(Usuario usuario) throws SQLException {
        utils.limpiarDisplay();
        materiales(usuario);
        menuInterno(usuario);
    }

    private static void menuInterno(Usuario usuario) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Agregar Material  | 2. Editar Material  | 3. Eliminar Material  | 4. Volver");

        int option = scanner.nextInt();
        scanner.nextLine();
        
        switch (option) {
            case 1:
                menuAgregarMaterial(scanner);
                menu(usuario);
                break;
            case 2:
                menuEditarMaterial(scanner);
                menu(usuario);
                break;
            case 3:
                menuEliminarMaterial(scanner);
                menu(usuario);
                break;
            case 4:
                utils.limpiarDisplay();
                dspMenuPrincipal.menu(usuario);
                break;
            default:
                System.out.println("Opción no válida");
                menuInterno(usuario);
        }
    }

    static void mostrarMateriales() throws SQLException {
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

    private static void menuEliminarMaterial(Scanner scanner) throws SQLException {
        utils.limpiarDisplay();
        mostrarMateriales();
        System.out.println("ID de Material a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Material.eliminarMaterial(id);
        utils.limpiarDisplay();
    }

    private static void menuAgregarMaterial(Scanner scanner) throws SQLException {
        utils.limpiarDisplay();
        System.out.println("Nomenclatura: ");
        String nomenclatura = scanner.nextLine();

        System.out.println("Descripción: ");
        String descrip = scanner.nextLine();

        System.out.println("Precio x Unidad (o metro): ");
        Double precio = scanner.nextDouble();

        System.out.println("Stock: ");
        Double stock = scanner.nextDouble();

        Material.agregarMaterial(nomenclatura, descrip, precio, stock);
        utils.limpiarDisplay();
    }

    private static void menuEditarMaterial(Scanner scanner) throws SQLException {
        utils.limpiarDisplay();
        mostrarMateriales();
        System.out.println("ID de Material a editar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nomenclatura: ");
        String nomenclatura = scanner.nextLine();
        System.out.println("Descripción: ");
        String descrip = scanner.nextLine();
        System.out.println("Precio x Unidad (o metro): ");
        Double precio = scanner.nextDouble();
        System.out.println("Stock: ");
        Double stock = scanner.nextDouble();
        Material.editarMaterial(id, nomenclatura, descrip, precio, stock);
        utils.limpiarDisplay();
    }

    private static void materiales(Usuario usuario) throws SQLException {
        mostrarMateriales();
    }

    public static void main(String[] args) throws SQLException {
        menu(new Usuario(1, "admin", "admin", 1, 3));
    }
}
