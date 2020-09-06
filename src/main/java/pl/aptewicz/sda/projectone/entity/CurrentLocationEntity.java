package pl.aptewicz.sda.projectone.entity;

import com.google.gson.annotations.SerializedName;
import pl.aptewicz.sda.projectone.dto.CurrentLocationDto;

import java.util.List;

public class CurrentLocationEntity {

    private final long id;
    private final PositionInSpaceEntity issPosition;
    private final int timestamp;

    public CurrentLocationEntity(long id, PositionInSpaceEntity issPosition, int timestamp) {
        this.id = id;
        this.issPosition = issPosition;
        this.timestamp = timestamp;
    }

    public PositionInSpaceEntity getIssPossition() {
        return issPosition;
    }

    public int getTimestamp() {
        return timestamp;
    }
    public long getId() {
        return id;
    }

    public static class PositionInSpaceEntity {

        private final String longitude;
        private final String latitude;

        PositionInSpaceEntity(String longitude, String latitude) {
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
