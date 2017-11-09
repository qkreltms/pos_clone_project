package pos;

import java.util.ArrayList;

import data.Customer;
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
		
		ArrayList<Customer> custList = new ArrayList<>();
		custList = customerDB.getAllData();
		
//		값 들어갔나 확인
		System.out.println(custList.size());
		System.out.println(custList.get(0).getCustId() + custList.get(0).getCustName() + custList.get(0).getCustPhoneNumber() + custList.get(0).getCustCardNumber());
		System.out.println("값이 완벽하게 리스트에 들어감");
	}
}
