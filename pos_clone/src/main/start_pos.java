package main;

import java.sql.Date;
import java.sql.SQLException;
import data.Order;

import logic.OrderDB;
import logic.ShopDB;
import logic.ShopOwnerDB;

public class start_pos {
	public static void main(String[] args) throws SQLException {
		ShopDB shopDB = new ShopDB();
		ShopOwnerDB shopOwnerDB = new ShopOwnerDB();
		//TODO: update, delete �����
////	�� ���� Ȯ��
		OrderDB orderDB = new OrderDB();
		orderDB.insert(new Order(1, 1, 20000, new Date(System.currentTimeMillis())));
		orderDB.getAllData();
	}
}
