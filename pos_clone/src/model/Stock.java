package model;

public class Stock {
	private int stockId = 0;
	private int menuId = 0;
	private int shopId = 0;
	private int quantity = 0;
	
	public Stock(int stockId, int menuId, int shopId, int quantity) {
		this.stockId = stockId;
		this.menuId = menuId;
		this.shopId = shopId;
		this.quantity = quantity;
	}
	public Stock(int menuId, int shopId, int quantity) {
		this.menuId = menuId;
		this.shopId = shopId;
		this.quantity = quantity;
	}
	public Stock(int menuId, int quantity) {
		this.menuId = menuId;
		this.quantity = quantity;
	}
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
