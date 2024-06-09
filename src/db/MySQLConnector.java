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

  public MySQLConnector() throws SQLException {
    try {
      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sico", "root", "root");
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  public static Connection getConnection() {
    return con;
  }

  public ResultSet query(String query) throws SQLException {
    try {
      return con.createStatement().executeQuery(query);
    } catch (SQLException ex) {
      throw ex;
    }
  }

  public void execute(String query) throws SQLException {
    try {
      con.createStatement().execute(query);
    } catch (SQLException ex) {
      throw ex;
    }
  }

  public ResultSet getDepartamentos() throws SQLException {
    MySQLConnector connector = new MySQLConnector();
    return connector.query("SELECT * FROM Departamento");
  }

  public String print(ResultSet rs) throws SQLException {
    StringBuilder sb = new StringBuilder();
    while (rs.next()) {
      sb.append(rs.getInt("id"));
      sb.append(" ");
      sb.append(rs.getString("cuatrigrama"));
      sb.append(" ");
      sb.append(rs.getString("nombre"));
      sb.append("\n");
    }
    return sb.toString();
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
            rs.getBoolean("admin")

        );
      } else {
        return null;
      }
    }
  }

  public ResultSet getUsuarios() throws SQLException {
    MySQLConnector connector = new MySQLConnector();
    return connector.query("SELECT * FROM Usuario");
  }

  public static void main(String[] args) throws SQLException {
    dspDepartamentos.departamentos();

    //Busco que el usuario que haga queries sea admin en este caso
    dspUsuarios.usuarios(new Usuario(1, "admin", "admin", 1, true));
  }
}