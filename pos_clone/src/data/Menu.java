package data;

public class Menu {
	private int menuId;
	private String menuName;
	private int menuPrice;
	private int shopId;

	public Menu(int menuId, String menuName, int menuPrice,  int shopId) {
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.shopId = shopId;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getMenuPrice() {
		return menuPrice;
	}
	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
}
