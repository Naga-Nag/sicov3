import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLConnector {

    private static Connection con;

    public static void main(String args[]) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sico", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Obra");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Connection getConnection() {
        return con;
    }
}
