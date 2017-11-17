package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.DataProvider;
import model.Menu;


public class MenuDB extends DatabaseAbstract implements DataProvider {
	private Connection con;
	public final static String dbName = "menu";
	public static String columMenuId = "menu_id";
	private ArrayList<Menu> list;

	public MenuDB() {
		con = DBConnection.connect();
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
			Statement statement;

			statement = con.createStatement();

			String query = "insert into menu values (menu_s.nextval, " 
					+ "'" + e.getShopId() +"'" + ","
					+ "'" + e.getMenuName() +"'" + ","
					+ "'" + e.getMenuPrice() + "'" + ")";

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
