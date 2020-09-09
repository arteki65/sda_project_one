package pl.aptewicz.sda.projectone.controller;

import pl.aptewicz.sda.projectone.service.http.OpenNotifyConnector;
import pl.aptewicz.sda.projectone.service.mapper.IssPositionDtoViewMapper;
import pl.aptewicz.sda.projectone.view.IssPositionView;
import pl.aptewicz.sda.projectone.view.IssSpeedView;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class IssSpeedController extends IssPositionController{


    public IssSpeedController(OpenNotifyConnector openNotifyConnector, IssPositionDtoViewMapper mapper) {
        super(openNotifyConnector, mapper);
    }

    public IssSpeedView getIssSpeedView() throws Exception {

        IssPositionView issPositionView1 = mapper.mapToView(openNotifyConnector.getIssPosition().orElseThrow(() -> new Exception("Error")));

        TimeUnit.SECONDS.sleep(5);

        IssPositionView issPositionView2 = mapper.mapToView(openNotifyConnector.getIssPosition().orElseThrow(() -> new Exception("Error")));

        return IssSpeedView()
    }
}
