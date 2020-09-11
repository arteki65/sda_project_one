package pl.aptewicz.sda.projectone.service.mapper;

import pl.aptewicz.sda.projectone.dto.IssPositionDto;
import pl.aptewicz.sda.projectone.dto.PeopleInSpaceDto;
import pl.aptewicz.sda.projectone.view.IssPositionView;
import pl.aptewicz.sda.projectone.view.PeopleInSpaceView;

import java.util.List;
import java.util.stream.Collectors;

public class IssPositionDtoViewMapper {
    // TODO: implement like in PeopleInSpaceDtoViewMapper


    public IssPositionView mapIssPositionDtoToView (IssPositionDto issDto){
        //return new IssPositionView(issDto.getTimestamp(), issDto.getIssPosition().getLatitude(),issDto.getIssPosition().getLongitude());
        return new IssPositionView(issDto.getTimestamp(), mapIssCurrentPositionFromDtoToView(issDto.getIssPosition()));
    }
private IssPositionView.IssCurrentPositionView mapIssCurrentPositionFromDtoToView(
        IssPositionDto.IssPosition position) {
        return new IssPositionView.IssCurrentPositionView(
                position.getLongitude(), position.getLatitude());
}



   /* public PeopleInSpaceView mapDtoToView(PeopleInSpaceDto dto) {
        return new PeopleInSpaceView(dto.getNumber(), mapHumanInSpaceFromDtoToView(dto.getPeople()));
    }

    private List<PeopleInSpaceView.HumanInSpaceView> mapHumanInSpaceFromDtoToView(
            List<PeopleInSpaceDto.HumanInSpace> people) {
        return people.stream().map(humanInSpace -> new PeopleInSpaceView.HumanInSpaceView(humanInSpace.getCraft(),
                humanInSpace.getName())).collect(Collectors.toList());
    } */

}


