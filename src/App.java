
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        // Credenciales
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

            displayMenuPrincipal();
        } else {
            System.out.println("Error al iniciar sesión.");
        }

        scanner.close();
    }

    private static void displayDepartamentos() throws SQLException {
        MySQLConnector con = new MySQLConnector();
        ResultSet departamentos = con.getDepartamentos();

        while (departamentos.next()) {
            System.out.println(departamentos.getInt(1) + " " + departamentos.getString(2) + " " + departamentos.getString(3));
        }
    }

    private static void displayMenuPrincipal() {
        System.out.println("1. Mostrar departamentos");
        System.out.println("2. Salir");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                limpiarDisplay();
                try {
                    displayDepartamentos();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                System.exit(0);
                break;
        }
    }

    private static void limpiarDisplay() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
