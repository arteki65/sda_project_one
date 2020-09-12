package pl.aptewicz.sda.projectone.controller;

import pl.aptewicz.sda.projectone.service.IssPositionService;
import pl.aptewicz.sda.projectone.service.http.OpenNotifyConnector;
import pl.aptewicz.sda.projectone.service.mapper.IssPositionDtoViewMapper;
import pl.aptewicz.sda.projectone.view.IssPositionView;
import pl.aptewicz.sda.projectone.view.IssSpeedView;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class IssSpeedController extends IssPositionController {


    public IssSpeedController(OpenNotifyConnector openNotifyConnector, IssPositionDtoViewMapper mapper,
                              IssPositionService issPositionService) {
        super(openNotifyConnector, mapper, issPositionService);
    }

    public IssSpeedView getIssSpeedView() throws Exception {

        IssPositionView issPositionView1 = getIssPositionView();
        TimeUnit.SECONDS.sleep(5);
        IssPositionView issPositionView2 = getIssPositionView();

        return new IssSpeedView(Arrays.asList(issPositionView1, issPositionView2));
    }
}
