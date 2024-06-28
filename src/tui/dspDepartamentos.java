package tui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import internal.Departamento;
import internal.Usuario;

public class dspDepartamentos {

    public static void menu(Usuario usuario) throws SQLException {
        utils.limpiarDisplay();
        departamentos();
        menuInterno(usuario);
    }

    public static void departamentos() throws SQLException {
        ResultSet departamentos = Departamento.getDepartamentos();
        while (departamentos.next()) {
            System.out.println(
                    departamentos.getInt(1) + " " + departamentos.getString(2) + " " + departamentos.getString(3));
        }
    }

    private static void menuEliminarDepartamento(Scanner scanner) throws SQLException {
        utils.limpiarDisplay();
        departamentos();
        System.out.println("ID de departamento a eliminar: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        Departamento.eliminarDepartamento(option);
    }

    private static void menuCrearDepartamento(Scanner scanner) throws SQLException {
        utils.limpiarDisplay();
        System.out.println("Nombre Completo: ");
        String nombre = scanner.nextLine();
        System.out.println("Cuatrigrma: ");
        String cuatrigrma = scanner.nextLine();
        Departamento.crearDepartamento(nombre, cuatrigrma);
    }

    private static void menuEditarDepartamento(Scanner scanner) throws SQLException {
        utils.limpiarDisplay();
        departamentos();
        System.out.println("ID de departamento a editar: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nombre Completo: ");
        String nombre = scanner.nextLine();
        System.out.println("Cuatrigrma: ");
        String cuatrigrma = scanner.nextLine();
        Departamento.editarDepartamento(option, nombre, cuatrigrma);
    }

    private static void menuInterno(Usuario usuario) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        if (usuario.getRol() == 3) {
            System.out.println(
                    "1. Crear Departamento  | 2. Editar Departamento  | 3. Eliminar Departamento  | 4. Volver");
        }

        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                menuCrearDepartamento(scanner);
                menu(usuario);
                break;
            case 2:
                System.out.println("Editar Departamento");
                menuEditarDepartamento(scanner);
                menu(usuario);

            case 3:
                menuEliminarDepartamento(scanner);
                menu(usuario);
                break;
            case 4:
                utils.limpiarDisplay();
                dspMenuPrincipal.menu(usuario);
                break;
            default:
                System.out.println("Opción no válida.");
                menuInterno(usuario);
        }

        scanner.close();
    }

    public static void main(String[] args) throws SQLException {
        menu(new Usuario(1, "admin", "admin", 1, 3));
    }
}
