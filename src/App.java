
import java.sql.ResultSet;

public class App {
    public static void main(String[] args) throws Exception {
        MySQLConnector con = new MySQLConnector();
        ResultSet depa = con.getDepartamentos();

        while (depa.next()) {
            System.out.println(depa.getInt(1) + " " + depa.getString(2) + " " + depa.getString(3));
        }
    }
}
