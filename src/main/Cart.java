package main;

public class Cart {
	private String id;
	private Integer qty;
	private Integer price;
	private Integer total;
	
	public Cart(String id, Integer qty, Integer price, Integer total) {
		super();
		this.id = id;
		this.qty = qty;
		this.price = price;
		this.total = total;
	
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}

	
	
	
	
}
