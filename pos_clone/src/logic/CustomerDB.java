package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import data.CustDataProvider;
import data.Customer;

public class CustomerDB implements CustDataProvider{
	private Connection con;
	public final static String dbName = "Customer";
	private ArrayList<Customer> custList;

	public CustomerDB() {
		con = DBConnection.connect();
	}
	
	@Override
	public ArrayList<Customer> getAllData() throws SQLException {
		return select("SELECT * FROM "+dbName); 
	}
	
	public ArrayList<Customer> select(String query) throws SQLException {
		PreparedStatement preparedStatement = con.prepareStatement(query);
		ResultSet cursor = preparedStatement.executeQuery();
		custList = new ArrayList<>();
		
		System.out.println("=============" + dbName + "=============");
		System.out.println("id\tname\tphone_num\tcard_num");
		while(cursor.next()) {
			if (cursor.wasNull()) {
				System.out.println(dbName + "DB에 아무런 값이 들어있지 않습니다.");
			} else {
				int id = cursor.getInt(1);
				String name = cursor.getString(2);
				String phoneNum = cursor.getString(3);
				String cardNum = cursor.getString(4);
			
				custList.add(new Customer(id, name, phoneNum, cardNum));
				String result = id + "\t" + name + "\t" + phoneNum + "\t" + cardNum;
				System.out.println(result);
			}
		}

		con.close(); 
		preparedStatement.close();
		cursor.close();

		return custList;
	}
}	
