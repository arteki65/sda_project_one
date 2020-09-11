package pl.aptewicz.sda.projectone.service.mapper;

import pl.aptewicz.sda.projectone.dto.IssPositionDto;
import pl.aptewicz.sda.projectone.dto.PeopleInSpaceDto;

public interface JsonMapper {
    PeopleInSpaceDto mapPeopleInSpaceFromJson(String json);
    IssPositionDto mapIssPositionDtoFromJson(String json);
}
