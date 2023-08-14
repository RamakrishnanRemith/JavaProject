package common;
import java.sql.DriverManager;

/**
 * DataBaseConnection
 * @author Prem Kumar
 *
 */
public class DataBaseConnection {
	public static java.sql.Connection getConnection() throws Exception {
		java.sql.Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management","root","");
			if(!con.isClosed()) {
				System.out.println("Connected");
			}
			else {
				System.out.println("Not Connected");
			}
		} catch(Exception e1) {
			System.out.println(e1);
		}
		return con;
	}
}