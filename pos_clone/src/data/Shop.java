package data;

public class Shop {
	private int shopId;
	private String shopName;
	private int ownerId;
	
	public Shop(int shopId, int ownerId, String shopName) {
		this.shopId = shopId;
		this.shopName = shopName;
		this.ownerId = ownerId;
	}
	public Shop(int ownerId, String shopName) {
		this.shopName = shopName;
		this.ownerId = ownerId;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
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
}
