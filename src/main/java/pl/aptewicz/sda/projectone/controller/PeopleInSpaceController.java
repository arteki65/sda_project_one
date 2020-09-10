package pl.aptewicz.sda.projectone.controller;

import pl.aptewicz.sda.projectone.service.PeopleInSpaceService;
import pl.aptewicz.sda.projectone.service.mapper.PeopleInSpaceDtoViewMapper;
import pl.aptewicz.sda.projectone.view.PeopleInSpaceView;

public class PeopleInSpaceController {

    private final PeopleInSpaceService peopleInSpaceService;

    private final PeopleInSpaceDtoViewMapper dtoViewMapper;

    public PeopleInSpaceController(PeopleInSpaceService peopleInSpaceService,
                                   PeopleInSpaceDtoViewMapper dtoViewMapper) {
        this.peopleInSpaceService = peopleInSpaceService;
        this.dtoViewMapper = dtoViewMapper;
    }

    public PeopleInSpaceView getPeopleInSpaceInfo() throws Exception {
        final var peopleInSpaceDto = this.peopleInSpaceService.getPeopleInSpace();
        return dtoViewMapper.mapDtoToView(peopleInSpaceDto);
    }
}
