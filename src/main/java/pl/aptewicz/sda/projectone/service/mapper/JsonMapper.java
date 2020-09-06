package pl.aptewicz.sda.projectone.service.mapper;

import pl.aptewicz.sda.projectone.dto.PeopleInSpaceDto;

public interface JsonMapper {
    PeopleInSpaceDto mapFromJson(String json);
}
