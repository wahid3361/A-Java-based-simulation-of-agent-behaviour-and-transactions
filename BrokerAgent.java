package eCommerce;

public class BrokerAgent {

	String[] requestList = new String[5];

	public void getRequestFromCustomer(String[] list) {
		requestList = list;
		System.out.println("Purchase request received by Broker Agent");
	}

	public ProductWithRetailer[] inviteRetailers(RetailerAgent[] listRetailers) {

		ProductWithRetailer[] listProductFound = new ProductWithRetailer[5];

		System.out.println("\nList of prouduts found\n");
		
		int j = 0;
		for (int i = 0; i < listRetailers.length; ++i) {
			RetailerAgent ra1 = listRetailers[i];
			ProductWithRetailer pr = ra1.getRequestFromBroker(requestList);
			if (pr != null) {
				listProductFound[j] = pr;

				System.out.println("Total Payble : " + listProductFound[j].getTotalPayable() + " , Retailer ID : "
						+ listProductFound[j].getRetailerID());

				j++;
			}
			
			
		}
		if(j==0) {
			return null;
		}

		return listProductFound;

	}

	public void findBestDeals() {

	}

	public void recommendToCustomer(CustomerAgent ca, ProductWithRetailer[] listProductFound,
			RetailerAgent[] listOfRetailers) throws InterruptedException {

		ProductWithRetailer deal = listProductFound[0];

		ca.getDealFromBroker(deal, listOfRetailers);
	}
}
