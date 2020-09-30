package testTrustly;

public class FindKey {
	public static int findRowHeader(String htmlText){
		int indexRowHeader = -1;
		indexRowHeader = htmlText.indexOf("rowheader");
		return indexRowHeader;
	}
}
