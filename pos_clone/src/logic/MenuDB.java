package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.DataProvider;
import model.Menu;
import model.Stock;


public class MenuDB extends DatabaseAbstract implements DataProvider {
	private Connection con;
	public final static String dbName = "menu";
	public static String columMenuId = "menu_id";
	private ArrayList<Menu> list;

	public MenuDB() {
		try {
			con = DBConnection.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Menu> findRecordBy (String columName, int id) {
		return select("SELECT * FROM "+ dbName + " WHERE " + columName + " = " + id);
	}
	@Override
	public ArrayList<Menu> getAllData() {
		return select("SELECT * FROM "+dbName); 
	}

	@Override
	public ArrayList<Menu> select(String query) {
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			ResultSet cursor = preparedStatement.executeQuery();
			list = new ArrayList<>();

			System.out.println("=============" + dbName + "=============");
			System.out.println("menuId\tshopId\tmenuName\t\tmenuPrice");
			while(cursor.next()) {
				int menuId = cursor.getInt(1);
				int shopId = cursor.getInt(2);
				String menuName = cursor.getString(3);
				int menuPrice = cursor.getInt(4);

				if (!cursor.wasNull()) {
					list.add(new Menu(menuId, menuName, menuPrice, shopId));
					String result = menuId + "\t" + shopId + "\t" + menuName + "\t" + menuPrice;
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
			Menu e = (Menu) o;
			if (e == null) return false;
			Statement statement = con.createStatement();
			
			String query = "insert into menu values (menu_s.nextval, " 
					+ "'" + e.getShopId() +"'" + ","
					+ "'" + e.getMenuName() +"'" + ","
					+ "'" + e.getMenuPrice() + "'" + ")";
			
			String query2 = "insert into menu_stock values (menu_stock_s.nextval, " 
					+  "menu_s.currval" + ","
					+  e.getShopId() + ","
					+ 0 + ")";
			
			statement.executeUpdate(query);
			statement.executeUpdate(query2);
			
			statement.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public boolean update(Object o) {
		Menu m = (Menu) o;
		String updateSQL = "update menu set menu_name = " 
		+ "'" + m.getMenuName() + "'" 
		+ ", menu_price ="
		+ m.getMenuPrice() 
		+ ", shop_id ="
		+ m.getShopId() +
		" where menu_id = " 
		+ m.getMenuId();
		
		try {
		Statement statement = con.createStatement();
		statement.executeUpdate(updateSQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean delete(Object o) {
		try {
		Statement statement = con.createStatement();
		Menu m = (Menu) o;
		
		String query = "delete from menu_stock where menu_id = "
		+ m.getMenuId();
		
		String query2 = "delete from menu where menu_id = "
		+ m.getMenuId();
		
		statement.executeUpdate(query);
		statement.executeUpdate(query2);
		
		return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public long getMaxId() {
		try {
			long maxId = 0;
			String query = "select max(menu_id) from menu";
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
