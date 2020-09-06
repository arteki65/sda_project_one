package pl.aptewicz.sda.projectone.dto;

import com.google.gson.annotations.SerializedName;

import java.awt.geom.CubicCurve2D;
import java.util.List;

public class CurrentLocationDto {

    @SerializedName("iss_position")
    private final PositionInSpace issPosition;
    private final int timestamp;

    public CurrentLocationDto(PositionInSpace issPosition, int timestamp) {
        this.issPosition = issPosition;
        this.timestamp = timestamp;
    }

    public PositionInSpace getPositionInSpace() {
        return issPosition;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public static class PositionInSpace {

        private final String longitude;
        private final String latitude;

        PositionInSpace(String longitude, String latitude) {
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



