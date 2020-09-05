package pl.aptewicz.sda.projectone.service.http;

import pl.aptewicz.sda.projectone.dto.IssSpeedDto;
import pl.aptewicz.sda.projectone.service.formatter.JsonSpeedFormatter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class SpeedOpenNotifyConnector {

    private static final HttpRequest request = HttpRequest
            .newBuilder()
            .GET().uri(URI.create("http://api.open-notify.org/iss-now.json")).build();

    private final JsonSpeedFormatter speedFormatter;

    private final HttpClient httpClient;

    public SpeedOpenNotifyConnector(JsonSpeedFormatter speedFormatter, HttpClient httpClient) {
        this.speedFormatter = speedFormatter;
        this.httpClient = httpClient;
    }

    public String getSpeed() {

        IssSpeedDto measurement1 = getIssSpeedDto();
        Double longitude1 = measurement1.getIssPosition().getLongitude();
        Double latitude1 = measurement1.getIssPosition().getLatitude();
        Long timestamp1 = measurement1.getTimestamp();


        IssSpeedDto measurement2 = getIssSpeedDto();
        Double longitude2 = measurement2.getIssPosition().getLongitude();
        Double latitude2 = measurement2.getIssPosition().getLatitude();
        Long timestamp2 = measurement2.getTimestamp();

        Instant one = Instant.ofEpochSecond(timestamp1);
        Instant two = Instant.ofEpochSecond(timestamp2);
        Duration duration = Duration.between(one, two);
        long timeElapsed = duration.getSeconds();
        double distance = haversine(latitude1, longitude1, latitude2, longitude2);

        double speed = distance / timeElapsed;


        return String.format("Average speed is %f ", speed);

    }

    private IssSpeedDto getIssSpeedDto() {

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return speedFormatter.formatResponse(response);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }

    }

    private double haversine(double lat1, double lon1,
                            double lat2, double lon2)
    {

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);


        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);


        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }

}
