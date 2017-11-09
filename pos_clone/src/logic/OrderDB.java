package logic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDB {
	Connection con;
	PreparedStatement preparedStatement;
	ResultSet cursor;
	String dbName = "Orders";
	
	public void getAllData() {
		try {
			con = DBConnection.connect();
			
			String query = "SELECT * FROM "+dbName;
			preparedStatement = con.prepareStatement(query);
			cursor = preparedStatement.executeQuery();
			
			System.out.println("=============" + dbName + "=============");
			System.out.println("orderId\tcustId\tmenuId\torderPrice\tdate");
			while(cursor.next()) {
				int orderId = cursor.getInt(1);
				int custId = cursor.getInt(2);
				int menuId = cursor.getInt(3);
				int orderPrice = cursor.getInt(4);
				Date date = cursor.getDate(5);
				
				String result = orderId + "\t" + custId + "\t" + menuId + "\t" + orderPrice + "\t\t" + date;
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
