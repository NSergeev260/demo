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
public class ParallelRequestHttpGet implements Runnable {

    private final String URL;
    private final HttpClient client;

    public ParallelRequestHttpGet(String url, HttpClient client) {
      URL = url;
      this.client = client;
    }

    @Override
    public void run() {

        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(URL))
                .GET()
                .timeout(Duration.of(300, SECONDS))
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                log.info("Body: {}", response.body());
            } else {
                log.warn("Something wrong. StatusCode NOT 200!");
            }

        } catch (IOException | URISyntaxException e) {
            log.warn("Something wrong! CATCH block.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}