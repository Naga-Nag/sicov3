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

    private static void menuInterno(Usuario usuario) throws SQLException {
        displayUsuarios();
        System.out.println("1. Crear Usuario  | 2. Editar Usuario  | 3. Eliminar Usuario  | 4. Volver");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                System.out.println("Crear Usuario");
                Scanner usuInput = new Scanner(System.in);

                System.out.println("Nombre: ");
                String nombre = usuInput.nextLine();

                System.out.println("Contraseña: ");
                String contra = usuInput.nextLine();

                System.out.println("Confirmar contraseña: ");
                String confirmarContra = usuInput.nextLine();

                if (!contra.equals(confirmarContra)) {
                    System.out.println("Las contraseñas no coinciden");
                    dspDepartamentos.menu(usuario);
                }

                int rol = 1;

                if (usuario.getRol() == 3) {
                    System.out.println("Administrador: S/N");
                    String admin = usuInput.nextLine();

                    if (admin.equals("S")) {
                        rol = 3;
                    }
                }

                Usuario user = new Usuario(0, nombre, contra, 0, rol);

                //user.GUARDAR_EN_LA_BASE_DE_DATOS(); TODO:Pendiente de implementar;
                
                usuInput.close();
                System.out.println("Usuario creado exitosamente.");
                break;

            case 2:
                System.out.println("Editar Usuario (pendiente de implementación)");
                break;
            case 3:
                System.out.println("Eliminar Usuario (pendiente de implementación)");
                break;
            case 4:
                utils.limpiarDisplay();
                dspMenuPrincipal.menu(usuario);
                break;
            default:
                System.out.println("Opción no válida.");
                dspUsuarios.menu(usuario);
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
                    //La contraseña no se muestra por las dudas
        }
    }

    public static void usuarios(Usuario usuario) throws SQLException {
        if (usuario.getRol() == 3) {
            displayUsuarios();
        }
    }
}
