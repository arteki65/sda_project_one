package pl.aptewicz.sda.projectone.service;

import pl.aptewicz.sda.projectone.repository.IssPositionRepository;
import pl.aptewicz.sda.projectone.service.http.OpenNotifyConnector;
import pl.aptewicz.sda.projectone.service.mapper.IssPositionEntityMapper;

public class IssSpeedService extends IssPositionService {
    public IssSpeedService(OpenNotifyConnector openNotifyConnector, IssPositionRepository issPositionRepository, IssPositionEntityMapper issPositionEntityMapper) {
        super(openNotifyConnector, issPositionRepository, issPositionEntityMapper);
    }
}
