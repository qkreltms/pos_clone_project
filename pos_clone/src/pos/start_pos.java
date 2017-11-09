package pos;

import logic.CustomerDB;
import logic.MenuDB;
import logic.OrderDB;
import logic.ShopDB;
import logic.ShopOwnerDB;

public class start_pos {
	public static void main(String[] args) {
		CustomerDB customerDB = new CustomerDB();
		MenuDB menuDB = new MenuDB();
		OrderDB orderDB = new OrderDB();
		ShopDB shopDB = new ShopDB();
		ShopOwnerDB shopOwnerDB = new ShopOwnerDB();
		
		customerDB.getAllData();
		menuDB.getAllData();
		orderDB.getAllData();
		shopDB.getAllData();
		shopOwnerDB.getAllData();
	}
}
