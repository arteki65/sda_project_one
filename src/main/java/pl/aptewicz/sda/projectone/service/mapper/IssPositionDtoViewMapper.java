package pl.aptewicz.sda.projectone.service.mapper;

import pl.aptewicz.sda.projectone.dto.IssPositionDto;
import pl.aptewicz.sda.projectone.view.IssPositionView;

import java.util.List;

public class IssPositionDtoViewMapper {
    // ODO: implement like in PeopleInSpaceDtoViewMapper
    public IssPositionView mapIssPositionToView (IssPositionDto issPositionDto){
        return  new IssPositionView(issPositionDto.getTimestamp(),issCurrentPositionViews(issPositionDto.getIssPosition()));
    }


    private IssPositionView.IssCurrentPositionView
            issCurrentPositionViews(IssPositionDto.IssPosition issCPV){
        return  new IssPositionView.IssCurrentPositionView(issCPV.getLongitude(),issCPV.getLatitude());
    }
}
