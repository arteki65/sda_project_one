package pl.aptewicz.sda.projectone.service.mapper;

import pl.aptewicz.sda.projectone.dto.PeopleInSpaceDto;
import pl.aptewicz.sda.projectone.entity.HumanInSpaceEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HumanInSpaceEntityMapper {
    public HumanInSpaceEntity mapFromResultSet(ResultSet rs) throws SQLException {
        return new HumanInSpaceEntity(rs.getString("craft"), rs.getString("name"));
    }

    public HumanInSpaceEntity mapFromDto(PeopleInSpaceDto.HumanInSpace humanInSpace) {
        return new HumanInSpaceEntity(humanInSpace.getCraft(), humanInSpace.getName());
    }

    public PeopleInSpaceDto.HumanInSpace mapToDto(HumanInSpaceEntity entity) {
        return new PeopleInSpaceDto.HumanInSpace(entity.getCraft(), entity.getName());
    }
}
