package pl.aptewicz.sda.projectone.service.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.aptewicz.sda.projectone.dto.PeopleInSpaceDto;

public class JacksonJsonMapper implements JsonMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public PeopleInSpaceDto mapFromJson(String json) {
        try {
            return this.objectMapper.readValue(json, PeopleInSpaceDto.class);
        } catch (JsonProcessingException e) {
            // TODO: log exception
            return null;
        }
    }
}
