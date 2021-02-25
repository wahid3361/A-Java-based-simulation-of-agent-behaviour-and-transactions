package eCommerce;

public class Product {
	String name = "";
	int availableQty = 0;
	int price = 0;

	// getter setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAvailableQty() {
		return availableQty;
	}

	public void setAvailableQty(int availableQty) {
		this.availableQty = availableQty;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	// cunstroctur
	public Product(String name, int availableQty, int price) {
		super();
		this.name = name;
		this.availableQty = availableQty;
		this.price = price;
	}

	public Product() {
		super();
		this.name = "";
		this.availableQty = 0;
		this.price = 0;
	}

}
