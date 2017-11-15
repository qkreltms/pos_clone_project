package logic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import data.DataProvider;
import data.Menu;
import data.Order;

public class OrderDB extends DatabaseAbstract implements DataProvider {
	private Connection con;
	public final static String dbName = "orders";
	public static String columOrderId = "orders_id";
	private ArrayList<Order> list;

	public OrderDB() {
		con = DBConnection.connect();
	}

	@Override
	public ArrayList<Order> findRecordById (String columName, int id) {
		return select("SELECT * FROM "+ dbName + " WHERE " + columName + " = " + id);
	}

	@Override
	public ArrayList<Order> getAllData() {
		return select("SELECT * FROM "+dbName); 
	}

	@Override
	public ArrayList<Order> select(String query) {
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean insert(Object o) {
		try {
			Order e = (Order) o;
			if (e == null) return false;
			Statement statement = con.createStatement();
			String query = "insert into orders values (orders_s.nextval, " 
					+ "'" + e.getCustId() +"'" + ","
					+ "'" + e.getMenuId() +"'" + ","
					+ "'" + e.getOrderPrice() +"'" + ","
					+ "'" + new Date(System.currentTimeMillis()) + "'" + ")";

			final int SUCCESSFULLY_INSERTED = 1;
			if (statement.executeUpdate(query) == SUCCESSFULLY_INSERTED) {
				statement.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean update(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

}
