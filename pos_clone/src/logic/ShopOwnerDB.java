package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.DataProvider;
import model.Shop;
import model.ShopOwner;

public class ShopOwnerDB extends DatabaseAbstract implements DataProvider {
	private Connection con;
	public static String dbName = "Shop_owner";
	public static String columShopOwnerId = "shop_owner_id";
	private String columShopOwnerName = "shop_owner_name";
	private String columShopOwnerPhoneNum = "shop_owner_phone_num";

	private ArrayList<ShopOwner> list;

	public ShopOwnerDB() {
		con = DBConnection.connect();
	}

	@Override
	public ArrayList<ShopOwner> findRecordBy (String columName, int id) {
		return select("SELECT * FROM "+ dbName + " WHERE " + columName + " = " + id);
	}


	@Override
	public ArrayList<ShopOwner> getAllData() {
		return select("SELECT * FROM "+ dbName); 
	}

	@Override
	public ArrayList<ShopOwner> select(String query) {
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			ResultSet cursor = preparedStatement.executeQuery();
			list = new ArrayList<>();

			System.out.println("=============" + dbName + "=============");
			System.out.println("id\tname\tphoneNum");
			while(cursor.next()) {
				int id = cursor.getInt(1);
				String name = cursor.getString(2);
				String phoneNum = cursor.getString(3);				

				if (!cursor.wasNull()) {
					list.add(new ShopOwner(id, name, phoneNum));
					String result = id + "\t" + name + "\t" + phoneNum;
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
			ShopOwner e = (ShopOwner) o;
			if (e == null) return false;
			Statement statement = con.createStatement();
			String query = "insert into shop_owner values (shop_owner_s.nextval, " 
					+ "'" + e.getShopOwnerName() +"'" + ","
					+ "'" + e.getShopOwnerPhoneNumber() + "'" + ")";

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