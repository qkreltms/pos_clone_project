package pos;

import java.sql.SQLException;
import java.util.ArrayList;

import data.Customer;
import data.Shop;
import logic.CustomerDB;
import logic.MenuDB;
import logic.OrderDB;
import logic.ShopDB;
import logic.ShopOwnerDB;

public class start_pos {
	public static void main(String[] args) throws SQLException {
//		CustomerDB customerDB = new CustomerDB();
//		MenuDB menuDB = new MenuDB();
//		OrderDB orderDB = new OrderDB();
		ShopDB shopDB = new ShopDB();
//		ShopOwnerDB shopOwnerDB = new ShopOwnerDB();
		
//		customerDB.getAllData();
//		menuDB.getAllData();
//		orderDB.getAllData();
//		shopDB.getAllData();
//		shopOwnerDB.getAllData();

////	값 들어갔나 확인

		ArrayList<Shop> shopList = new ArrayList<>();
		shopList = shopDB.getAllData();
		for (int i=0; i<shopList.size(); i++) {
		System.out.println(
				shopList.get(i).getShopId() + " "+
				shopList.get(i).getOwnerId() + 
				shopList.get(i).getShopName() + 
				shopList.get(i).getOnwerName() + 
				shopList.get(i).getOwnerPhoneNum());
		}
//		shopOwnerDB.getAllData();
		
		
//		ArrayList<Customer> custList = new ArrayList<>();
//		custList = customerDB.getAllData();
//		System.out.println(custList.size());
//		System.out.println(custList.get(0).getCustId() + custList.get(0).getCustName() + custList.get(0).getCustPhoneNumber() + custList.get(0).getCustCardNumber());
//		System.out.println("값이 완벽하게 리스트에 들어감");
	}
}
