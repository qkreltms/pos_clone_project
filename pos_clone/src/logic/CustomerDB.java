//http://all-record.tistory.com/69 참고

package logic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDB {
	Connection con;
	
	public CustomerDB() {}
	
	private void getAllData() {
		try {
			con = DBConnection.connect();
			Statement statement = con.createStatement();
			String query = "SELECT * FROM Customer";
			ResultSet cursor = statement.executeQuery(query);
			while(cursor.next()) {
				System.out.println("\t"+cursor.getInt(1));
				System.out.println("\t"+cursor.getString(2));				
			}
		} catch (SQLException e) {
			System.out.println("sql문 에러 발생. query를 확인해주세요.");
			e.printStackTrace();
		} finally {
			try { 
				con.close(); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String args[]) {
		CustomerDB cust = new CustomerDB();
		cust.getAllData();
	}
}	
