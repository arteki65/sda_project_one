package pl.aptewicz.sda.projectone.controller;

import pl.aptewicz.sda.projectone.service.http.OpenNotifyConnector;
import pl.aptewicz.sda.projectone.service.mapper.IssPositionDtoViewMapper;
import pl.aptewicz.sda.projectone.view.IssPositionView;

public class IssPositionController {

    private final OpenNotifyConnector openNotifyConnector;

    private final IssPositionDtoViewMapper mapper;

    public IssPositionController(OpenNotifyConnector openNotifyConnector, IssPositionDtoViewMapper mapper) {
        this.openNotifyConnector = openNotifyConnector;
        this.mapper = mapper;
    }

    public IssPositionView getIssPositionView()  {
        final var result = openNotifyConnector.getIssPosition();
        return result.map(mapper::mapIssPositionToView)
                .orElseThrow(() ->  new UnsupportedOperationException());

    }
}
