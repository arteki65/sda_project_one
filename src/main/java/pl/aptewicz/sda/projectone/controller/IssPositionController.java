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

    public IssPositionView getIssPositionView() throws Exception {
        // TODO: implement method like in PeopleInSpaceController
        final var result = this.openNotifyConnector.getIssPosition();
        return result.map(mapper::mapIssPositionDtoToView)
                .orElseThrow(() -> new Exception("Unable to get info about ISS position."));

        //throw new UnsupportedOperationException();
    }
}
