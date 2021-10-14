package client;

import DataTransferObjects.Users;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import core.ApiAggregator;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;

/**
 * This class encapsulates the client request handling logic. It is agnostic of what kinds of requests are being made.
 * The exact request formatting is outsourced to ClientRequestGenerator.
 */
public class ApiClient {

    private HttpClient client;

    public ApiClient() {
        this.client = HttpClient.newHttpClient();
    }

    public String makeRequest(HttpRequest req) throws Exception {
        // combine using a set so that duplicate objects aren't added
        // json object converted class
        try {
            HttpResponse<String> apiResponse = client.send(req, HttpResponse.BodyHandlers.ofString());
            ApiAggregator api = new ApiAggregator();
            return apiResponse.body();
        } catch (IOException ioe) {
            System.out.println("An I/O error occurred when sending or receiving data.");
            System.out.println(ioe.getMessage());

        } catch (InterruptedException ie) {
            System.out.println("The operation was interrupted.");
            System.out.println(ie.getMessage());

        } catch (IllegalArgumentException iae) {
            System.out.println(
                    "The request argument was invalid. It must be built as specified by HttpRequest.Builder.");
            System.out.println(iae.getMessage());

        } catch (SecurityException se) {
            System.out.println("There was a security configuration error.");
            System.out.println(se.getMessage());
        }
        throw new Exception("ERROR: Request Failed");
    }
}

// this website: https://www.techiediaries.com/java/java-11-httpclient-gson-send-http-get-parse-json-example/