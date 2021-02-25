package eCommerce;

import java.util.Scanner;

public class Environment {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		
		
		// customer agent
		CustomerAgent ca = new CustomerAgent("Zaid", "c1", 1500);

		// create retailer agents
		RetailerAgent[] listOfRetailers = new RetailerAgent[5];

		for (int i = 0; i < 5; ++i) {
			listOfRetailers[i] = new RetailerAgent("r" + String.valueOf(i));
		}

		// shopping loop
		boolean purchaseAgian = false;
		do {
			//display available products
			
			System.out.println("\nProduts avilable in store are : ink, book, shoe, bag, pen\n");
			System.out.println("\n ================================= \n");
			// make request to buy something

			String[] reqestList = ca.makeRequestToPurchase();
			//display request made by customer agent
			System.out.println("\nRequest Customer agent made \n");
			for (int i = 0; i < reqestList.length; ++i) {
				if (i == 0) {
					System.out.println("Min price: " + reqestList[i]);
				} else if (i == 1) {
					System.out.println("Max price: " + reqestList[i]);
				} else if (i == 2) {
					System.out.println("Product name: " + reqestList[i]);
				} else if (i == 3) {
					System.out.println("Product quantity: " + reqestList[i]);
				} else if (i == 4) {
					System.out.println("Delivery option: " + reqestList[i] + "\n");
				}

			}

			System.out.println("\n ================================= \n");

			// broker agent get customer request as input
			BrokerAgent ba = new BrokerAgent();
			ba.getRequestFromCustomer(reqestList);

			System.out.println("\n ================================= \n");
			System.out.println("\n Finding products.......\n");
			
			// broker agent invite retailers
			Thread.sleep(1000); //wait for 1 second
			
			ProductWithRetailer[] listProductFound = ba.inviteRetailers(listOfRetailers);

			if (listProductFound == null) {
				System.out.println("No Item found matches your request, try something else\n");
			} else {
				
				// broker agent recommend deal back to customer agent
				ba.recommendToCustomer(ca, listProductFound, listOfRetailers);

			}

			/// go purchase again
			System.out.println("\n ================================= \n");
			String choice = "";

			System.out.println("If want to purchase agian press 'y' : ");
			Scanner input = new Scanner(System.in); // input is just a random variable to get input
			choice = input.next();
			if (choice.equals("y") || choice.equals("Y")) {
				purchaseAgian = true;
			} else {
				purchaseAgian = false;
			}
		} while (purchaseAgian);

		System.out.println("\nProgram terminated \n");
	}

}
