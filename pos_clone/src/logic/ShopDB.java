package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.DataProvider;
import model.Shop;

public class ShopDB extends DatabaseAbstract implements DataProvider {
	private Connection con;
	public final static String dbName = "Shop";
	public static String columShopId = "shop_id";
	public static String columShopOwnerId = "shop_owner_id";
	private String columShopName = "shop_name";	
	private ArrayList<Shop> list;

	public ShopDB() {
		con = DBConnection.connect();
	}

	@Override
	public ArrayList<Shop> findRecordById (String columName, int id) {
		return select("SELECT * FROM "+ dbName + " WHERE " + columName + " = " + id);
	}

	@Override
	public ArrayList<Shop> getAllData() {
		return select("SELECT * FROM " + dbName); 
	}

	@Override
	public ArrayList<Shop> select(String query) {
		try {
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
			Shop e = (Shop) o;
			if (e == null) return false;
			Statement statement = con.createStatement();
			String query = "insert into shop values (shop_s.nextval, " 
					+ "'" + e.getOwnerId() +"'" + ","
					+ "'" + e.getShopName() + "'" + ")";

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

	@Override
	public long getMaxId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
