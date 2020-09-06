package pl.aptewicz.sda.projectone.service.http;

import pl.aptewicz.sda.projectone.service.formatter.ResponseFormatter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OpenNotifyConnector {

    private static final HttpRequest request =
            HttpRequest.newBuilder().GET().uri(URI.create("http://api.open-notify.org/astros.json")).build();
    private static final HttpRequest request2 =
            HttpRequest.newBuilder().GET().uri(URI.create("http://api.open-notify.org/iss-now.json")).build();

    private final ResponseFormatter responseFormatter;

    private final HttpClient httpClient;

    public OpenNotifyConnector(ResponseFormatter responseFormatter, HttpClient httpClient) {
        this.responseFormatter = responseFormatter;
        this.httpClient = httpClient;
    }

    public String getPeopleInSpace() {
       try {
            final var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
           return responseFormatter.formatResponse(response);
        } catch (IOException | InterruptedException e) {
           e.printStackTrace();
            return "Error while getting people in space...";
        }
    }

    public String getIssPosition() {
        try {
            final var response2 = httpClient.send(request2, HttpResponse.BodyHandlers.ofString());
            return responseFormatter.formatIssPosition(response2);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error while getting ISS current position...";
        }
    }

}
