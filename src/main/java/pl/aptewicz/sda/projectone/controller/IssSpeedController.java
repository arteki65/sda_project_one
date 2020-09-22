package pl.aptewicz.sda.projectone.controller;

import pl.aptewicz.sda.projectone.dto.IssPositionDto;
import pl.aptewicz.sda.projectone.service.IssPositionService;
import pl.aptewicz.sda.projectone.service.http.OpenNotifyConnector;
import pl.aptewicz.sda.projectone.service.mapper.IssPositionDtoViewMapper;
import pl.aptewicz.sda.projectone.view.IssPositionView;
import pl.aptewicz.sda.projectone.view.IssSpeedView;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class IssSpeedController extends IssPositionController {


    public IssSpeedController(OpenNotifyConnector openNotifyConnector, IssPositionDtoViewMapper mapper,
                              IssPositionService issPositionService) {
        super(openNotifyConnector, mapper, issPositionService);
    }

    public IssSpeedView getIssSpeedView() throws Exception {

        List<IssPositionDto> issPositions = issPositionService.getIssPositions();
       // issPositions.stream().map(mapper::mapToView).collect(Collectors.toList());

        return new IssSpeedView(issPositions.stream().map(mapper::mapToView).collect(Collectors.toList()));
    }
}
