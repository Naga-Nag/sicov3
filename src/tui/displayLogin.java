package tui;
import java.sql.SQLException;
import java.util.Scanner;
import internal.MySQLConnector;

public class displayLogin {
    public void display() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        scanner.close();

        MySQLConnector con = new MySQLConnector();
        try {
            con.login(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
