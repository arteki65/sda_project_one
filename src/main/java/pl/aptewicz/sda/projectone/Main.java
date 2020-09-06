package pl.aptewicz.sda.projectone;

import pl.aptewicz.sda.projectone.service.formatter.JsonResponseFormatter;
import pl.aptewicz.sda.projectone.service.http.OpenNotifyConnector;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.Scanner;

public class Main {

    private static final HttpClient httpClient =
            HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).connectTimeout(Duration.ofSeconds(10)).build();

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
                "3 - exit";
        // @formatter:on
        System.out.println(menu);
    }

    private static void waitForUserAcknowledge() {
        System.out.println("Press any key to continue...");
        keyboardScanner.nextLine();
    }

    private static void showPeopleInSpace() {
        final var openNotifyConnector = new OpenNotifyConnector(new JsonResponseFormatter(), httpClient);
        System.out.println(openNotifyConnector.getPeopleInSpace());
    }

    private static void showCurrentLocationOfISS() {
        final var openNotifyConnector = new OpenNotifyConnector(new JsonResponseFormatter(), httpClient);
        System.out.println(openNotifyConnector.getIssPosition());
    }

    private static void showUnknownOperationInfo(String chosenOption) {
        final var unknownOperationInfo =
                String.format("\"%s\" option is unknown. Please specify one of the menu options!", chosenOption);
        System.err.println(unknownOperationInfo);
    }
}
