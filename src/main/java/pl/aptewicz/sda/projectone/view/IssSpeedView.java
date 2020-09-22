package pl.aptewicz.sda.projectone.view;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class IssSpeedView {

    private final List<IssPositionView> positions;

    public IssSpeedView(List<IssPositionView> positions) {
        this.positions = positions;
    }

    public String showISSSpeed() {

        double longitude1 = positions.get(1).getIssCurrentPositionView().getLongitude();
        double latitude1 = positions.get(1).getIssCurrentPositionView().getLatitude();
        LocalDateTime timestamp1 = positions.get(1).getTimestamp();

        double longitude2 = positions.get(0).getIssCurrentPositionView().getLongitude();
        double latitude2 = positions.get(0).getIssCurrentPositionView().getLatitude();
        LocalDateTime timestamp2 = positions.get(0).getTimestamp();


        long timeElapsed = Duration.between(timestamp1, timestamp2).getSeconds();
        double distance = haversine(latitude1, longitude1, latitude2, longitude2);

        double speed = (distance / timeElapsed) * 3600;


        return String.format("The current average ISS speed is %.2f km/h", speed);

    }

    private double haversine(double lat1, double lon1, double lat2, double lon2) {

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);


        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssSpeedView that = (IssSpeedView) o;
        return Objects.equals(positions, that.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positions);
    }
}
