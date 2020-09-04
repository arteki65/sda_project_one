package pl.aptewicz.sda.projectone.service.http;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.aptewicz.sda.projectone.service.http.service.formatter.JsonResponseFormatter;
import pl.aptewicz.sda.projectone.service.http.service.http.OpenNotifyConnector;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class OpenNotifyConnectorTest {

    @Test
    public void shouldReturnFormattedInfoAboutPeopleInSpace() throws IOException, InterruptedException {
        // given
        final var mockHttpClient = Mockito.mock(HttpClient.class);
        final var mockHttpResponse = Mockito.mock(HttpResponse.class);
        String jsonResponse = "{\"number\": 3, \"people\": [{\"craft\": \"ISS\", \"name\": \"Chris Cassidy\"}," +
                "{\"craft\": \"ISS\", \"name\": \"Anatoly Ivanishin\"}," +
                "{\"craft\": \"ISS\", \"name\": \"Ivan Vagner\"}]," + "\"message\": \"success\"}";
        Mockito.when(mockHttpResponse.body()).thenReturn(jsonResponse);
        Mockito.when(mockHttpClient.send(Mockito.any(), Mockito.eq(HttpResponse.BodyHandlers.ofString())))
                .thenReturn(mockHttpResponse);

        final var openNotifyConnector = new OpenNotifyConnector(new JsonResponseFormatter(), mockHttpClient);

        final var expectedResult = "Currently there are 3 people in space:\n" + "Chris Cassidy on craft ISS\n" +
                "Anatoly Ivanishin on craft ISS\n" + "Ivan Vagner on craft ISS\n";

        // when
        final var result = openNotifyConnector.getPeopleInSpace();

        // then
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void shouldReturnErrorInfoWhenHttpClientThrowsException() throws IOException, InterruptedException {
        // given
        final var mockHttpClient = Mockito.mock(HttpClient.class);
        final var mockHttpResponse = Mockito.mock(HttpResponse.class);
        String jsonResponse = "{\"number\": 3, \"people\": [{\"craft\": \"ISS\", \"name\": \"Chris Cassidy\"}," +
                "{\"craft\": \"ISS\", \"name\": \"Anatoly Ivanishin\"}," +
                "{\"craft\": \"ISS\", \"name\": \"Ivan Vagner\"}]," + "\"message\": \"success\"}";
        Mockito.when(mockHttpResponse.body()).thenReturn(jsonResponse);
        Mockito.when(mockHttpClient.send(Mockito.any(), Mockito.eq(HttpResponse.BodyHandlers.ofString())))
                .thenThrow(new IOException());

        final var openNotifyConnector = new OpenNotifyConnector(new JsonResponseFormatter(), mockHttpClient);

        // when
        final var result = openNotifyConnector.getPeopleInSpace();

        // then
        Assertions.assertTrue(result.contains("Error"));
    }
}
