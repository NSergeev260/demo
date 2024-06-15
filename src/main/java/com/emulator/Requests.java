package com.emulator;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
public class Requests {

    public void getRequest(String URL) throws URISyntaxException {

        HttpRequest request = HttpRequest.newBuilder(new URI(URL))
            .GET()
            .build();

        getResponse(request);
    }

    public void postRequest(String URL) throws URISyntaxException {

        HttpRequest request = HttpRequest.newBuilder(new URI(URL))
            .POST(HttpRequest.BodyPublishers.noBody())
            .build();

        getResponse(request);
    }

    private static void getResponse(HttpRequest request) {

        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                log.info("Request method: " + request.method());
                log.info("Status Code: " + response.statusCode());
                log.info("Body: " + response.body());
            } else {
                log.info("Something wrong. StatusCode NOT 200!");
            }

        } catch (
            IOException e) {
            log.info("Something wrong! CATCH block.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}