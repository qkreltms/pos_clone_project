package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	static String userId = "system";
	static String pwd = "111111";
	static String dbName = "orcl";
	static String jdbcLocation = "oracle.jdbc.driver.OracleDriver";
	static Connection con = null;
	public DBConnection() {}

	public static Connection connect() {
		//재생성 방지
		if (con == null) {
			try {
				Class.forName(jdbcLocation); //http://hiddenviewer.tistory.com/114
				String url = "jdbc:oracle:thin:@localhost:1521:" + dbName;
				con = DriverManager.getConnection(url, userId, pwd);
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
}
