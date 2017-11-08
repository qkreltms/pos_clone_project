package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	static String userId = "madang";
	static String pwd = "madang";
	static String dbName = "orcl";
	static String jdbcLocation = "oracle.jdbc.driver.OracleDriver";
	
	public DBConnection() {}
	
	public static Connection connect() {
		Connection con = null;
		try {
			Class.forName(jdbcLocation); //http://hiddenviewer.tistory.com/114
			System.out.println("JDBC ����̹� �ε��� �����߽��ϴ�.");
			String url = "jdbc:oracle:thin:@localhost:1521:" + dbName;
			con = DriverManager.getConnection(url, userId, pwd);
			System.out.println("�����ͺ��̽� ���ῡ �����߽��ϴ�.");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC ����̹� �ε��� �����߽��ϴ�. \n����̹� ��ġ ��ġ�� Ȯ���� �ּ���." + e.toString());
		} catch (SQLException e) {
			System.out.println("DB ������ �����Ͽ����ϴ�. \nID�� PW�� Ȯ���� �ּ���." + e.toString());
		} catch (Exception e) {
			System.out.println("�� �� ���� ����");
			e.printStackTrace();
		}
		
		return con;
	}
}
