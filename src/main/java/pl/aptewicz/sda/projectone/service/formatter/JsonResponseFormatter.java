package pl.aptewicz.sda.projectone.service.formatter;

import com.google.gson.Gson;
import pl.aptewicz.sda.projectone.dto.CurrentLocationDto;
import pl.aptewicz.sda.projectone.dto.PeopleInSpaceDto;

import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.ZoneId;
import java.util.stream.Collectors;

public class JsonResponseFormatter implements ResponseFormatter {

    private static final Gson gson = new Gson();

    @Override
    public String formatResponse(HttpResponse<String> response) {
        final var json = response.body();
        final var peopleInSpace = gson.fromJson(json, PeopleInSpaceDto.class);
        return String.format("Currently there are %d people in space:\n%s", peopleInSpace.getNumber(),
                peopleInSpace.getPeople().stream()
                        .map(humanInSpace -> humanInSpace.getName() + " on craft " + humanInSpace.getCraft() + "\n")
                        .collect(Collectors.joining()));
    }

    @Override
    public String formatResponseTwo(HttpResponse<String> responseTwo) {
        final var json = responseTwo.body();
        final var currentLocation = gson.fromJson(json, CurrentLocationDto.class);
        final var timestamp = currentLocation.getTimestamp();
        final var date = Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault());
        return String.format("International space station current location is %s and %s. The timestamp: %s",
                currentLocation.getPositionInSpace().getLatitude(), currentLocation.getPositionInSpace().getLongitude(),
                date);

    }
}
