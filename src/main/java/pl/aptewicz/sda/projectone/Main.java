package pl.aptewicz.sda.projectone;

import pl.aptewicz.sda.projectone.controller.IssPassTimesController;
import pl.aptewicz.sda.projectone.controller.IssPositionController;
import pl.aptewicz.sda.projectone.controller.PeopleInSpaceController;
import pl.aptewicz.sda.projectone.service.http.OpenNotifyConnector;
import pl.aptewicz.sda.projectone.service.mapper.*;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.Scanner;

public class Main {

    // Creation of required objects - this can be done by frameworks like Spring or Guice
//    private static final HttpClient httpClient =
//            HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).connectTimeout(Duration.ofSeconds(10)).build();

    private static final HttpClient httpClient = HttpClient.newHttpClient();
    //private static final JsonMapper jsonMapper = new GsonJsonMapper();
    private static final JsonMapper jsonMapper = new JacksonJsonMapper();


    private static OpenNotifyConnector openNotifyConnector = new OpenNotifyConnector(httpClient, jsonMapper);

    private static final PeopleInSpaceDtoViewMapper peopleInSpaceDtoViewMapper = new PeopleInSpaceDtoViewMapper();

    private static final PeopleInSpaceController peopleInSpaceController =
            new PeopleInSpaceController(openNotifyConnector, peopleInSpaceDtoViewMapper);

    private static final IssPositionDtoViewMapper issPositionDtoViewMapper = new IssPositionDtoViewMapper();

    private static final IssPositionController issPositionController =
            new IssPositionController(openNotifyConnector, issPositionDtoViewMapper);

    private static IssPassTimesDtoViewMapper issPassTimesDtoViewMapper = new IssPassTimesDtoViewMapper();

    private static IssPassTimesController issPassTimesControler =
            new IssPassTimesController(openNotifyConnector, issPassTimesDtoViewMapper);

    private static final Scanner keyboardScanner = new Scanner(System.in);

    public static void main(String[] args) {
        showAppTitle();
        var programRunning = true;
        while (programRunning) {
            showMenu();
            var chosenOption = keyboardScanner.nextLine();
            switch (chosenOption) {
                case "1":
                    showPeopleInSpace();
                    waitForUserAcknowledge();
                    break;
                case "2":
                    showCurrentLocationOfISS();
                    waitForUserAcknowledge();
                    break;
                case "3":
                    positionParamets();
                    showIssPassTimes();
                    waitForUserAcknowledge();
                    break;
                case "4":
                    programRunning = false;
                    System.out.println("Good bye!");
                    break;
                default:
                    showUnknownOperationInfo(chosenOption);
            }
        }
    }

    private static void showAppTitle() {
        final var appTitle = "\"SDA javaLon4 project one!";
        System.out.println(appTitle);
    }

    private static void showMenu() {
        // @formatter:off
        final var menu = "Choose menu option:\n" +
                "1 - show people in space\n" +
                "2 - show current location of ISS\n" +
                "3 - show International Space Station Pass Times\n" +
                "4 - exit";
        // @formatter:on
        System.out.println(menu);
    }

    private static void waitForUserAcknowledge() {
        System.out.println("Press Enter to continue...");
        keyboardScanner.nextLine();
    }

    private static void showPeopleInSpace() {
        try {
            final var peopleInSpaceInfo = peopleInSpaceController.getPeopleInSpaceInfo();
            System.out.println(peopleInSpaceInfo.getInfoAboutPeopleInSpace());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void showIssPassTimes() {
        try {
            var issPassTimesInfo = issPassTimesControler.getIssPassTimes();
            System.out.println(issPassTimesInfo.ShowIssPassTimes());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    private static void showCurrentLocationOfISS() {
        final var issPositionView = issPositionController.getIssPositionView();
        System.out.println(issPositionView.showIssLocation());
    }

    private static void showUnknownOperationInfo(String chosenOption) {
        final var unknownOperationInfo =
                String.format("\"%s\" option is unknown. Please specify one of the menu options!", chosenOption);
        System.err.println(unknownOperationInfo);
    }

    public static void positionParamets() {
        //TODO why range is only from -72 to 71 should be -80..80??
        System.out.println("enter a latitude value between ( -72 and 71 ): ");
        double latitude = keyboardScanner.nextDouble();
        System.out.println("enter a longtitude value between ( -180 and 180 ): ");
        double longtitude = keyboardScanner.nextDouble();
        openNotifyConnector.setLatitudeUser(latitude);
        openNotifyConnector.setLongtitudeUser(longtitude);
        overloadMethods(latitude, longtitude);
        keyboardScanner.nextLine();

    }

    public static IssPassTimesController overloadMethods(double lat, double lon) {
        openNotifyConnector = new OpenNotifyConnector(httpClient, jsonMapper,lat,lon);
        issPassTimesDtoViewMapper = new IssPassTimesDtoViewMapper();
        issPassTimesControler = new IssPassTimesController(openNotifyConnector, issPassTimesDtoViewMapper);
        return issPassTimesControler;
    }
}
