package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static String userId = "system";
	private static String pwd = "111111";
	private static String dbName = "orcl";
	private static String jdbcLocation = "oracle.jdbc.driver.OracleDriver";
	private static Connection con = null;
	public DBConnection() {}

	public static Connection connect() {
		if (con == null) {
			try {
				Class.forName(jdbcLocation); //http://hiddenviewer.tistory.com/114
				String url = "jdbc:oracle:thin:@localhost:1521:" + dbName;
				con = DriverManager.getConnection(url, userId, pwd);
				con.setAutoCommit(false); //autoCommit false because we need to save at last 
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return con;
	}
	
	public static Connection getConnection() { //use this for commit (transaction)
		return con;
	}
}
