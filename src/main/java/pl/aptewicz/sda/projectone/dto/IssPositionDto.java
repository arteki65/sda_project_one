package pl.aptewicz.sda.projectone.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IssPositionDto {

    @SerializedName("iss_position")
    private final IssPosition issPosition;
    private final long timestamp;

    @JsonCreator
    public IssPositionDto(@JsonProperty("iss_position") IssPosition issPosition,@JsonProperty("timestamp") long timestamp) {
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


        public IssPosition(@JsonProperty("longitude") double longitude,@JsonProperty("latitude") double latitude) {
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
