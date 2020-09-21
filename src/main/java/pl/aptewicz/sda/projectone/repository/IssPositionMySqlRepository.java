package pl.aptewicz.sda.projectone.repository;

import pl.aptewicz.sda.projectone.db.DBSetup;
import pl.aptewicz.sda.projectone.entity.IssPositionEntity;
import pl.aptewicz.sda.projectone.service.logger.LoggerService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IssPositionMySqlRepository implements IssPositionRepository {

    private final String TABLE = "iss_position";
    private final DBSetup dbSetup;
    private final LoggerService loggerService;

    public IssPositionMySqlRepository(DBSetup dbSetup, LoggerService loggerService) {
        this.dbSetup = dbSetup;
        this.loggerService = loggerService;
    }

    @Override
    public void saveIssPosition(IssPositionEntity entity) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO iss_position VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, entity.getId().toString());
            preparedStatement.setDouble(2, entity.getLongitude());
            preparedStatement.setDouble(3, entity.getLatitude());
            preparedStatement.setLong(4, entity.getTimestamp());
            preparedStatement.executeUpdate();


        } catch (SQLException throwables) {
            loggerService.logError("Can't save to database", throwables);

        }


    }

    private Connection getConnection() {

        return dbSetup.getDbConnection();

    }


}
