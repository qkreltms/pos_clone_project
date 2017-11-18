package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Customer;
import model.DataProvider;
import model.Order;
import model.Shop;

public class CustomerDB extends DatabaseAbstract implements DataProvider {
	private Connection con;
	public final static String dbName = "Customer";
	public static String columCustomerId = "customer_id";
	private ArrayList<Customer> list;

	public CustomerDB()  {
		try {
			con = DBConnection.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Customer> findRecordBy (String columName, int id) {
		return select("SELECT * FROM "+ dbName + " WHERE " + columName + " = " + id);
	}


	@Override
	public ArrayList<Customer> getAllData() {
		return select("SELECT * FROM "+dbName); 
	}

	@Override
	public ArrayList<Customer> select(String query) {
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			ResultSet cursor = preparedStatement.executeQuery();
			list = new ArrayList<>();

			System.out.println("=============" + dbName + "=============");
			System.out.println("id\tname\tphone_num\tcard_num");
			while(cursor.next()) {
				int id = cursor.getInt(1);
				String name = cursor.getString(2);
				String phoneNum = cursor.getString(3);
				String cardNum = cursor.getString(4);

				if (!cursor.wasNull()) {
					list.add(new Customer(id, name, phoneNum, cardNum));
					String result = id + "\t" + name + "\t" + phoneNum + "\t" + cardNum;
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
			Customer e = (Customer) o;
			if (e == null) return false;
			Statement statement = con.createStatement();
			String query = "insert into customer values (customer_s.nextval, " 
					+ "'" + e.getCustName() +"'" + ","
					+ "'" + e.getCustPhoneNumber() +"'" + ","
					+ "'" + e.getCustCardNumber() + "'" + ")";

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

	
	public long getMaxId() {
		try {
			String query = "select max(customer_id) from customer";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			ResultSet cursor = preparedStatement.executeQuery();
			long maxId = 0;
			
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
