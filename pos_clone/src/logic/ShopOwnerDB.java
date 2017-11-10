package logic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.Customer;
import data.ShopOwner;
import data.ShopOwnerDataProvider;

public class ShopOwnerDB implements ShopOwnerDataProvider{
	private Connection con;
	public static String dbName = "Shop_owner";
	private ArrayList<ShopOwner> custList;

	public ShopOwnerDB() {
		con = DBConnection.connect();
	}
	
	@Override
	public ArrayList<ShopOwner> getAllData() throws SQLException {
		return select("SELECT * FROM "+dbName); 
	}

	public ArrayList<ShopOwner> select(String query) throws SQLException {
		PreparedStatement preparedStatement = con.prepareStatement(query);
		ResultSet cursor = preparedStatement.executeQuery();
		custList = new ArrayList<>();
		
		System.out.println("=============" + dbName + "=============");
		System.out.println("id\tname\tphoneNum");
		while(cursor.next()) {
			if (cursor.wasNull()) {
				System.out.println(dbName + "DB에 아무런 값이 들어있지 않습니다.");
			} else {
				int id = cursor.getInt(1);
				String name = cursor.getString(2);
				String phoneNum = cursor.getString(3);				
			
				custList.add(new ShopOwner(id, name, phoneNum));
				String result = id + "\t" + name + "\t" + phoneNum;
				System.out.println(result);
			}
		}

		con.close(); 
		preparedStatement.close();
		cursor.close();

		return custList;
	}

}