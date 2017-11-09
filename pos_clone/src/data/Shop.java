package data;

public class Shop {
	private int shopId;
	private String shopName;
	private int shopOwnerId;
	
	public Shop(int shopId, String shopName, int shopOwnerId) {
		this.shopId = shopId;
		this.shopName = shopName;
		this.shopOwnerId = shopOwnerId;
	}
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public int getShopOwnerId() {
		return shopOwnerId;
	}
	public void setShopOwnerId(int shopOwnerId) {
		this.shopOwnerId = shopOwnerId;
	}
}
