package client;

import client.ClientAuth;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;

/**
 * This class generates the HttpRequests that are then used to make requests from the ApiClient.
 */
public class ClientRequestGenerator {


    public static HttpRequest getIntroGetRequest() {
        String reqUri = "https://epb3u4xo11.execute-api.us-east-1.amazonaws.com/Prod/introResource";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(reqUri)).build();
        return request;
    }

    public static HttpRequest getSecuredGetRequest() {
        String reqUri = "https://epb3u4xo11.execute-api.us-east-1.amazonaws.com/Prod/securedResource";
        ClientAuth authenticator = new ClientAuth();
        String apiKey = authenticator.getApiKey();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(reqUri))
                .header("x-api-key", apiKey)
                .build();
        return request;
    }
}
