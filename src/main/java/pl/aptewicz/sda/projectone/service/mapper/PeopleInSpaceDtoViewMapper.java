package pl.aptewicz.sda.projectone.service.mapper;

import pl.aptewicz.sda.projectone.dto.PeopleInSpaceDto;
import pl.aptewicz.sda.projectone.view.PeopleInSpaceView;

import java.util.List;
import java.util.stream.Collectors;

public class PeopleInSpaceDtoViewMapper {
    public PeopleInSpaceView mapDtoToView(PeopleInSpaceDto dto) {
        return new PeopleInSpaceView(dto.getNumber(), mapHumanInSpaceFromDtoToView(dto.getPeople()));
    }

    private List<PeopleInSpaceView.HumanInSpaceView> mapHumanInSpaceFromDtoToView(
            List<PeopleInSpaceDto.HumanInSpace> people) {
        return people.stream().map(humanInSpace -> new PeopleInSpaceView.HumanInSpaceView(humanInSpace.getCraft(),
                humanInSpace.getName())).collect(Collectors.toList());
    }
}
