package tui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import db.MySQLConnector;
import internal.Departamento;
import internal.Usuario;

public class dspDepartamentos {
    public static void menu(Usuario usuario) throws SQLException {
        departamentos();
        menuInterno(usuario);
    }

    public static void departamentos() throws SQLException {
        MySQLConnector con = new MySQLConnector();
        ResultSet departamentos = con.getDepartamentos();
        while (departamentos.next()) {
            System.out.println(
                    departamentos.getInt(1) + " " + departamentos.getString(2) + " " + departamentos.getString(3));
        }
    }

    private static void menuEliminarDepartamento() throws SQLException {
        utils.limpiarDisplay();
        departamentos();
        System.out.println("Que departamento desea eliminar?");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();

        //Departamento.eliminarDepartamento(option); TODO:Pendiente de implementar
        System.out.println("Departamento eliminado exitosamente.");

        scanner.close();
    }

    private static void menuCrearDepartamento() throws SQLException {
        utils.limpiarDisplay();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.println("Cuatrigrma: ");
        String cuatrigrma = scanner.nextLine();
        //Departamento.crearDepartamento(nombre, cuatrigrma); TODO:Pendiente de implementar

        scanner.close();
    }

    public static void menuInterno(Usuario usuario) throws SQLException {
        if (usuario.getRol() == 3) {
            System.out.println(
                    "1. Crear Departamento  | 2. Editar Departamento  | 3. Eliminar Departamento  | 4. Volver");
        }

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                menuCrearDepartamento();
                menu(usuario);
            case 2:
                System.out.println("Editar Departamento (pendiente de implementación)");
                break;
            case 3:
                menuEliminarDepartamento();
                menu(usuario);
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
