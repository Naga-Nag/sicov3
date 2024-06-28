package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import internal.Usuario;
import tui.dspDepartamentos;
import tui.dspUsuarios;

public class MySQLConnector {

  private static Connection con;

  //Conexion a la db sico
  public MySQLConnector() throws SQLException {
    try {
      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sico", "root", "root");
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  //Devuelve la connection, todavia no ley doy uso pero siento que en algun momento me va a ser util
  public static Connection getConnection() {
    return con;
  }

  //Un atajo para hacer un query
  public ResultSet query(String query) throws SQLException {
    try {
      return con.createStatement().executeQuery(query);
    } catch (SQLException ex) {
      throw ex;
    }
  }

  //Un atajo para hacer queries que no devuelven RS
  public void execute(String query) throws SQLException {
    try {
      con.createStatement().execute(query);
    } catch (SQLException ex) {
      throw ex;
    }
  }


  public static Usuario login(String nombre, String password) throws SQLException {
    String query = "SELECT * FROM Usuario WHERE nombre = ? AND password = ?";

    try (var preparedStatement = MySQLConnector.getConnection().prepareStatement(query)) {
      preparedStatement.setString(1, nombre);
      preparedStatement.setString(2, password);
      ResultSet rs = preparedStatement.executeQuery();

      if (rs.next()) {
        return new Usuario(
            rs.getInt("id"),
            rs.getString("nombre"),
            rs.getString("password"),
            rs.getInt("departamento_id"),
            rs.getInt("rol")
        );
      } else {
        return null;
      }
    }
  }

  //Devuelve un ResultSet con todos los usuarios
  public ResultSet getUsuarios() throws SQLException {
    MySQLConnector connector = new MySQLConnector();
    return connector.query("SELECT * FROM Usuario");
  }

  public static void main(String[] args) throws SQLException {
    dspDepartamentos.departamentos();

    //El usuario que haga queries tiene que ser admin en este caso
    Usuario admin = new Usuario(1, "admin", "admin", 1, 3);
    dspUsuarios.usuarios(admin);

    //Deberia resultar en el usuario correcto
    assert (login(admin.getNombre(), admin.getPassword()) != null);
  }
}