package pl.aptewicz.sda.projectone.entity;

import java.util.List;

public class IssPositionEntity {

    private final long id;
    private final List<IssCurrentPositionEntity> issPositionEntity;
    private final long timestamp;

    public IssPositionEntity(long id, List<IssCurrentPositionEntity> issPositionEntity, long timestamp) {
        this.id = id;
        this.issPositionEntity = issPositionEntity;
        this.timestamp = timestamp;
    }


    public long getId() {
        return id;
    }

    public List<IssCurrentPositionEntity> getIssPositionEntity() {
        return issPositionEntity;
    }

    public long getTimestamp() {
        return timestamp;
    }


    public static class IssCurrentPositionEntity {
        private final double longitude;
        private final double latitude;

        public IssCurrentPositionEntity(double longitude, double latitude) {
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
