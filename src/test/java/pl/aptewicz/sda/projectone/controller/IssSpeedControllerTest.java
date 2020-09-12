package pl.aptewicz.sda.projectone.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.aptewicz.sda.projectone.dto.IssPositionDto;
import pl.aptewicz.sda.projectone.service.IssPositionService;
import pl.aptewicz.sda.projectone.service.http.OpenNotifyConnector;
import pl.aptewicz.sda.projectone.service.mapper.IssPositionDtoViewMapper;
import pl.aptewicz.sda.projectone.view.IssPositionView;
import pl.aptewicz.sda.projectone.view.IssSpeedView;

import java.util.Arrays;
import java.util.Optional;

public class IssSpeedControllerTest {

    @Test
    public void shouldReturnCurrentISSSpeedViewWhenConnectorReturnData() throws Exception {

        //given
        OpenNotifyConnector openNotifyConnector = Mockito.mock(OpenNotifyConnector.class);
        Mockito.when(openNotifyConnector.getIssPosition()).thenReturn(
                Optional.of(new IssPositionDto(new IssPositionDto.IssPosition(123.123, 123.123), 45698745L)))
                .thenReturn(
                        Optional.of(new IssPositionDto(new IssPositionDto.IssPosition(456.456, 456.456), 59874562L)));

        IssSpeedController issSpeedController =
                new IssSpeedController(openNotifyConnector, new IssPositionDtoViewMapper(),
                        Mockito.mock(IssPositionService.class));

        IssSpeedView issSpeedViewExpected = new IssSpeedView(Arrays.asList(
                new IssPositionView(45698745L, new IssPositionView.IssCurrentPositionView(123.123, 123.123)),
                new IssPositionView(59874562L, new IssPositionView.IssCurrentPositionView(456.456, 456.456))));

        //when
        IssSpeedView issSpeedViewResult = issSpeedController.getIssSpeedView();

        //then

        Assertions.assertEquals(issSpeedViewExpected, issSpeedViewResult);


    }
}
