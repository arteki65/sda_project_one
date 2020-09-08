package pl.aptewicz.sda.projectone.view;

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
        return String.format("Currently the ISS is  %d : longitude: %f, latitude: %f", this.timestamp, this.issCurrentPositionView.getLongitude(),
                this.issCurrentPositionView.latitude);
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
