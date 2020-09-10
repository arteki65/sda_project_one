/*
package pl.aptewicz.sda.projectone.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.aptewicz.sda.projectone.dto.PeopleInSpaceDto;
import pl.aptewicz.sda.projectone.service.http.OpenNotifyConnector;
import pl.aptewicz.sda.projectone.service.mapper.PeopleInSpaceDtoViewMapper;
import pl.aptewicz.sda.projectone.view.PeopleInSpaceView;

import java.util.Collections;
import java.util.Optional;

public class PeopleInSpaceControllerTest {

    @Test
    public void shouldReturnInfoAboutPeopleInSpaceWhenConnectorReturnsData() throws Exception {
        // given
        final var mockConnector = Mockito.mock(OpenNotifyConnector.class);
        Mockito.when(mockConnector.getPeopleInSpace()).thenReturn(Optional.of(new PeopleInSpaceDto(1,
                Collections.singletonList(new PeopleInSpaceDto.HumanInSpace("ISS", "test name")))));

        final var controller = new PeopleInSpaceController(mockConnector, peopleInSpaceService, new PeopleInSpaceDtoViewMapper());
        final var expectedResult = new PeopleInSpaceView(1,
                Collections.singletonList(new PeopleInSpaceView.HumanInSpaceView("ISS", "test name")));

        // when
        final var result = controller.getPeopleInSpaceInfo();

        // then
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void shouldThrowExceptionWhenConnectorReturnsNoData() {
        // given
        final var mockConnector = Mockito.mock(OpenNotifyConnector.class);
        Mockito.when(mockConnector.getPeopleInSpace()).thenReturn(Optional.empty());

        final var controller = new PeopleInSpaceController(mockConnector, peopleInSpaceService, new PeopleInSpaceDtoViewMapper());

        // when
        try {
            controller.getPeopleInSpaceInfo();
        } catch (Exception e) {
            Assertions.assertTrue(true);
            return;
        }
        Assertions.fail();
    }
}
*/
