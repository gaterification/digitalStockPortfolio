import java.io.IOException;

public class NasdaqTest {
public static void main(String[] args) {
	Nasdaq n = new Nasdaq();
	try {
		n.getMarketPrice("TSLA");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
