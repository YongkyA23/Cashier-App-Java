package main;

public class TransactionDetail {
	private String transactionID;
	private String productID;
	private int quantity;
	private int totalPrice;
	
	
	
	public TransactionDetail(String transactionID, String productID, int quantity, int totalPrice) {
		super();
		this.transactionID = transactionID;
		this.productID = productID;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	
}
