import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLConnector {

  private static Connection con;

  public MySQLConnector() throws SQLException {
    try {
      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sico", "root", "root");
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  public Connection getConnection() {
    return con;
  }

  public ResultSet query(String query) throws SQLException {
    try {
      return con.createStatement().executeQuery(query);
    } catch (SQLException ex) {
      throw ex;
    }
  }

  public ResultSet getDepartamentos() throws SQLException {
    MySQLConnector connector = new MySQLConnector();
    return connector.query("SELECT * FROM Departamento");
  }
}