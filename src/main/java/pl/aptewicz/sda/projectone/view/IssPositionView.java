package pl.aptewicz.sda.projectone.view;

import java.util.List;
import java.util.stream.Collectors;

public class IssPositionView {

    private final long timestamp;

    private final List<IssCurrentPositionView> issPositionView;

    public IssPositionView(long timestamp, List<IssCurrentPositionView> issPositionView) {
        this.timestamp = timestamp;
        this.issPositionView = issPositionView;
    }
    public String showInfoAboutPeopleInSpace() {
        return String.format("Currently the ISS is  %d :\n%s", this.timestamp, this.issPositionView.stream()
                .map(issCurrentPositionView -> issCurrentPositionView.getLatitude() + " on longitude" + issCurrentPositionView.getLongitude() + "\n")
                .collect(Collectors.joining()));
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
