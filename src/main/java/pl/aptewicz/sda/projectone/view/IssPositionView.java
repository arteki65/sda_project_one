package pl.aptewicz.sda.projectone.view;



import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;


public class IssPositionView {

    private long timestamp;

    private IssCurrentPositionView issPositionView;




    public IssPositionView(long timestamp, IssCurrentPositionView issPositionView) {
        this.timestamp = timestamp;
        this.issPositionView = issPositionView;
    }




    public String showIssLocation() {

        return String.format("Currently the ISS is: \n  Data :  %S \n  on longitude:  %s ", TimestampToDate(timestamp),
                this.issPositionView.getLongitude()
                + "\n  on latitude :  " + this.issPositionView.getLatitude() + "");
    }

     public LocalDateTime TimestampToDate(Long timestamp) {
//        Timestamp ts = new Timestamp(System.currentTimeMillis());
//         LocalDate localDateTs = ts.toLocalDateTime().toLocalDate();
//        return  localDateTs;
         LocalDateTime IssData = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), TimeZone
                 .getDefault().toZoneId());
         return  IssData;
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
