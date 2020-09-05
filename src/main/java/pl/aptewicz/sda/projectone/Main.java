package pl.aptewicz.sda.projectone;

import pl.aptewicz.sda.projectone.dto.IssSpeedDto;
import pl.aptewicz.sda.projectone.service.formatter.JsonSpeedFormatter;
import pl.aptewicz.sda.projectone.service.formatter.SpeedResponseFormatter;
import pl.aptewicz.sda.projectone.service.http.OpenNotifyConnector;
import pl.aptewicz.sda.projectone.service.formatter.JsonResponseFormatter;
import pl.aptewicz.sda.projectone.service.http.SpeedOpenNotifyConnector;

import java.net.http.HttpClient;
import java.time.Duration;

public class Main {

    private static final HttpClient httpClient =
            HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).connectTimeout(Duration.ofSeconds(10)).build();

    public static void main(String[] args) {
        System.out.println("Hello javaLon4");

        //        final var openNotifyConnector = new OpenNotifyConnector(HttpResponse::body, httpClient);
        final OpenNotifyConnector openNotifyConnector = new OpenNotifyConnector(new JsonResponseFormatter(), httpClient);
        System.out.println(openNotifyConnector.getPeopleInSpace());

        final SpeedOpenNotifyConnector speedOpenNotifyConnector =
                new SpeedOpenNotifyConnector(new JsonSpeedFormatter(), httpClient);

        String speedDto = speedOpenNotifyConnector.getSpeed();
        System.out.println(speedDto);
        //System.out.println(speedDto.getTimestamp());
        //System.out.println(speedDto.getIssPosition().getLongitude());






    }
}
