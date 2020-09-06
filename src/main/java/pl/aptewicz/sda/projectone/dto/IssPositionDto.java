package pl.aptewicz.sda.projectone.dto;

import com.google.gson.annotations.SerializedName;

public class IssPositionDto {

    @SerializedName("iss_position")
    private final IssPosition issPosition;
    private final long timestamp;

    public IssPositionDto(IssPosition issPosition, long timestamp) {
        this.issPosition = issPosition;
        this.timestamp = timestamp;
    }


    public long getTimestamp() {
        return timestamp;
    }

    public IssPosition getIssPosition() {
        return issPosition;
    }

    public static class IssPosition {
        private final double longitude;

        private final double latitude;

        public IssPosition(double longitude, double latitude) {
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
