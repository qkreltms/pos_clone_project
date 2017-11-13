package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import data.DataProvider;
import data.Menu;

public class MenuDB extends DatabaseBuilder implements DataProvider {
	private Connection con;
	public final static String dbName = "menu";
	private String columMenuId = "menu_id";
	private ArrayList<Menu> list;

	public MenuDB() {
		con = DBConnection.connect();
	}
	
	public ArrayList<Menu> findOwnerById (String id) throws SQLException{
		return select("SELECT * FROM "+ dbName + " WHERE " + columMenuId + " = " + id);
	}
	@Override
	public ArrayList<Menu> getAllData() throws SQLException {
		return select("SELECT * FROM "+dbName); 
	}
	
	@Override
	public ArrayList<Menu> select(String query) throws SQLException {
		PreparedStatement preparedStatement = con.prepareStatement(query);
		ResultSet cursor = preparedStatement.executeQuery();
		list = new ArrayList<>();

		System.out.println("=============" + dbName + "=============");
		System.out.println("menuId\tshopId\tmenuName\tmenuPrice");
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

		con.close(); 
		preparedStatement.close();
		cursor.close();

		return list;
	}

	@Override
	public ArrayList<?> delete(String query) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(String query) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(Object o) throws SQLException {
		Menu e = (Menu) o;
		if (e == null) return false;
		Statement statement = con.createStatement();
		String query = "insert into menu values (menu_s.nextval, " 
				+ "'" + e.getShopId() +"'" + ","
				+ "'" + e.getMenuName() +"'" + ","
				+ "'" + e.getMenuPrice() + "'" + ")";
		
		if (statement.executeUpdate(query) == 1) return true;
		else return false;
	}

}
