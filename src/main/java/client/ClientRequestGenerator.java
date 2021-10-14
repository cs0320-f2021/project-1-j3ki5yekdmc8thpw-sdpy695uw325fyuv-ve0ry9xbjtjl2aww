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


    public static HttpRequest getSecuredRequest(String file) {
        ClientAuth authenticator = new ClientAuth();
        String apiKey = authenticator.getApiKey();
        String[] apiKeyArray = apiKey.split(" ");
        String user = apiKeyArray[0];
        String password = apiKeyArray[1];
        String reqUri = file + user + "&key=" + password;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(reqUri))
                .build();
        return request;
    }
}
