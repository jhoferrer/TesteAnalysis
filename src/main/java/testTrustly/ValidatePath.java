package testTrustly;

public class ValidatePath {
	public static boolean validatePath(String path) {
		if(path.contains("id=")) {
			return true;
		}else {
			return false;
		}
	}
}
