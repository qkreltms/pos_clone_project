package logic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.Order;
import data.OrderDataProvider;

public class OrderDB implements OrderDataProvider{
	private Connection con;
	public final static String dbName = "Customer";
	private ArrayList<Order> custList;

	public OrderDB() {
		con = DBConnection.connect();
	}
	
	@Override
	public ArrayList<Order> getAllData() throws SQLException {
		return select("SELECT * FROM "+dbName); 
	}
	
	public ArrayList<Order> select(String query) throws SQLException {
		PreparedStatement preparedStatement = con.prepareStatement(query);
		ResultSet cursor = preparedStatement.executeQuery();
		custList = new ArrayList<>();
		
		System.out.println("=============" + dbName + "=============");
		System.out.println("orderId\tcustId\tmenuId\torderPrice\tdate");
		while(cursor.next()) {
			if (cursor.wasNull()) {
				System.out.println(dbName + "DB에 아무런 값이 들어있지 않습니다.");
			} else {
				int orderId = cursor.getInt(1);
				int custId = cursor.getInt(2);
				int menuId = cursor.getInt(3);
				int orderPrice = cursor.getInt(4);
				Date date = cursor.getDate(5);
			
				//custList.add(new Order(id, name, phoneNum, cardNum));
				String result = orderId + "\t" + custId + "\t" + menuId + "\t" + orderPrice + "\t\t" + date;
				System.out.println(result);
			}
		}

		con.close(); 
		preparedStatement.close();
		cursor.close();

		return custList;
	}
}
