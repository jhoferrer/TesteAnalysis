package testTrustly;

import java.util.ArrayList;

public class CreateArrayResult {
	public static ArrayList<FileInfo> createArrayResult(ArrayList<FileInfo> arrayData,FileInfo currentData){
		if (arrayData.size() > 0) {
			boolean hasFormat = false;
			for(int i=0; i <= arrayData.size()-1 ; i++) {
				if (arrayData.get(i).format.equals(currentData.format)) {
					arrayData.get(i).lines += Integer.valueOf(currentData.lines);
					arrayData.get(i).bytes += currentData.bytes;
					
					//
					hasFormat = true;
					
					//Closes the for because it has already been identified that this file format already exists in the array
					i = arrayData.size();       
				}//else if((array_dados.size() - i) == 0){
				//	array_dados.add(new file_info(format, lines, bytes));
				//}
			}
			if (!hasFormat) {
				arrayData.add(new FileInfo(currentData.format, currentData.lines, currentData.bytes));
			}
		}else {
			arrayData.add(new FileInfo(currentData.format, currentData.lines, currentData.bytes));
		}
		return arrayData;
	}
}
