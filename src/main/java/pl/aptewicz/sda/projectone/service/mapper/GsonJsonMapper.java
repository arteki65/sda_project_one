package pl.aptewicz.sda.projectone.service.mapper;

import com.google.gson.Gson;
import pl.aptewicz.sda.projectone.dto.PeopleInSpaceDto;

public class GsonJsonMapper implements JsonMapper {
    private final Gson gson = new Gson();

    @Override
    public PeopleInSpaceDto mapFromJson(String json) {
        return gson.fromJson(json, PeopleInSpaceDto.class);
    }
}
