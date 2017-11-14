package data;

import java.sql.Date;

public class Order {
	private int orderId;
	private int custId;
	private int MenuId;
	private int orderPrice;
	private Date orderDate;
	
	public Order(int orderId, int custId, int MenuId, int orderPrice, Date orderDate) {
		this.orderId = orderId;
		this.custId = custId;
		this.MenuId = MenuId;
		this.orderPrice = orderPrice;
		this.orderDate = orderDate;
	}
	public Order(int custId, int MenuId, int orderPrice) {
		this.custId = custId;
		this.MenuId = MenuId;
		this.orderPrice = orderPrice;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
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
	public int getMenuId() {
		return MenuId;
	}
	public void setMenuId(int menuId) {
		MenuId = menuId;
	}
}
