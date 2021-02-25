package eCommerce;

public class RetailerAgent {
	String name = "";
	String retailerID = "";
	Product[] productList;
	double balance=0;
	

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public RetailerAgent() {
		productList = new Product[5];
		intilizeProducts();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRetailerID() {
		return retailerID;
	}

	public void setRetailerID(String retailerID) {
		this.retailerID = retailerID;
	}

	public Product[] getProductList() {
		return productList;
	}

	public void setProductList(Product[] productList) {
		this.productList = productList;
	}

	public RetailerAgent(String retailerID) {
		super();
		this.retailerID = retailerID;
		productList = new Product[5];
		intilizeProducts();
	}

	public void intilizeProducts() {

		// name , qty, price

		Product p1 = new Product("ink", 50, 25);
		Product p2 = new Product("book", 5, 200);
		Product p3 = new Product("shoe", 10, 1000);
		Product p4 = new Product("bag", 50, 500);
		Product p5 = new Product("pen", 100, 100);

		productList[0] = p1;
		productList[1] = p2;
		productList[2] = p3;
		productList[3] = p4;
		productList[4] = p5;

	}

	public ProductWithRetailer getRequestFromBroker(String[] requestList) {
		ProductWithRetailer pr = null;
		if (acceptRequest()) {

			pr = findProduct(requestList);

		} else if (declineRequest()) {
			System.out.println("Request rejected");
		}

		return pr;

	}

	public boolean acceptRequest() {
		return true;
	}

	public boolean declineRequest() {
		return true;
	}

	public ProductWithRetailer findProduct(String[] requestList) {

		ProductWithRetailer pr = null;

		boolean found = false;
		int minPrice = Integer.parseInt(requestList[0]);
		int maxPrice = Integer.parseInt(requestList[1]);
		String productName = requestList[2];
		int qtyRequested = Integer.parseInt(requestList[3]);
		String deliveryOption = requestList[4];

		for (int i = 0; i < productList.length; ++i) {
			if (productList[i].getPrice() >= minPrice && productList[i].getPrice() <= maxPrice) {
				// price within range

				// System.out.println("\nprice in range");

				// System.out.println("prOrg : " + productList[i].getName() + " , prReq : " +
				// productName);
				if (productList[i].getName().equals(productName)) {
					// product found
					// System.out.println("product found");
					if (productList[i].getAvailableQty() >= qtyRequested) {
						// product available
						// System.out.println("product available");
						System.out.println(
								"\nProduct : " + productName + " , Price per item : " + productList[i].getPrice());

						found = true;

						int payable = productList[i].getPrice() * qtyRequested;
						pr = new ProductWithRetailer(productName, qtyRequested, productList[i].getPrice(), payable,
								retailerID);

					} else {
						// System.out.println("product not avialble");
					}

				} else {
					// System.out.println("product not found");
				}

			} else {
				// System.out.println("Price not in Range");
			}
		}

		if (!found) {
		//	System.out.println("\nNo product found mathing to your specs");
		}

		return pr;
	}

}
