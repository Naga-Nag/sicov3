package tui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import db.MySQLConnector;
import internal.Usuario;

public class dspUsuarios {

    public static void menu(Usuario usuario) throws SQLException {
        utils.limpiarDisplay();
        menuInterno(usuario);
    }

    private static void menuCrearUsuario(Usuario usuario, Scanner scanner) throws SQLException {
        utils.limpiarDisplay();
        System.out.println("Nombre Completo: ");
        String nombre = scanner.nextLine();
        System.out.println("Contraseña: ");
        String contra = scanner.nextLine();
        System.out.println("Confirmar contraseña: ");
        String confirmarContra = scanner.nextLine();
        if (!contra.equals(confirmarContra)) {
            System.out.println("Las contraseñas no coinciden");
            menuCrearUsuario(usuario, scanner); 
        }
        int rol = 1;
        if (usuario.getRol() == 3) {
            System.out.println("Administrador: S/N");
            String admin = scanner.nextLine();
            if (admin.equals("S")) {
                rol = 3;
            }
        }
        Usuario.crearUsuario(nombre, contra, rol);
    }

    private static void menuEliminarUsuario(Usuario usuario, Scanner scanner) throws SQLException {
        utils.limpiarDisplay();
        displayUsuarios();
        System.out.println("ID de usuario a eliminar: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        Usuario.eliminarUsuario(option);
    }

    private static void menuEditarUsuario(Scanner scanner) throws SQLException {
        utils.limpiarDisplay();
        displayUsuarios();
        System.out.println("ID de usuario a editar: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nombre Completo: ");
        String nombre = scanner.nextLine();
        System.out.println("Contraseña: ");
        String contra = scanner.nextLine();
        System.out.println("Confirmar contraseña: ");
        String confirmarContra = scanner.nextLine();
        if (!contra.equals(confirmarContra)) {
            System.out.println("Las contraseñas no coinciden");
            menuEditarUsuario(scanner); 
        }
    }

    private static void menuInterno(Usuario usuario) throws SQLException {
        Scanner scanner = new Scanner(System.in); 

        displayUsuarios();
        System.out.println("1. Crear Usuario  | 2. Editar Usuario  | 3. Eliminar Usuario  | 4. Volver");

        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                menuCrearUsuario(usuario, scanner);
                menu(usuario);
                break;
            case 2:
                menuEditarUsuario(scanner);
                menu(usuario);
                break;
            case 3:
                menuEliminarUsuario(usuario, scanner);
                menu(usuario);
                break;
            case 4:
                utils.limpiarDisplay();
                dspMenuPrincipal.menu(usuario);
                break;
            default:
                System.out.println("Opción no válida.");
                menu(usuario);
        }

        scanner.close();
    }

    private static void displayUsuarios() throws SQLException {
        System.out.println("Usuarios");

        MySQLConnector con = new MySQLConnector();
        ResultSet usuarios = con.getUsuarios();

        while (usuarios.next()) {
            System.out.println(
                    usuarios.getInt(1) + " " + /* usuarios.getString(2) + */ " " + usuarios.getString(3));
            // La contraseña no se muestra por las dudas
        }
    }

    public static void usuarios(Usuario usuario) throws SQLException {
        if (usuario.getRol() == 3) {
            displayUsuarios();
        }
    }

    public static void main(String[] args) throws SQLException {
        menu(new Usuario(1, "admin", "admin", 1, 3));
    }
}
