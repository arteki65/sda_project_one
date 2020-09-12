package pl.aptewicz.sda.projectone.service;

import pl.aptewicz.sda.projectone.dto.IssPositionDto;
import pl.aptewicz.sda.projectone.repository.IssPositionRepository;
import pl.aptewicz.sda.projectone.service.http.OpenNotifyConnector;
import pl.aptewicz.sda.projectone.service.mapper.IssPositionEntityMapper;

public class IssPositionService {

    private final OpenNotifyConnector openNotifyConnector;

    private final IssPositionRepository issPositionRepository;

    private final IssPositionEntityMapper issPositionEntityMapper;

    public IssPositionService(OpenNotifyConnector openNotifyConnector, IssPositionRepository issPositionRepository,
                              IssPositionEntityMapper issPositionEntityMapper) {
        this.openNotifyConnector = openNotifyConnector;
        this.issPositionRepository = issPositionRepository;
        this.issPositionEntityMapper = issPositionEntityMapper;
    }

    // TODO: implement
    public IssPositionDto getIssPosition() throws Exception {
        // get iss position from internet using openNotifyConnector
        // if success then save result in db using repository
        // return dto to controller or throw exception
        throw new UnsupportedOperationException();
    }
}
