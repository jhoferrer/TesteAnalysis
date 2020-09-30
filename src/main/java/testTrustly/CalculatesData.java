package testTrustly;

import java.util.ArrayList;

public class CalculatesData {
	public static FileInfo calculatesData(String address, String dataInfoOfFile,String filePath) {
		int lines = 0;
		final int NO_KEYWORD = -1;
		final String KEYWORD_INFO_FILE = "file-info-divider";
		
		/*Checks if the page contains the id: "file-info-divider", which is characteristic of files with number of lines and bytes, if not, 
		the code goes on to search for Bytes without searching for number of lines.*/
		
		int indexKeyword = dataInfoOfFile.indexOf(KEYWORD_INFO_FILE);
		if (indexKeyword != NO_KEYWORD) {
			lines = FindLines.findLines(dataInfoOfFile, indexKeyword);
		}
		
		int[] dataBytes = FindBytes.findBytes(dataInfoOfFile, 0, 0 );
		
		float bytes = CalculatesBytes.calculatesBytes(dataInfoOfFile,dataBytes);
		
		String format = "";
		
		if(filePath == "") {
			int indexDot = address.lastIndexOf(".");
			format = address.substring(indexDot);
		}else {
			int indexDot = filePath.lastIndexOf(".");
			format = filePath.substring(indexDot);
		}
		
		return (new FileInfo(format, lines, bytes));//CreateArrayResult.createArrayResult(array_dados,format, lines, bytes);
	}

}
