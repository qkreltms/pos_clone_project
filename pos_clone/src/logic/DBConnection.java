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
			System.out.println("JDBC 드라이버 로딩에 성공했습니다.");
			String url = "jdbc:oracle:thin:@localhost:1521:" + dbName;
			con = DriverManager.getConnection(url, userId, pwd);
			System.out.println("데이터베이스 연결에 성공했습니다.");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로딩을 실패했습니다. \n드라이버 설치 위치를 확인해 주세요." + e.toString());
		} catch (SQLException e) {
			System.out.println("DB 접속을 실패하였습니다. \nID와 PW를 확인해 주세요." + e.toString());
		} catch (Exception e) {
			System.out.println("알 수 없는 에러");
			e.printStackTrace();
		}
		
		return con;
	}
}
