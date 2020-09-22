package pl.aptewicz.sda.projectone;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;
import pl.aptewicz.sda.projectone.config.AppConfig;
import pl.aptewicz.sda.projectone.controller.IssPositionController;
import pl.aptewicz.sda.projectone.controller.IssSpeedController;
import pl.aptewicz.sda.projectone.controller.PeopleInSpaceController;
import pl.aptewicz.sda.projectone.db.DBSetup;
import pl.aptewicz.sda.projectone.repository.HumanInSpaceMySqlRepository;
import pl.aptewicz.sda.projectone.repository.HumanInSpaceRepository;
import pl.aptewicz.sda.projectone.repository.IssPositionMySqlRepository;
import pl.aptewicz.sda.projectone.repository.IssPositionRepository;
import pl.aptewicz.sda.projectone.service.IssPositionService;
import pl.aptewicz.sda.projectone.service.IssSpeedService;
import pl.aptewicz.sda.projectone.service.PeopleInSpaceService;
import pl.aptewicz.sda.projectone.service.cli.CliArgsParser;
import pl.aptewicz.sda.projectone.service.http.OpenNotifyConnector;
import pl.aptewicz.sda.projectone.service.logger.LoggerService;
import pl.aptewicz.sda.projectone.service.mapper.*;
import pl.aptewicz.sda.projectone.view.IssPositionView;
import pl.aptewicz.sda.projectone.view.IssSpeedView;

import java.net.http.HttpClient;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Optional;
import java.util.Scanner;

import static pl.aptewicz.sda.projectone.config.CliUsageConfig.CLI_OPTIONS;

public class Main {

    // @formatter:off
    // Creation of required objects - this can be done by frameworks like Spring or Guice
    private static final LoggerService loggerService = new LoggerService();

    private static final HttpClient httpClient =
            HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).connectTimeout(Duration.ofSeconds(10)).build();

    private static final JsonMapper jsonMapper = new GsonJsonMapper();

    private static final OpenNotifyConnector openNotifyConnector = new OpenNotifyConnector(httpClient, jsonMapper);

    private static final PeopleInSpaceDtoViewMapper peopleInSpaceDtoViewMapper = new PeopleInSpaceDtoViewMapper();

    private static final HumanInSpaceEntityMapper humanInSpaceEntityMapper = new HumanInSpaceEntityMapper();

    private static final IssPositionEntityMapper issPositionEntityMapper = new IssPositionEntityMapper();

    private static final IssPositionDtoViewMapper issPositionDtoViewMapper = new IssPositionDtoViewMapper();

    private static final Scanner keyboardScanner = new Scanner(System.in);

    private static final CliArgsParser cliArgsParser = new CliArgsParser();

    private static DBSetup dbSetup;

    private static HumanInSpaceRepository humanInSpaceRepository;

    private static IssPositionRepository issPositionRepository;

    private static PeopleInSpaceService peopleInSpaceService;

    private static IssPositionService issPositionService;

    private static IssSpeedService issSpeedService;

    private static PeopleInSpaceController peopleInSpaceController;

    private static IssPositionController issPositionController;

    private static IssSpeedController issSpeedController;

    private static AppConfig appConfig;
    // @formatter:on

    public static void main(String[] args) {

        initAppConfig(args);
        initDb();
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
                    showCurrentISSSpeed();
                    waitForUserAcknowledge();
                    break;
                case "4":
                    programRunning = false;
                    dbSetup.closeDbConnection();
                    System.out.println("Good bye!");
                    break;
                default:
                    showUnknownOperationInfo(chosenOption);
            }
        }
    }

    private static void initAppConfig(String[] args) {
        try {
            appConfig = cliArgsParser.parseAppConfig(args);
            loggerService.setIsDebugMode(appConfig.isDebugMode());
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            final var helpFormatter = new HelpFormatter();
            helpFormatter.printHelp("following program args are allowed", CLI_OPTIONS);
            System.exit(1);
        }
    }

    private static void initDb() {
        try {
            dbSetup = new DBSetup(appConfig.getDbUser(), appConfig.getDbPass(), appConfig.getDbHost(),
                    appConfig.getDbName(), loggerService);

            humanInSpaceRepository = new HumanInSpaceMySqlRepository(humanInSpaceEntityMapper, loggerService, dbSetup);

            peopleInSpaceService = new PeopleInSpaceService(openNotifyConnector, humanInSpaceRepository, humanInSpaceEntityMapper);

            peopleInSpaceController = new PeopleInSpaceController(peopleInSpaceService, peopleInSpaceDtoViewMapper);

            issPositionRepository = new IssPositionMySqlRepository(dbSetup, loggerService);

            issPositionService = new IssPositionService(openNotifyConnector, issPositionRepository, issPositionEntityMapper);

            issPositionController = new IssPositionController(openNotifyConnector, issPositionDtoViewMapper, issPositionService);

            issSpeedService = new IssSpeedService(openNotifyConnector, issPositionRepository, issPositionEntityMapper);

            issSpeedController = new IssSpeedController(openNotifyConnector, issPositionDtoViewMapper, issPositionService);

        } catch (SQLException e) {
            loggerService.logError("Setup of database connection failed!", e);
            System.err.println("There is a problem with database connection, app cannot start...");
            System.exit(1);
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
                "3 - show the current ISS speed\n" +
                "4 - exit";
        // @formatter:on
        System.out.println(menu);
    }

    private static void waitForUserAcknowledge() {
        System.out.println("Press any key to continue...");
        keyboardScanner.nextLine();
    }

    private static void showPeopleInSpace() {
        try {
            final var start = System.currentTimeMillis();
            final var peopleInSpaceInfo = peopleInSpaceController.getPeopleInSpaceInfo();
            final var stop = System.currentTimeMillis();
            System.out.println(peopleInSpaceInfo.getInfoAboutPeopleInSpace());
            System.out.println("Fetched in " + (stop - start) + " ms");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void showCurrentLocationOfISS() {
        try {
            final IssPositionView issPositionView = issPositionController.getIssPositionView();
            System.out.println(issPositionView.showIssLocation());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void showCurrentISSSpeed() {

        try {
            final IssSpeedView issSpeedView = issSpeedController.getIssSpeedView();
            System.out.println(issSpeedView.showISSSpeed());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void showUnknownOperationInfo(String chosenOption) {
        final var unknownOperationInfo =
                String.format("\"%s\" option is unknown. Please specify one of the menu options!", chosenOption);
        System.err.println(unknownOperationInfo);
    }

}
