package pl.aptewicz.sda.projectone.dto;

import java.util.List;

public class IssSpeedDto {

    private final Long timestamp;
    private final Position iss_position;

    public IssSpeedDto(Long timestamp, Position issPosition) {
        this.timestamp = timestamp;
        this.iss_position = issPosition;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Position getIssPosition() {
        return iss_position;
    }

    public static class Position {

        private final String longitude;
        private final String latitude;

        public Position(String longitude, String latitude) {
            this.longitude = longitude;
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return Double.parseDouble(longitude);
        }

        public Double getLatitude() {
            return Double.parseDouble(latitude);
        }
    }

}
