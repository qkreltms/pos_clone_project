package model;

public class Customer {
	private int custId;
	private String custName;
	private String custPhoneNumber;
	private String custCardNumber;
	
	public Customer(int custId, String custName, String custPhoneNumber, String custCardNumber) {
		this.custId = custId;
		this.custName = custName;
		this.custPhoneNumber = custPhoneNumber;
		this.custCardNumber = custCardNumber;
	}
	public Customer(String custName, String custPhoneNumber, String custCardNumber) {
		this.custName = custName;
		this.custPhoneNumber = custPhoneNumber;
		this.custCardNumber = custCardNumber;
	}
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustPhoneNumber() {
		return custPhoneNumber;
	}
	public void setCustPhoneNumber(String custPhoneNumber) {
		this.custPhoneNumber = custPhoneNumber;
	}
	public String getCustCardNumber() {
		return custCardNumber;
	}
	public void setCustCardNumber(String custCardNumber) {
		this.custCardNumber = custCardNumber;
	}

}
