package testTrustly;

public class CalculatesBytes {
	public static float calculatesBytes(String dataInfoOfFile, int[] dataBytes) {
		String wordBytes = "";
		while(dataInfoOfFile.charAt(dataBytes[0]) != ' '){
			wordBytes = String.valueOf(dataInfoOfFile.charAt(dataBytes[0])) + wordBytes;
			dataBytes[0]--;
		}
		
		switch(dataBytes[1]) {
		case 2:
			int indexDot = wordBytes.indexOf(".");
			if (indexDot != -1) {
				float bytes_final = (Integer.valueOf(wordBytes.substring(0,indexDot)) * 1024 ) + (Float.valueOf(wordBytes.substring(indexDot) ) * 1024 );
				wordBytes = String.valueOf(bytes_final);
			}else {
				int bytes_final = Integer.valueOf(wordBytes) * 1024;
				wordBytes = String.valueOf(bytes_final);
			}
			break;
			
		case 3:
			indexDot = wordBytes.indexOf(".");
			if (indexDot != -1) {
				float bytes_final = (Integer.valueOf(wordBytes.substring(0,indexDot)) * 1024 * 1024 ) + (Float.valueOf(wordBytes.substring(indexDot) ) * 1024 * 1024 );
				wordBytes = String.valueOf(bytes_final);
			}else {
				int bytes_final = Integer.valueOf(wordBytes) * 1024 * 1024;
				wordBytes = String.valueOf(bytes_final);
			}
			break;
			
		case 4:
			indexDot = wordBytes.indexOf(".");
			if (indexDot != -1) {
				float bytes_final = (Integer.valueOf(wordBytes.substring(0,indexDot)) * 1024 * 1024 * 1024 ) + (Float.valueOf(wordBytes.substring(indexDot) ) * 1024 * 1024* 1024 );
				wordBytes = String.valueOf(bytes_final);
			}else {
				int bytes_final = Integer.valueOf(wordBytes) * 1024 * 1024 * 1024;
				wordBytes = String.valueOf(bytes_final);
			}
			break;
		
		}
		return Float.valueOf(wordBytes);
	}
	

}
