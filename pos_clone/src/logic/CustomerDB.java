package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import data.Customer;
import data.DataProvider;
import data.Shop;

public class CustomerDB extends DatabaseAbstract implements DataProvider {
	private Connection con;
	public final static String dbName = "Customer";
	public static String columCustomerId = "customer_id";
	private ArrayList<Customer> list;

	public CustomerDB() {
		con = DBConnection.connect();
	}

	@Override
	public ArrayList<Customer> findRecordById (String columName, int id) {
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

}	
