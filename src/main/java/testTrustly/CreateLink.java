package testTrustly;

public class CreateLink {
	public static String createLink(String path) {
		int indexHref = (path.indexOf("href")+6);
		int indexFinalLink = path.indexOf("\">",indexHref);
		path = path.substring(indexHref, indexFinalLink);
		path = "https://github.com"+path;
		
		//Path now has the link for the next level
		return path; 
	}
}
