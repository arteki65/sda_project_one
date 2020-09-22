package pl.aptewicz.sda.projectone.service.mapper;

import pl.aptewicz.sda.projectone.dto.IssPositionDto;
import pl.aptewicz.sda.projectone.entity.IssPositionEntity;

import java.util.UUID;

public class IssPositionEntityMapper {
    // TODO: implement mapper
    public IssPositionEntity mapFromDto(IssPositionDto dto) {

        return new IssPositionEntity(UUID.randomUUID(), dto.getIssPosition().getLongitude(),
                dto.getIssPosition().getLatitude(), dto.getTimestamp());
    }

    public IssPositionDto mapFromEntity(IssPositionEntity entity) {

        return new IssPositionDto(new IssPositionDto.IssPosition(entity.getLongitude(), entity.getLatitude()),
                entity.getTimestamp());
    }
}
