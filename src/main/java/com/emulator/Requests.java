package com.emulator;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

@Slf4j
public class Requests {

    public String getRequest(String URL) throws URISyntaxException {

        HttpRequest request = HttpRequest.newBuilder(new URI(URL))
            .GET()
            .timeout(Duration.of(300, SECONDS))
            .build();

        return getResponse(request);
    }

    public String postRequest(String URL) throws URISyntaxException {

        HttpRequest request = HttpRequest.newBuilder(new URI(URL))
            .POST(HttpRequest.BodyPublishers.noBody())
            .timeout(Duration.of(300, SECONDS))
            .build();

        return getResponse(request);
    }

    private static String getResponse(HttpRequest request) {

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
            response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                log.info("Request method: {}", request.method());
                log.info("Status Code: {}", response.statusCode());
                log.info("Body: {}", response.body());
            } else {
                log.warn("Something wrong. StatusCode NOT 200!");
            }

        } catch (
            IOException e) {
            log.warn("Something wrong! CATCH block.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response.body();
    }
}