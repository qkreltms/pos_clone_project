//http://all-record.tistory.com/69 참고

package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDB {
	Connection con;
	PreparedStatement preparedStatement;
	ResultSet cursor;
	String dbName = "Customer";
	
	public void getAllData() {
		try {
			con = DBConnection.connect();
			
			String query = "SELECT * FROM "+dbName;
			preparedStatement = con.prepareStatement(query);
			cursor = preparedStatement.executeQuery();
			
			System.out.println("=============" + dbName + "=============");
			System.out.println("id\tname\tphone_num\tcard_num");
			while(cursor.next()) {
				int id = cursor.getInt(1);
				String name = cursor.getString(2);
				String phoneNum = cursor.getString(3);
				String cardNum = cursor.getString(4);
				String result = id + "\t" + name + "\t" + phoneNum + "\t" + cardNum;
				System.out.println(result);	
			}
		} catch (SQLException e) {
			System.out.println("sql문 에러 발생. query를 확인해주세요.");
			e.printStackTrace();
		} finally {
			try { 
				con.close(); 
				preparedStatement.close();
				cursor.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}	
