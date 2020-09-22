package pl.aptewicz.sda.projectone.repository;

import pl.aptewicz.sda.projectone.db.DBSetup;
import pl.aptewicz.sda.projectone.entity.IssPositionEntity;
import pl.aptewicz.sda.projectone.service.logger.LoggerService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IssPositionMySqlRepository implements IssPositionRepository {

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

    @Override
    public List<IssPositionEntity> getIssPosition() {

        List<IssPositionEntity> issPositionEntities = new ArrayList<>();
        try {
            PreparedStatement ps = getConnection()
                    .prepareStatement("SELECT * FROM iss_position ORDER by timestamp DESC LIMIT 2");

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
            IssPositionEntity issPositionEntity =
                    new IssPositionEntity(UUID.fromString(resultSet.getString("id")),
                            resultSet.getDouble("longitude"),
                            resultSet.getDouble("latitude"),
                            resultSet.getLong("timestamp"));
            issPositionEntities.add(issPositionEntity);

            }

        } catch (SQLException e) {
            loggerService.logError("Can't get ISSPosition", e);

        }


        return issPositionEntities;
    }

    private Connection getConnection() {

        return dbSetup.getDbConnection();

    }


}
