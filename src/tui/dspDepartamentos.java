package tui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import db.MySQLConnector;
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

    public static void menuInterno(Usuario usuario) throws SQLException {
        if (usuario.esAdmin()) {
            System.out.println("1. Crear Departamento  | 2. Editar Departamento  | 3. Eliminar Departamento  | 4. Volver");
        }

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:

                System.out.println("Departamento creado exitosamente.");
                break;
            case 2:
                System.out.println("Editar Departamento (pendiente de implementación)");
                break;
            case 3:
                System.out.println("Eliminar Departamento (pendiente de implementación)");
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
}
