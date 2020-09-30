package testTrustly;

public class FindLines {
	public static int findLines(String contentPage, int indexKeyword) {
		String lines="";
		String dataInfoOfFile = contentPage.substring(indexKeyword-200,indexKeyword+100);
		int indexLine = dataInfoOfFile.indexOf("line");
		final int NO_KEYWORD = -1;
		
		if (indexLine != NO_KEYWORD) {
			indexLine -= 2;
			while(!(dataInfoOfFile.charAt(indexLine) == ' ')){
				lines = String.valueOf(dataInfoOfFile.charAt(indexLine)) + lines;
				indexLine--;
			}
		}
		return Integer.valueOf(lines);
	}
}
