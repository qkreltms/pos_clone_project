package model;

import java.sql.Date;
import java.util.ArrayList;

public class Order {
	private int orderId;
	private int custId;
	private Date orderDate;
	private ArrayList<Menu> menuList;
	
	public Order(int orderId, int custId, Date orderDate, ArrayList<Menu> menuList) {
		this.orderId = orderId;
		this.custId = custId;
		this.orderDate = orderDate;
		this.menuList = menuList;
	}
	//시간이 자동 생성될때 아래것 사용
	public Order(int orderId, int custId, ArrayList<Menu> menuList) {
		this.orderId = orderId;
		this.custId = custId;
		this.menuList = menuList;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public ArrayList<Menu> getMenuList() {
		return menuList;
	}
	public void setMenuList(ArrayList<Menu> menuList) {
		this.menuList = menuList;
	}
}
