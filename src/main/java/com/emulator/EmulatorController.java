package com.emulator;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.Instant;

public class EmulatorController {

    private static final String URL = "http://localhost:80/getCard?cardId=106fbd78-1f37-11ef-bd2a-0cd292f91adb";
    //    private static final String URL = "https://official-joke-api.appspot.com/random_joke";
    private static final int NUMBER_OF_REQUESTS = 1;

    public static void main(String[] args) throws Exception {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(new URI(URL))
            .GET()
            .build();
        try {
            HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                System.out.println("Body: " + response.body());
                System.out.println("\nStatus Code: " + response.statusCode());
            } else {
                System.out.println("Something wrong there!");
            }
        } catch (IOException e) {
            System.out.println("Something wrong here!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Request method: " + request.method());
    }
}
