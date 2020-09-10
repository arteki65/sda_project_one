package pl.aptewicz.sda.projectone.service.http;

import pl.aptewicz.sda.projectone.dto.IssPassTimesDto;
import pl.aptewicz.sda.projectone.dto.IssPositionDto;
import pl.aptewicz.sda.projectone.dto.PeopleInSpaceDto;
import pl.aptewicz.sda.projectone.service.mapper.JsonMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.stream.Collectors;

public class OpenNotifyConnector implements Comparable<URI> {

    private static final HttpRequest getPeopleInSpaceRequest =
            HttpRequest.newBuilder().GET().uri(URI.create("http://api.open-notify.org/astros.json")).build();
    private static final HttpRequest request2 =
            HttpRequest.newBuilder().GET().uri(URI.create("http://api.open-notify.org/iss-now.json")).build();

    double latitudeUser = 1;
    double longtitudeUser = 1;

    String userRequest = String.format("http://api.open-notify.org/iss-pass.json?lat=%.4f&lon=%.4f&alt=10&n=4",this.latitudeUser,this.longtitudeUser);
    private  HttpRequest requestIpt = HttpRequest.newBuilder().GET().uri(URI.create(userRequest)).build();

    private final HttpClient httpClient;

    private final JsonMapper jsonMapper;

    public OpenNotifyConnector( HttpClient httpClient, JsonMapper jsonMapper ,double latitudeUser, double longtitudeUser) {
        this.latitudeUser = latitudeUser;
        this.longtitudeUser = longtitudeUser;
        this.httpClient = httpClient;
        this.jsonMapper = jsonMapper;
        userRequest = String.format("http://api.open-notify.org/iss-pass.json?lat=%.4f&lon=%.4f&alt=10&n=6",this.latitudeUser,this.longtitudeUser);
        requestIpt = HttpRequest.newBuilder().GET().uri(URI.create(userRequest)).build();

    }


    public OpenNotifyConnector(HttpClient httpClient, JsonMapper jsonMapper) {
        this.httpClient = httpClient;
        this.jsonMapper = jsonMapper;
//        userRequest = String.format("http://api.open-notify.org/iss-pass.json?lat=%.4f&lon=%.4f&alt=10&n=4",this.latitudeUser,this.longtitudeUser);
//        requestIpt = HttpRequest.newBuilder().GET().uri(URI.create(userRequest)).build();
    }

    public void setLatitudeUser(double latitudeUser) {
        this.latitudeUser = latitudeUser;
    }

    public void setLongtitudeUser(double longtitudeUser) {
        this.longtitudeUser = longtitudeUser;
    }

    public Optional<PeopleInSpaceDto> getPeopleInSpace() {
        try {
            final var response = httpClient.send(getPeopleInSpaceRequest, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return Optional.ofNullable(jsonMapper.mapPeopleInSpaceFromJson(response.body()));
            }
            return Optional.empty();
        } catch (IOException | InterruptedException e) {
            // add logging of exception
            return Optional.empty();
        }
    }

    public Optional<IssPositionDto> getIssPosition() {
        try {
            final var response2 = httpClient.send(request2, HttpResponse.BodyHandlers.ofString());
            // ODO: add response status code check like above
            if(response2.statusCode() == 200){
                return Optional.ofNullable(jsonMapper.mapIssPositionDtoFromJson(response2.body()));
            }else
                return Optional.empty();

        } catch (IOException | InterruptedException e) {
            return Optional.empty();
        }
    }

    public Optional<IssPassTimesDto> getIssPassTimes() {
        try {
//             final HttpRequest requestIpt =
//                    HttpRequest.newBuilder().GET().uri(URI.create(userRequest)).build();

            final var responseIpt = httpClient.send(requestIpt, HttpResponse.BodyHandlers.ofString());
            if (responseIpt.statusCode() == 200) {
                return Optional.ofNullable(jsonMapper.mapIssPassTimesDtoFromJson(responseIpt.body()));
            }
            return Optional.empty();
        } catch (IOException | InterruptedException e) {
            // add logging of exception
            return Optional.empty();
        }
    }

    @Override
    public int compareTo(URI o) {
        return 0;
    }
}
