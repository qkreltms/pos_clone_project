package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import data.DataProvider;
import data.Shop;

public class ShopDB extends DatabaseBuilder implements DataProvider{
	private Connection con;
	public final static String dbName = "Shop";
	private String columShopId = "shop_id";
	private String columShopOwnerId = "shop_owner_id";
	private String columShopName = "shop_name";	
	private ArrayList<Shop> list;

	public ShopDB() {
		con = DBConnection.connect();
	}
	
	public ArrayList<Shop> findOwnerById (String id) throws SQLException{
		return select("SELECT * FROM "+ dbName + " WHERE " + columShopId + " = " + id);
	}
	
	@Override
	public ArrayList<Shop> getAllData() throws SQLException {
		return select("SELECT * FROM " + dbName); 
	}
	
	@Override
	public ArrayList<Shop> select(String query) throws SQLException {
		PreparedStatement preparedStatement = con.prepareStatement(query);
		ResultSet cursor = preparedStatement.executeQuery();
		list = new ArrayList<>();

		System.out.println("=============" + dbName + "=============");
		System.out.println("shopId\townerId\tshopName");
		while(cursor.next()) {
			int shopId = cursor.getInt(1);
			int ownerId = cursor.getInt(2);
			String shopName = cursor.getString(3);

			if (!cursor.wasNull()) {
				list.add(new Shop(shopId, ownerId, shopName));
				String result = shopId + "\t" + ownerId + "\t" + shopName;
				System.out.println(result);	
			}
		}

		con.close(); 
		preparedStatement.close();
		cursor.close();

		return list;
	}

	@Override
	public boolean update(String query) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<?> delete(String query) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Object o) throws SQLException {
		Shop e = (Shop) o;
		if (e == null) return false;
		Statement statement = con.createStatement();
		String query = "insert into shop values (shop_s.nextval, " 
				+ "'" + e.getOwnerId() +"'" + ","
				+ "'" + e.getShopName() + "'" + ")";
		
		if (statement.executeUpdate(query) == 1) return true;
		else return false;
	}

}
