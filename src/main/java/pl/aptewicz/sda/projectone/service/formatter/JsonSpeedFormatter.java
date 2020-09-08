package pl.aptewicz.sda.projectone.service.formatter;

import com.google.gson.Gson;
import pl.aptewicz.sda.projectone.dto.IssSpeedDto;

import java.net.http.HttpResponse;

public class JsonSpeedFormatter implements SpeedResponseFormatter {

   private static final Gson gson = new Gson();


    @Override
    public IssSpeedDto formatResponse(HttpResponse<String> response) {

        final String json = response.body();

        return gson.fromJson(json,IssSpeedDto.class);

    }
}
