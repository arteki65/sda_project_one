package pl.aptewicz.sda.projectone.controller;

import pl.aptewicz.sda.projectone.service.IssPositionService;
import pl.aptewicz.sda.projectone.service.http.OpenNotifyConnector;
import pl.aptewicz.sda.projectone.service.mapper.IssPositionDtoViewMapper;
import pl.aptewicz.sda.projectone.view.IssPositionView;

public class IssPositionController {

    protected final OpenNotifyConnector openNotifyConnector;

    protected final IssPositionDtoViewMapper mapper;

    protected final IssPositionService issPositionService;

    public IssPositionController(OpenNotifyConnector openNotifyConnector, IssPositionDtoViewMapper mapper,
                                 IssPositionService issPositionService) {
        this.openNotifyConnector = openNotifyConnector;
        this.mapper = mapper;
        this.issPositionService = issPositionService;
    }

    public IssPositionView getIssPositionView() throws Exception {
        final var issPositionDto = issPositionService.getIssPosition();

        return mapper.mapToView(issPositionDto);
    }
}
