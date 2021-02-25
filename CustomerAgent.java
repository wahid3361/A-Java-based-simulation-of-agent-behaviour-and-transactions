package eCommerce;

import java.util.Scanner; // enter the before the start of any class 

public class CustomerAgent {

	String custAgentName = "";
	String custAgentID = "";
	double walletBalance = 0;

	// constructor
	public CustomerAgent(String custAgentName, String custAgentID, double walletBalance) {
		super();
		this.custAgentName = custAgentName;
		this.custAgentID = custAgentID;
		this.walletBalance = walletBalance;
	}

	// getter and setters

	public String getCustAgentName() {
		return custAgentName;
	}

	public void setCustAgentName(String custAgentName) {
		this.custAgentName = custAgentName;
	}

	public String getCustAgentID() {
		return custAgentID;
	}

	public void setCustAgentID(String custAgentID) {
		this.custAgentID = custAgentID;
	}

	public double getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(double walletBalance) {
		this.walletBalance = walletBalance;
	}

	// make request to buy something

	public String[] makeRequestToPurchase() {

		String[] requestList = new String[5];

		Scanner input = new Scanner(System.in); // input is just a random variable to get input

		int minPrice = 0;
		int maxPrice = 0, qty = 0;
		String productName = "";
		String deliveryOption = "";

		// input price range

		System.out.println("Enter Min Price(Must be integer number) : ");
		minPrice = input.nextInt();
		// System.out.println("The Min price you entered is : " + minPrice);

		System.out.println("Enter Max Price(Must be integer number) : ");
		maxPrice = input.nextInt();
		// System.out.println("The Max price you entered is : " + maxPrice);

		// input product name

		System.out.println("Enter Product Name(Must contain alphabets) : ");
		productName = input.next();
		// System.out.println("The product name you entered is : " + productName);

		// input quantity

		System.out.println("Enter product Quantity(Must be integer number) : ");
		qty = input.nextInt();
		// System.out.println("The porduct quantity you entered is : " + qty);

		// input delivery options

		System.out.println("Press '1' for online delivery, Press '2' for pickup: ");
		int delvInput = 0;
		delvInput = input.nextInt();
		if (delvInput == 1) {
			deliveryOption = "online";
		} else if (delvInput == 2) {
			deliveryOption = "pickup";
		} else {
			deliveryOption = "";
		}

		// System.out.println("The delivery option you entered is : " + deliveryOption);

		requestList[0] = String.valueOf(minPrice);
		requestList[1] = String.valueOf(maxPrice);
		requestList[2] = String.valueOf(productName);
		requestList[3] = String.valueOf(qty);
		requestList[4] = String.valueOf(deliveryOption);

		return requestList;

	}

	public void getDealFromBroker(ProductWithRetailer deal, RetailerAgent[] listOfRetailers) throws InterruptedException {
		
		System.out.println("\n ================================= \n");
		
		System.out.println("\n Finding best deal.......\n");
		
		// broker agent invite retailers
		Thread.sleep(1000); //wait for 1 second
		
		//display best deal
		System.out.println("\nBest deal found by Broker agent\n");
		System.out.println("Product name : " + deal.getName());
		System.out.println("Price per item : " + deal.getPrice());
		System.out.println("Total payable : " + deal.getTotalPayable());
		System.out.println("Retailer id : " + deal.getRetailerID());
		System.out.println("\nIf you want to accept it press 'y' else press 'n' : ");

		String choice = "";
		Scanner input = new Scanner(System.in); // input is just a random variable to get input
		choice = input.next();
		if (choice.equals("y") || choice.equals("Y")) {
			// accept it

			acceptDeal(deal, listOfRetailers);

		} else if (choice.equals("n") || choice.equals("N")) {
			// reject it
		} else {
			System.out.println("Wrong input");
		}

	}

	public void acceptDeal(ProductWithRetailer deal, RetailerAgent[] listOfRetailers) {
		System.out.println("\n\nCustomer agent's Wallet balance before purchase : " + this.walletBalance);
		if(this.walletBalance>=deal.getTotalPayable()) {
			this.walletBalance = this.walletBalance - deal.getTotalPayable();

			for (int i = 0; i < listOfRetailers.length; ++i) {
				if (listOfRetailers[i].getRetailerID().equals(deal.getRetailerID())) {
					// retailer found

					// now remove item from its product list

					int itemListSize = listOfRetailers[i].getProductList().length;

					for (int j = 0; j < itemListSize; ++j) {
						if (listOfRetailers[i].getProductList()[j].getName().equals(deal.getName())) {

							// Decrease available quantity
							listOfRetailers[i].getProductList()[j].setAvailableQty(
									listOfRetailers[i].getProductList()[j].getAvailableQty() - deal.requestedQty);

							// add balance to retailer wallet
							listOfRetailers[i].setBalance(listOfRetailers[i].getBalance() + deal.totalPayable);

							System.out.println("\nTransaction has been successfull\n");

							System.out.println("Customer agent's Wallet balance after purchase : " + this.walletBalance);

							System.out.println("\nQuantity available now : "
									+ listOfRetailers[i].getProductList()[j].getAvailableQty());
						}
					}

				}
			}
		}else {
			System.out.println("\nSorry customer agent does not have enough credit in wallet to make transaction\n");
		}
		
	}
}
