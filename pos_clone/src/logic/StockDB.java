package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.DataProvider;
import model.Stock;

public class StockDB extends DatabaseAbstract implements DataProvider{
	private Connection con;
	public final static String dbName = "menu_stock";
	public static String columMenuId = "menu_id";
	private ArrayList<Stock> list;

	public StockDB() {
		try {
			con = DBConnection.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Stock> findRecordBy (String columName, int id) {
		return select("SELECT * FROM "+ dbName + " WHERE " + columName + " = " + id);
	}
	
	@Override
	public ArrayList<Stock> getAllData() {
		return select("SELECT * FROM "+dbName); 
	}

	@Override
	public ArrayList<Stock> select(String query) {
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			ResultSet cursor = preparedStatement.executeQuery();
			list = new ArrayList<>();

			System.out.println("=============" + dbName + "=============");
			System.out.println("stockId\tmenuId\tshopId\tquantity");
			while(cursor.next()) {
				int stockId = cursor.getInt(1);
				int menuId = cursor.getInt(2);
				int shopId = cursor.getInt(3);
				int quantity = cursor.getInt(4);

				if (!cursor.wasNull()) {
					list.add(new Stock(stockId, menuId, shopId, quantity));
					String result = stockId + "\t" + menuId + "\t" + shopId + "\t" + quantity;
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
			Stock e = (Stock) o;
			if (e == null) return false;
			Statement statement;

			statement = con.createStatement();

			String query = "insert into menu values (menu_s.nextval, " 
					+ "'" + e.getMenuId() +"'" + ","
					+ "'" + e.getShopId() +"'" + ","
					+ "'" + e.getQuantity() + "'" + ")";

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
