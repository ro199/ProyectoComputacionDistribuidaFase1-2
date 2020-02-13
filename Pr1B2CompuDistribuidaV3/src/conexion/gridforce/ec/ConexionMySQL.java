package conexion.gridforce.ec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
	
	Connection con = null;
	
	public Connection conexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rhth?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root", "");
			if (!con.isClosed())
				System.out.println("Successfully connected to MySQL server...");
		} catch(Exception e) {
			System.err.println("Exception: " + e.getMessage());
		} /*finally {
			try {
				if (con != null)
					con.close();
			} catch(SQLException e) {}
		}*/
		
		return con;
	}
	public static void main(String args[]) {

		
	}
}
