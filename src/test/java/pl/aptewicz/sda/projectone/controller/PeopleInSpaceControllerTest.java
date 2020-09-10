package pl.aptewicz.sda.projectone.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.aptewicz.sda.projectone.dto.PeopleInSpaceDto;
import pl.aptewicz.sda.projectone.service.PeopleInSpaceService;
import pl.aptewicz.sda.projectone.service.mapper.PeopleInSpaceDtoViewMapper;
import pl.aptewicz.sda.projectone.view.PeopleInSpaceView;

import java.util.Collections;

public class PeopleInSpaceControllerTest {

    @Test
    public void shouldReturnInfoAboutPeopleInSpaceWhenConnectorReturnsData() throws Exception {
        // given
        final var peopleInSpaceServiceMock = Mockito.mock(PeopleInSpaceService.class);
        Mockito.when(peopleInSpaceServiceMock.getPeopleInSpace()).thenReturn(new PeopleInSpaceDto(1,
                Collections.singletonList(new PeopleInSpaceDto.HumanInSpace("ISS", "test name"))));

        final var controller = new PeopleInSpaceController(peopleInSpaceServiceMock, new PeopleInSpaceDtoViewMapper());
        final var expectedResult = new PeopleInSpaceView(1,
                Collections.singletonList(new PeopleInSpaceView.HumanInSpaceView("ISS", "test name")));

        // when
        final var result = controller.getPeopleInSpaceInfo();

        // then
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void shouldThrowExceptionWhenConnectorReturnsNoData() throws Exception {
        // given
        final var peopleInSpaceServiceMock = Mockito.mock(PeopleInSpaceService.class);
        Mockito.when(peopleInSpaceServiceMock.getPeopleInSpace()).thenThrow(new Exception());

        final var controller = new PeopleInSpaceController(peopleInSpaceServiceMock, new PeopleInSpaceDtoViewMapper());

        // when
        Assertions.assertThrows(Exception.class, controller::getPeopleInSpaceInfo);
    }
}
