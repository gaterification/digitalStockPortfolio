package ch.hwz.sem3.dsp.ui;

public class StartDigitalStockPortfolio {
	public static void main(String[] args) {
		ApiConnection apiTest = new ApiConnection();

		new FrontEnd(apiTest);
	}
}