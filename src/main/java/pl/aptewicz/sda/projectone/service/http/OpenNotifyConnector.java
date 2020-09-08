package pl.aptewicz.sda.projectone.service.http;

import pl.aptewicz.sda.projectone.dto.IssPositionDto;
import pl.aptewicz.sda.projectone.dto.PeopleInSpaceDto;
import pl.aptewicz.sda.projectone.service.mapper.JsonMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class OpenNotifyConnector {

    private static final HttpRequest getPeopleInSpaceRequest =
            HttpRequest.newBuilder().GET().uri(URI.create("http://api.open-notify.org/astros.json")).build();
    private static final HttpRequest getIssPositionRequest =
            HttpRequest.newBuilder().GET().uri(URI.create("http://api.open-notify.org/iss-now.json")).build();

    private final HttpClient httpClient;

    private final JsonMapper jsonMapper;

    public OpenNotifyConnector(HttpClient httpClient, JsonMapper jsonMapper) {
        this.httpClient = httpClient;
        this.jsonMapper = jsonMapper;
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
            final var response2 = httpClient.send(getIssPositionRequest, HttpResponse.BodyHandlers.ofString());
            if (response2.statusCode() == 200) {
                return Optional.ofNullable(jsonMapper.mapIssPositionDtoFromJson(response2.body()));
            }
            return Optional.empty();
        } catch (IOException | InterruptedException e) {
            return Optional.empty();
        }
    }

}
