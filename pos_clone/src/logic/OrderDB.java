package logic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.DataProvider;
import model.Menu;
import model.Order;

public class OrderDB extends DatabaseAbstract implements DataProvider {
	private Connection con;
	public final static String dbName = "orders";
	public static String columOrderId = "orders_id";
	public static String columShopId = "orders_shop_id";
	private ArrayList<Order> list;

	public OrderDB() {
		try {
			con = DBConnection.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Order> findRecordBy (String columName, int id) {
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
			ByteArrayInputStream bais;
			ObjectInput ins;
			
			System.out.println("=============" + dbName + "=============");
			System.out.println("orderId\tcustId\tdate\t\tmenuList");
			while(cursor.next()) {

				int orderId = cursor.getInt(1);
				int custId = cursor.getInt(2);
				Date date = cursor.getDate(3);
				bais = new ByteArrayInputStream(cursor.getBytes(4));
				ins = new ObjectInputStream(bais);
				@SuppressWarnings("unchecked")
				ArrayList<Menu> menuList = (ArrayList<Menu>) ins.readObject();
				
				if (!cursor.wasNull()) {
					list.add(new Order(orderId, custId, date, menuList));
					String result = orderId + "\t" + custId  + "\t" + date + "\t" + menuList.get(0).getMenuName();
					System.out.println(result);
				}
			}

			preparedStatement.close();
			cursor.close();
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean insert(Object o) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutput oos = new ObjectOutputStream(bos);
			Order e = (Order) o;
			
			oos.writeObject(e.getMenuList());
			oos.flush();
			byte[] menuList = bos.toByteArray();
			PreparedStatement ps;
			String query = "insert into orders values (?, ?, ?, ?)";
			ps = con.prepareStatement(query);
			ps.setInt(1, (int)getMaxId()+1);
			ps.setInt(2, e.getCustId());
			ps.setDate(3, new Date(System.currentTimeMillis()));
			ps.setObject(4, menuList);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
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
	
	public long getMaxId() {
		try {
			long maxId = 0;
			String query = "select max(orders_id) from orders";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			ResultSet cursor = preparedStatement.executeQuery();
			
			while(cursor.next()) {
				maxId = cursor.getInt(1);
			}

			preparedStatement.close();
			cursor.close();
			
			if (maxId == 0) return 1;
			return maxId;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}
}
