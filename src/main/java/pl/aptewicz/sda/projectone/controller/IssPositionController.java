package pl.aptewicz.sda.projectone.controller;

import pl.aptewicz.sda.projectone.dto.IssPositionDto;
import pl.aptewicz.sda.projectone.service.http.OpenNotifyConnector;
import pl.aptewicz.sda.projectone.service.mapper.IssPositionDtoViewMapper;
import pl.aptewicz.sda.projectone.view.IssPositionView;

import java.util.Optional;

public class IssPositionController {

    protected final OpenNotifyConnector openNotifyConnector;

    protected final IssPositionDtoViewMapper mapper;

    public IssPositionController(OpenNotifyConnector openNotifyConnector, IssPositionDtoViewMapper mapper) {
        this.openNotifyConnector = openNotifyConnector;
        this.mapper = mapper;
    }

    public IssPositionView getIssPositionView() throws Exception {
        final Optional<IssPositionDto>  issPositionDto = openNotifyConnector.getIssPosition();

        return mapper.mapToView(issPositionDto.orElseThrow(() -> new Exception("Can get current Position")));
    }
}
