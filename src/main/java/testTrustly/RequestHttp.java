package testTrustly;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestHttp {
	public static String sendGet(String url) throws Exception{
		final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).
				setHeader("User-Agent", "Java 11 HttpClient Bot").build();
		
		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		String url_text = response.body();		
		return url_text;
	}
}
