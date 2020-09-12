package pl.aptewicz.sda.projectone.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.aptewicz.sda.projectone.dto.IssPositionDto;
import pl.aptewicz.sda.projectone.service.IssPositionService;
import pl.aptewicz.sda.projectone.service.http.OpenNotifyConnector;
import pl.aptewicz.sda.projectone.service.mapper.IssPositionDtoViewMapper;
import pl.aptewicz.sda.projectone.view.IssPositionView;

import java.util.Optional;

public class IssPositionControllerTest {

    @Test
    public void shouldReturnCurrentISSPositionViewWhenConnectorReturnData() throws Exception {
        //given
        final OpenNotifyConnector openNotifyConnector = Mockito.mock(OpenNotifyConnector.class);
        Mockito.when(openNotifyConnector.getIssPosition()).thenReturn(Optional.of(new IssPositionDto(new IssPositionDto.IssPosition(
                321.321,459.459), 569874562L)));
        final IssPositionController issPositionController = new IssPositionController(openNotifyConnector, new IssPositionDtoViewMapper(),
                Mockito.mock(IssPositionService.class));
        final IssPositionView issPositionViewExpected = new IssPositionView(
                569874562L,new IssPositionView.IssCurrentPositionView(321.321, 459.459 ));
        //when
        final IssPositionView issPositionView = issPositionController.getIssPositionView();

        //then
        Assertions.assertEquals(issPositionViewExpected, issPositionView);

    }
}
