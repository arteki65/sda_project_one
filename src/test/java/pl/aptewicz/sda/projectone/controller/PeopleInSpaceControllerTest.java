package pl.aptewicz.sda.projectone.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.aptewicz.sda.projectone.dto.PeopleInSpaceDto;
import pl.aptewicz.sda.projectone.service.http.OpenNotifyConnector;
import pl.aptewicz.sda.projectone.service.mapper.PeopleInSpaceDtoViewMapper;

import java.util.Collections;
import java.util.Optional;

public class PeopleInSpaceControllerTest {

    @Test
    public void shouldReturnInfoAboutPeopleInSpaceWhenConnectorReturnsData() throws Exception {
        // given
        final var mockConnector = Mockito.mock(OpenNotifyConnector.class);
        Mockito.when(mockConnector.getPeopleInSpace()).thenReturn(Optional.of(new PeopleInSpaceDto(1,
                Collections.singletonList(new PeopleInSpaceDto.HumanInSpace("ISS", "test name")))));

        final var controller = new PeopleInSpaceController(mockConnector, new PeopleInSpaceDtoViewMapper());
        final var expectedResult = "Currently  there are 1 people in space:\n" + "test name on craft ISS\n";

        // when
        final var result = controller.getPeopleInSpaceInfo();

        // then
        Assertions.assertEquals(expectedResult, result);
    }
}
