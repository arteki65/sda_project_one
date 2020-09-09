package pl.aptewicz.sda.projectone.view;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

public class IssPositionView {

    private final long timestamp;

    private final IssCurrentPositionView issCurrentPositionView;

    public IssPositionView(long timestamp, IssCurrentPositionView issPositionView) {
        this.timestamp = timestamp;
        this.issCurrentPositionView = issPositionView;
    }
    public String showIssLocation() {
        return String.format("%s - Currently the ISS is located at longitude: %f and latitude: %f", getTimestamp(),
                this.issCurrentPositionView.getLongitude(),
                this.issCurrentPositionView.getLatitude());
    }

    public LocalDateTime getTimestamp() {

        return  LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());
    }

    public IssCurrentPositionView getIssCurrentPositionView() {
        return issCurrentPositionView;
    }

    public static class IssCurrentPositionView {

        private final double longitude;
        private final double latitude;


        public IssCurrentPositionView(double longitude, double latitude) {
            this.longitude = longitude;
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public double getLatitude() {
            return latitude;
        }


    }
}
