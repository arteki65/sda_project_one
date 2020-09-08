package pl.aptewicz.sda.projectone.view;

import java.util.List;
import java.util.stream.Collectors;

public class IssPositionView {

    private final long timestamp;

    private final IssCurrentPositionView issPositionView;

    public IssPositionView(long timestamp, IssCurrentPositionView issPositionView) {
        this.timestamp = timestamp;
        this.issPositionView = issPositionView;
    }
    public String showIssLocation() {
        return String.format("Currently the ISS is: \n  timestamp :  %d \n  on longitude:  %s ", this.timestamp, this.issPositionView.getLongitude()
                 + "\n  on latitude :  " + this.issPositionView.getLatitude() + "");
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
