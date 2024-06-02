package tui;
import java.sql.SQLException;
import java.util.Scanner;

import db.MySQLConnector;
import internal.Usuario;

public class dspLogin {
    public static Usuario display() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        

        MySQLConnector con = new MySQLConnector();
        try {
            Usuario usuario = con.login(username, password);
            scanner.close();
            return usuario;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        scanner.close();
        return null;
    }
}
