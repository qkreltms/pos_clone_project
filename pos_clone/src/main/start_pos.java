package main;

import data.DataProvider;
import data.Order;

import logic.OrderDB;
import logic.ShopDB;
import logic.ShopOwnerDB;

public class start_pos {
	public static void main(String[] args) {
		ShopDB shopDB = new ShopDB();
		ShopOwnerDB shopOwnerDB = new ShopOwnerDB();
		//TODO: 아이디 1씩 증가하도록 수정
		//TODO: update, delete 만들기
////	값 들어갔나 확인
		try {
			
		DataProvider orderDB = new OrderDB();
		orderDB.insert(new Order(1, 1, 250000));
		orderDB.getAllData();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
