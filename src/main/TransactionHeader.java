package main;


public class TransactionHeader {
	private String transactionID;
	private String customerID;
	private String staffID;
	private String transactionDate;
	private int totalTransactionPrice;
	
	
	
	public TransactionHeader(String transactionID, String customerID, String staffID, String transactionDate,
			int totalTransactionPrice) {
		super();
		this.transactionID = transactionID;
		this.customerID = customerID;
		this.staffID = staffID;
		this.transactionDate = transactionDate;
		this.totalTransactionPrice = totalTransactionPrice;
	}
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getStaffID() {
		return staffID;
	}
	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}
	
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public int getTotalTransactionPrice() {
		return totalTransactionPrice;
	}
	public void setTotalTransactionPrice(int totalTransactionPrice) {
		this.totalTransactionPrice = totalTransactionPrice;
	}
	
	
	
}
