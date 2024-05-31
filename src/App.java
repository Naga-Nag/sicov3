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
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Opción no válida.");
                displayMenuPrincipal(usuario); 
        }
    }

    private static void displayDepartamentos() throws SQLException {
        MySQLConnector con = new MySQLConnector();
        ResultSet departamentos = con.getDepartamentos();

        while (departamentos.next()) {
            System.out.println(departamentos.getInt(1) + " " + departamentos.getString(2) + " " + departamentos.getString(3));
        }
    }

    private static void displayMenuDepartamentos(Usuario usuario) throws SQLException {
        System.out.println("Departamentos");
        System.out.println("1. Crear Departamento");
        System.out.println("2. Editar Departamento");
        System.out.println("3. Eliminar Departamento");
        System.out.println("4. Volver");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:

                Usuario nuevoUsuario = new Usuario(); 
                nuevoUsuario.save(); 
                System.out.println("Departamento creado exitosamente.");
                break;
            case 2:
                System.out.println("Editar Departamento (pendiente de implementación)");
                break;
            case 3:
                System.out.println("Eliminar Departamento (pendiente de implementación)");
                break;
            case 4:
                displayMenuPrincipal(usuario);
                break;
            default:
                System.out.println("Opción no válida.");
                displayMenuDepartamentos(usuario);
        }
    }

    private static void limpiarDisplay() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
