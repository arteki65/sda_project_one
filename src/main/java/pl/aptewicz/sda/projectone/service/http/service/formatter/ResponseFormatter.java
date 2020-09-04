package pl.aptewicz.sda.projectone.service.http.service.formatter;

import java.net.http.HttpResponse;

public interface ResponseFormatter {
    String formatResponse(HttpResponse<String> response);
}
