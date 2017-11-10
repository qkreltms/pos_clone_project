package data;

public class Shop {
	private int shopId;
	private String shopName;
	private int ownerId;
	private String onwerName;
	private String ownerPhoneNum;
	
	public Shop(int shopId, int ownerId, String shopName, String onwerName, String ownerPhoneNum) {
		this.shopId = shopId;
		this.shopName = shopName;
		this.ownerId = ownerId;
		this.onwerName = onwerName;
		this.ownerPhoneNum = ownerPhoneNum;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public String getOnwerName() {
		return onwerName;
	}
	public void setOnwerName(String onwerName) {
		this.onwerName = onwerName;
	}
	public String getOwnerPhoneNum() {
		return ownerPhoneNum;
	}
	public void setOwnerPhoneNum(String ownerPhoneNum) {
		this.ownerPhoneNum = ownerPhoneNum;
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
