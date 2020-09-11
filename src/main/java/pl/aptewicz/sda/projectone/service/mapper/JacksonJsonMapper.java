package pl.aptewicz.sda.projectone.service.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.aptewicz.sda.projectone.dto.IssPositionDto;
import pl.aptewicz.sda.projectone.dto.PeopleInSpaceDto;

public class JacksonJsonMapper implements JsonMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public PeopleInSpaceDto mapPeopleInSpaceFromJson(String json) {
        try {
            return this.objectMapper.readValue(json, PeopleInSpaceDto.class);
        } catch (JsonProcessingException e) {
            // log exception
            return null;
        }
    }

    @Override
    public IssPositionDto mapIssPositionDtoFromJson(String json) {
        // TODO: implement mapping from jso to dot using Jackson lib
        throw new UnsupportedOperationException();
    }
}
