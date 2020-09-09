package pl.aptewicz.sda.projectone.view;

import pl.aptewicz.sda.projectone.dto.IssSpeedDto;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class IssSpeedView {

    private List<IssPositionView> positions;

    public IssSpeedView(List<IssPositionView> positions) {
        this.positions = positions;
    }

    public String showISSSpeed() {

        double longitude1 = positions.get(0).getIssCurrentPositionView().getLongitude();
        double latitude1 = positions.get(0).getIssCurrentPositionView().getLatitude();
        long timestamp1 = positions.get(0).getTimestamp();
       // TimeUnit.SECONDS.sleep(5);
        double longitude2 = positions.get(1).getIssCurrentPositionView().getLongitude();
        double latitude2 = positions.get(1).getIssCurrentPositionView().getLatitude();
        long timestamp2 = positions.get(1).getTimestamp();


        Instant one = Instant.ofEpochSecond(timestamp1);
        Instant two = Instant.ofEpochSecond(timestamp2);
        Duration duration = Duration.between(one, two);
        long timeElapsed = duration.getSeconds();
        double distance = haversine(latitude1, longitude1, latitude2, longitude2);

        double speed = distance / timeElapsed;


        return String.format("Average speed is %f ", speed);

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





}
