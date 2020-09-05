package pl.aptewicz.sda.projectone;

import pl.aptewicz.sda.projectone.service.http.OpenNotifyConnector;
import pl.aptewicz.sda.projectone.service.formatter.JsonResponseFormatter;

import java.net.http.HttpClient;
import java.time.Duration;

public class Main {

    private static final HttpClient httpClient =
            HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).connectTimeout(Duration.ofSeconds(10)).build();

    public static void main(String[] args) {
        System.out.println("Hello javaLon4");

        //        final var openNotifyConnector = new OpenNotifyConnector(HttpResponse::body, httpClient);
        final var openNotifyConnector = new OpenNotifyConnector(new JsonResponseFormatter(), httpClient);
        System.out.println(openNotifyConnector.getPeopleInSpace());
        System.out.println(openNotifyConnector.getIssPosition());
    }
}
