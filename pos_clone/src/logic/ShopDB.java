package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.Shop;
import data.ShopDataProvider;

public class ShopDB implements ShopDataProvider{
	private Connection con;
	public final static String dbName = "Shop";
	private ArrayList<Shop> custList;

	public ShopDB() {
		con = DBConnection.connect();
	}

	@Override
	public ArrayList<Shop> getAllData() throws SQLException {
		return select("SELECT * FROM " + ShopOwnerDB.dbName + " ow, " + dbName + " sh WHERE sh.shop_owner_id = ow.shop_owner_id"); 
	}

	public ArrayList<Shop> select(String query) throws SQLException {
		PreparedStatement preparedStatement = con.prepareStatement(query);
		ResultSet cursor = preparedStatement.executeQuery();
		custList = new ArrayList<>();

		System.out.println("=============" + dbName + "=============");
		System.out.println("shopId\townerId\tshopName\townerName\townerPhoneNum");
		while(cursor.next()) {
			int shopId = cursor.getInt(1);
			String ownerName = cursor.getString(2);
			String ownerPhoneNum = cursor.getString(3);
			int ownerId = cursor.getInt(4);
			String shopName = cursor.getString(6);

			if (!cursor.wasNull()) {
				custList.add(new Shop(shopId, ownerId, shopName, ownerName, ownerPhoneNum));
				String result = shopId + "\t" + ownerId + "\t" + shopName +"\t\t" + ownerName + "\t\t" + ownerPhoneNum;
				System.out.println(result);	
			}
		}

		con.close(); 
		preparedStatement.close();
		cursor.close();

		return custList;
	}
}
