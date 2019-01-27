package FrontEndDigitalStockPortfolio;

public class StartDigitalStockPortfolio {
	public static void main(String[] args) {
		ApiConnection apiTest = new ApiConnection();

		new FrontEnd(apiTest);
	}
}