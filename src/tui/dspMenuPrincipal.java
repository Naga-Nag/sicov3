package tui;

import java.sql.SQLException;
import java.util.Scanner;

import internal.Usuario;

public class dspMenuPrincipal {

    public static void menu(Usuario usuario) throws SQLException {
        System.out.println("1. Departamentos");
        System.out.println("2. Usuarios");
        System.out.println("3. Obras");
        System.out.println("4. Materiales");
        System.out.println("5. Salir");

        Scanner menuScanner = new Scanner(System.in);
        int option = menuScanner.nextInt();
        menuScanner.nextLine();

        System.out.println("Opcion: " + option);

        switch (option) {
            case 1:
                utils.limpiarDisplay();
                dspDepartamentos.menu(usuario);
            case 2:
                utils.limpiarDisplay();
                dspUsuarios.menu(usuario);
            case 3:
                utils.limpiarDisplay();
                dspObras.menu(usuario);
            case 4:
                utils.limpiarDisplay();
                dspMaterial.menu(usuario);
            case 5:
                utils.limpiarDisplay();
                System.exit(0);
            default:
                System.out.println("Opción no válida");
                utils.limpiarDisplay();
                menu(usuario);
        }
        menuScanner.close();
    }

    public static void main(String[] args) throws SQLException {
        menu(new Usuario(1, "admin", "admin", 1,3));
    }
}
