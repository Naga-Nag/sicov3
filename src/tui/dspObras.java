package tui;

import java.sql.SQLException;
import java.util.Scanner;

import internal.Obra;
import internal.Usuario;

public class dspObras {
    public static void menu(Usuario usuario) throws SQLException {
        utils.limpiarDisplay();
        obras(usuario);
        menuInterno(usuario);
    }

    public static void obras(Usuario usuario) throws SQLException {
        Obra.mostrarObras();
    }

    private static void menuCrearObra(Scanner scanner) throws SQLException {
        utils.limpiarDisplay();
        System.out.println("Crear Obra");

        System.out.println("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.println("Descripción: ");
        String descrip = scanner.nextLine();

        System.out.println("Departamento: ");
        String depto = scanner.nextLine();

        Obra.crearObra(nombre, descrip, depto);
    }

    private static void menuEditarObra(Scanner scanner) throws SQLException {
        utils.limpiarDisplay();
        System.out.println("Editar Obra");

        Obra.mostrarObras();

        System.out.println("ID de Obra a editar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.println("Descripción: ");
        String descrip = scanner.nextLine();

        System.out.println("Departamento: ");
        String depto = scanner.nextLine();

        Obra.editarObra(id, nombre, descrip, depto);
    }

    private static void menuEliminarObra(Scanner scanner) throws SQLException {
        utils.limpiarDisplay();
        System.out.println("Eliminar Obra");

        Obra.mostrarObras();

        System.out.println("ID de Obra a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Obra.eliminarObra(id);
    }

    private static void menuInterno(Usuario usuario) throws SQLException {
        System.out.println();
        System.out.println("1. Crear Obra || 2. Editar Obra || 3. Eliminar Obra || 4. Volver");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                menuCrearObra(scanner);
                menuInterno(usuario);
                break;
            case 2:
                menuEditarObra(scanner);
                menuInterno(usuario);
                break;
            case 3:
                menuEliminarObra(scanner);
                menuInterno(usuario);
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

    public static void main(String[] args) throws SQLException {
        menu(new Usuario(1, "admin", "admin", 1,3));
    }
    
}
