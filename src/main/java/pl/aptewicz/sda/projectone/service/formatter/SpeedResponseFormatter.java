package pl.aptewicz.sda.projectone.service.formatter;

import pl.aptewicz.sda.projectone.dto.IssSpeedDto;

import java.net.http.HttpResponse;

public interface SpeedResponseFormatter {

    IssSpeedDto formatResponse(HttpResponse<String> response);
}
