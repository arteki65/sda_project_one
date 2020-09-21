package pl.aptewicz.sda.projectone.db;

import pl.aptewicz.sda.projectone.service.logger.LoggerService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBSetup {

    private final Connection dbConnection;

    private final LoggerService loggerService;

    public DBSetup(String dbUser, String dbPass, String dbHost, String dbName, LoggerService loggerService)
            throws SQLException {
        this.loggerService = loggerService;
        this.dbConnection = DriverManager
                .getConnection(String.format("jdbc:mysql://%s/%s?user=%s&password=%s&serverTimezone=GMT", dbHost,
                        dbName, dbUser, dbPass));
    }

    public Connection getDbConnection() {
        return dbConnection;
    }

    public void closeDbConnection() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            this.loggerService.logError("Error while closing db connection!", e);
        }
    }
}
