import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        // Login section
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        // Login
        MySQLConnector con = new MySQLConnector();
        Usuario usuario = con.login(username, password);
        limpiarDisplay();

        if (usuario != null) {
            System.out.println("Bienvenido " + usuario.getNombre());
            displayMenuPrincipal(usuario);
        } else {
            System.out.println("Error al iniciar sesión.");
        }

        scanner.close();
    }

    private static void displayMenuPrincipal(Usuario usuario) throws SQLException {
        System.out.println("1. Departamentos");
        System.out.println("2. Usuarios");
        System.out.println("4. Salir");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                limpiarDisplay();
                try {
                    displayDepartamentos();
                    displayMenuDepartamentos(usuario);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                limpiarDisplay();
                displayMenuUsuarios(usuario);
                break;
            case 4:
                limpiarDisplay();
                System.exit(0);
                break;
            default:
                limpiarDisplay();
                System.out.println("Opción no válida.");
                displayMenuPrincipal(usuario);
        }

        scanner.close();
    }

    private static void displayDepartamentos() throws SQLException {
        System.out.println("Departamentos");

        MySQLConnector con = new MySQLConnector();
        ResultSet departamentos = con.getDepartamentos();

        while (departamentos.next()) {
            System.out.println(
                    departamentos.getInt(1) + " " + departamentos.getString(2) + " " + departamentos.getString(3));
        }
    }

    private static void displayMenuDepartamentos(Usuario usuario) throws SQLException {
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
                limpiarDisplay();
                displayMenuPrincipal(usuario);
                break;
            default:
                System.out.println("Opción no válida.");
                displayMenuDepartamentos(usuario);
        }

        scanner.close();
    }

    private static void displayMenuUsuarios(Usuario usuario) throws SQLException {
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
                    displayMenuDepartamentos(usuario);
                }

                boolean esAdmin = false;

                if (usuario.esAdmin()) {
                    System.out.println("Administrador: S/N");
                    String admin = usuInput.nextLine();

                    if (admin.equals("S")) {
                        esAdmin = true;
                    }
                    else {
                        esAdmin = false;
                    }
                }

                Usuario user = new Usuario(0, nombre, contra, 0, esAdmin);
                user.save();
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
                limpiarDisplay();
                displayMenuPrincipal(usuario);
                break;
            default:
                System.out.println("Opción no válida.");
                displayMenuUsuarios(usuario);
        }

        scanner.close();
    }

    private static void displayUsuarios() throws SQLException {
        System.out.println("Usuarios");
        
        MySQLConnector con = new MySQLConnector();
        ResultSet usuarios = con.getUsuarios();

        while (usuarios.next()) {
            System.out.println(
                    usuarios.getInt(1) + " " + usuarios.getString(2) + " " + usuarios.getString(3));
        }
    }
    private static void limpiarDisplay() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
