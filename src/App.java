import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import db.MySQLConnector;
import internal.Usuario;
import tui.*;
import tui.utils;
public class App {

    public static void main(String[] args) throws SQLException {
        Usuario usuario = tui.dspLogin.display();
        utils.limpiarDisplay();

        if (usuario != null) {
            System.out.println("Bienvenido " + usuario.getNombre());
            tui.dspMenuPrincipal.menu(usuario);
        } else {
            System.out.println("Error al iniciar sesión.");
        }

    }
}
