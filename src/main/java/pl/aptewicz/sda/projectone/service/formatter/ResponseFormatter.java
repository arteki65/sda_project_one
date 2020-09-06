package pl.aptewicz.sda.projectone.service.formatter;

import java.net.http.HttpResponse;

public interface ResponseFormatter {
    String formatResponse(HttpResponse<String> response);
    String formatIssPosition(HttpResponse<String> response);
}
