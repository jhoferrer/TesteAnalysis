package testTrustly;

public class FindBytes {
public static int[] findBytes(String dataInfoOfFile, int indexByte, int sizeByte) {
		
		/*This function finds the number of Bytes in the file, dataBytes [0] contains the index of the position in the file of the word related to Bytes, 
		 dataBytes [1] contains the type of Bytes, GB = 4, MB = 3, KB = 2 or Bytes = 1*/
		
		int[] data = new int[2];
		String typeByte="";
		
		for(int i=1; i <= 4; i++) {
			indexByte = 0;
			switch(i) {
			case 1:
				typeByte = "Byte";
				break;
				
			case 2:
				typeByte = "KB";
				break;
				
			case 3:
				typeByte = "MB";
				break;
				
			case 4:
				typeByte = "GB";
				break;
			}
			
			while(indexByte != -1 && sizeByte != i) {
				indexByte = dataInfoOfFile.indexOf(typeByte,indexByte+1);
				if(indexByte != -1) {
					if (dataInfoOfFile.charAt(indexByte-1) == ' ' && (dataInfoOfFile.charAt(indexByte-2) == '1' || dataInfoOfFile.charAt(indexByte-2) == '2' || dataInfoOfFile.charAt(indexByte-2) == '3' 
							|| dataInfoOfFile.charAt(indexByte-2) == '4' || dataInfoOfFile.charAt(indexByte-2) == '5' || dataInfoOfFile.charAt(indexByte-2) == '6' || dataInfoOfFile.charAt(indexByte-2) == '7' 
							|| dataInfoOfFile.charAt(indexByte-2) == '8' || dataInfoOfFile.charAt(indexByte-2) == '9' || dataInfoOfFile.charAt(indexByte-2) == '0' ) ) {
						sizeByte = i;
						indexByte -= 2;
						data[0] = indexByte;
						data[1] = sizeByte;
						return data;
						}
					}
				}
		}
		return data;
	}

}
