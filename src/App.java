import java.sql.SQLException;
import java.util.Scanner;

import db.MySQLConnector;
import internal.Usuario;
import tui.utils;

public class App {

    public static void main(String[] args) throws SQLException {
        Scanner loginscanner = new Scanner(System.in);
        System.out.println("Username:");
        String username = loginscanner.nextLine();
        System.out.println("Password:");
        String password = loginscanner.nextLine();
        //loginscanner.close(); Por alguna razon que no comprendo, si cierro el scanner aca, no me funciona bien el sig scanner del menu...

        MySQLConnector con = new MySQLConnector();
        try {
            Usuario usuario = con.login(username, password);
            if (usuario == null) {
                System.out.println("Credenciales invalidas");
                System.exit(0);
            }
            utils.limpiarDisplay();
            tui.dspMenuPrincipal.menu(usuario);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loginscanner.close();
    }
}
