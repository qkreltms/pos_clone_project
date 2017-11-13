package data;

public class ShopOwner {
	private int shopOwnerId;
	private String shopOwnerPhoneNumber;
	private String shopOwnerName;
	
	public ShopOwner(int shopOwnerId, String shopOwnerName, String shopOwnerPhoneNumber) {
		this.shopOwnerId = shopOwnerId;
		this.shopOwnerPhoneNumber = shopOwnerPhoneNumber;
		this.shopOwnerName = shopOwnerName;
	}
	public ShopOwner(String shopOwnerName, String shopOwnerPhoneNumber) {
		this.shopOwnerPhoneNumber = shopOwnerPhoneNumber;
		this.shopOwnerName = shopOwnerName;
	}
	public int getShopOwnerId() {
		return shopOwnerId;
	}
	public void setShopOwnerId(int shopOwnerId) {
		this.shopOwnerId = shopOwnerId;
	}
	public String getShopOwnerPhoneNumber() {
		return shopOwnerPhoneNumber;
	}
	public void setShopOwnerPhoneNumber(String shopOwnerPhoneNumber) {
		this.shopOwnerPhoneNumber = shopOwnerPhoneNumber;
	}
	public String getShopOwnerName() {
		return shopOwnerName;
	}
	public void setShopOwnerName(String shopOwnerName) {
		this.shopOwnerName = shopOwnerName;
	}
}
