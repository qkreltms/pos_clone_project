package logic;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import data.DataProvider;
import data.Order;

public class OrderDB extends DatabaseAbstract implements DataProvider{
	private Connection con;
	public final static String dbName = "orders";
	private String columOrderId = "orders_id";
	private ArrayList<Order> list;

	public OrderDB() {
		con = DBConnection.connect();
	}
	
	@Override
	public ArrayList<Order> findRecordById (String id) throws SQLException{
		return select("SELECT * FROM "+ dbName + " WHERE " + columOrderId + " = " + id);
	}
	
	@Override
	public ArrayList<Order> getAllData() throws SQLException {
		return select("SELECT * FROM "+dbName); 
	}
	
	@Override
	public ArrayList<Order> select(String query) throws SQLException {
		PreparedStatement preparedStatement = con.prepareStatement(query);
		ResultSet cursor = preparedStatement.executeQuery();
		list = new ArrayList<>();

		System.out.println("=============" + dbName + "=============");
		System.out.println("orderId\tcustId\tmenuId\torderPrice\tdate");
		while(cursor.next()) {
			int orderId = cursor.getInt(1);
			int custId = cursor.getInt(2);
			int menuId = cursor.getInt(3);
			int orderPrice = cursor.getInt(4);
			Date date = cursor.getDate(5);

			if (!cursor.wasNull()) {
				list.add(new Order(orderId, custId, menuId, orderPrice, date));
				String result = orderId + "\t" + custId + "\t" + menuId + "\t" + orderPrice + "\t\t" + date;
				System.out.println(result);
			}
		}

		preparedStatement.close();
		cursor.close();

		return list;
	}



	
	
	/*
	 * 
	 * @»ç¿ë¹ý	orderDB.insert(new Order(1, 1, 20000, new Date(System.currentTimeMillis())));
	 */
	@Override
	public boolean insert(Object o) throws SQLException {
		Order e = (Order) o;
		if (e == null) return false;
		Statement statement = con.createStatement();
		String query = "insert into orders values (orders_s.nextval, " 
				+ "'" + e.getCustId() +"'" + ","
				+ "'" + e.getMenuId() +"'" + ","
				+ "'" + e.getOrderPrice() +"'" + ","
				+ "'" + new Date(System.currentTimeMillis()) + "'" + ")";
		
		
		if (statement.executeUpdate(query) == 1) {
			statement.close();
			return true;
		}
		else return false;
	}

	@Override
	public boolean update(Object o) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Object o) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
}
