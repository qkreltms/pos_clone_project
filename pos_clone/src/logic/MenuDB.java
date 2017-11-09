package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuDB {
	Connection con;
	PreparedStatement preparedStatement;
	ResultSet cursor;
	String dbName = "Menu";
	
	public void getAllData() {
		try {
			con = DBConnection.connect();
			
			String query = "SELECT * FROM "+dbName;
			preparedStatement = con.prepareStatement(query);
			cursor = preparedStatement.executeQuery();
			
			System.out.println("=============" + dbName + "=============");
			System.out.println("menuId\tshopId\tmenuName\tmenuPrice");
			while(cursor.next()) {
				int menuId = cursor.getInt(1);
				int shopId = cursor.getInt(2);
				String menuName = cursor.getString(3);
				int menuPrice = cursor.getInt(4);
				String result = menuId + "\t" + shopId + "\t" + menuName + "\t\t" + menuPrice;
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
