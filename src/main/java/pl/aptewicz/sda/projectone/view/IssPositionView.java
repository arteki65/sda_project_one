package pl.aptewicz.sda.projectone.view;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssPositionView that = (IssPositionView) o;
        return timestamp == that.timestamp &&
                Objects.equals(issCurrentPositionView, that.issCurrentPositionView);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, issCurrentPositionView);
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            IssCurrentPositionView that = (IssCurrentPositionView) o;
            return Double.compare(that.longitude, longitude) == 0 &&
                    Double.compare(that.latitude, latitude) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(longitude, latitude);
        }
    }
}
