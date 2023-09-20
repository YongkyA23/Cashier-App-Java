package main;

public class Products {
	private String id;
	private String type;
	private String name;
	private Integer price;

	
	public Products(String id, String type, String name, Integer price) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.price = price;
	}
	
	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	
	
	
}
