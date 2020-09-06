package pl.aptewicz.sda.projectone.view;

import com.google.gson.annotations.SerializedName;
import pl.aptewicz.sda.projectone.dto.CurrentLocationDto;

import java.util.List;
import java.util.stream.Collectors;

public class CurrentLocationView {


    private final CurrentLocationInfoView issPosition;
    private final int timestamp;

    public CurrentLocationView(CurrentLocationInfoView issPosition, int timestamp) {
        this.issPosition = issPosition;
        this.timestamp = timestamp;
    }

    public String showInfoAboutCurrentLocation() {
        return String.format("International space station current location is %s. The timestamp:\n %d",
                this.issPosition, this.timestamp );
    }

    public static class CurrentLocationInfoView {
        private final String longitude;
        private final String latitude;

        CurrentLocationInfoView (String longitude, String latitude) {
            this.longitude = longitude;
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public String getLatitude() {
            return latitude;
        }
    }
}
