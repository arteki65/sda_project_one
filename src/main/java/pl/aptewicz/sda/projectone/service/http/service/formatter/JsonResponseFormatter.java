package pl.aptewicz.sda.projectone.service.http.service.formatter;

import com.google.gson.Gson;
import pl.aptewicz.sda.projectone.service.http.dto.PeopleInSpaceDto;

import java.net.http.HttpResponse;
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
}
