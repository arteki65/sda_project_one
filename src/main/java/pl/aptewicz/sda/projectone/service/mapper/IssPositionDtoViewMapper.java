package pl.aptewicz.sda.projectone.service.mapper;

import pl.aptewicz.sda.projectone.dto.IssPositionDto;
import pl.aptewicz.sda.projectone.view.IssPositionView;

public class IssPositionDtoViewMapper {

    public IssPositionView mapToView(IssPositionDto issPositionDto) {

        return new IssPositionView(issPositionDto.getTimestamp(),
                mapCurrentPositionToView(issPositionDto.getIssPosition()));
    }

    private IssPositionView.IssCurrentPositionView mapCurrentPositionToView(IssPositionDto.IssPosition dtoPosition) {

        return new IssPositionView.IssCurrentPositionView(dtoPosition.getLongitude(),
                dtoPosition.getLatitude());
    }


}
