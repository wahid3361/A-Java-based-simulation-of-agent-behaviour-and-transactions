package eCommerce;

public class ProductWithRetailer {
	String name = "";
	int requestedQty = 0;
	int price = 0;
	int totalPayable = 0;
	String retailerID = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRequestedQty() {
		return requestedQty;
	}

	public void setRequestedQty(int requestedQty) {
		this.requestedQty = requestedQty;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getTotalPayable() {
		return totalPayable;
	}

	public void setTotalPayable(int totalPayable) {
		this.totalPayable = totalPayable;
	}

	public String getRetailerID() {
		return retailerID;
	}

	public void setRetailerID(String retailerID) {
		this.retailerID = retailerID;
	}

	public ProductWithRetailer(String name, int requestedQty, int price, int totalPayable, String retailerID) {
		super();
		this.name = name;
		this.requestedQty = requestedQty;
		this.price = price;
		this.totalPayable = totalPayable;
		this.retailerID = retailerID;
	}

	public ProductWithRetailer() {
		super();
	}

}
