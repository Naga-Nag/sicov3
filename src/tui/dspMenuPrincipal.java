package tui;

import java.sql.SQLException;
import java.util.Scanner;

import internal.Usuario;

public class dspMenuPrincipal {

    public static void menu(Usuario usuario) throws SQLException {
        System.out.println("1. Departamentos");
        System.out.println("2. Usuarios");
        System.out.println("4. Salir");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                utils.limpiarDisplay();
                try {
                    dspDepartamentos.menu(usuario);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                utils.limpiarDisplay();
                dspUsuarios.menu(usuario);
                break;
            case 4:
                utils.limpiarDisplay();
                System.exit(0);
                break;
            default:
                utils.limpiarDisplay();
                System.out.println("Opción no válida.");
                dspMenuPrincipal.menu(usuario);
        }

        scanner.close();
    }
}
