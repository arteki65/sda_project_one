package pl.aptewicz.sda.projectone.controller;

import pl.aptewicz.sda.projectone.service.http.OpenNotifyConnector;
import pl.aptewicz.sda.projectone.service.mapper.PeopleInSpaceDtoViewMapper;

public class PeopleInSpaceController {

    private final OpenNotifyConnector openNotifyConnector;

    private final PeopleInSpaceDtoViewMapper dtoViewMapper;

    public PeopleInSpaceController(OpenNotifyConnector openNotifyConnector, PeopleInSpaceDtoViewMapper dtoViewMapper) {
        this.openNotifyConnector = openNotifyConnector;
        this.dtoViewMapper = dtoViewMapper;
    }

    public String getPeopleInSpaceInfo() throws Exception {
        final var result = this.openNotifyConnector.getPeopleInSpace();
        final var view = result.map(dtoViewMapper::mapDtoToView)
                .orElseThrow(() -> new Exception("Unable to get info about people in space."));
        return view.getInfoAboutPeopleInSpace();
    }
}
