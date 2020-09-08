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
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public IssPositionDto mapIssPositionDtoFromJson(String json) {
        // ODO: implement mapping from jso to dot using Jackson lib
        try {
            return  this.objectMapper.readValue(json,IssPositionDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException();
        }
    }
}
