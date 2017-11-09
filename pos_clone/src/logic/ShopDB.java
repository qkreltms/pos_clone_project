package logic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShopDB {
	Connection con;
	PreparedStatement preparedStatement;
	ResultSet cursor;
	String dbName = "Shop";
	
	public void getAllData() {
		try {
			con = DBConnection.connect();
			
			String query = "SELECT * FROM "+dbName;
			preparedStatement = con.prepareStatement(query);
			cursor = preparedStatement.executeQuery();
			
			System.out.println("=============" + dbName + "=============");
			System.out.println("shopId\townerId\tname");
			while(cursor.next()) {
				int shopId = cursor.getInt(1);
				int ownerId = cursor.getInt(2);
				String name = cursor.getString(3);
				
				String result = shopId + "\t" + ownerId + "\t" + name;
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
