package pl.aptewicz.sda.projectone.repository;

import pl.aptewicz.sda.projectone.db.DBSetup;
import pl.aptewicz.sda.projectone.entity.HumanInSpaceEntity;
import pl.aptewicz.sda.projectone.service.logger.LoggerService;
import pl.aptewicz.sda.projectone.service.mapper.HumanInSpaceEntityMapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class HumanInSpaceMySqlRepository implements HumanInSpaceRepository {
    private static final String TABLE = "human_in_space";

    private final HumanInSpaceEntityMapper mapper;

    private final LoggerService loggerService;

    private final DBSetup dbSetup;

    public HumanInSpaceMySqlRepository(HumanInSpaceEntityMapper mapper, LoggerService loggerService, DBSetup dbSetup) {
        this.mapper = mapper;
        this.loggerService = loggerService;
        this.dbSetup = dbSetup;
    }

    @Override
    public void savePeopleInSpace(List<HumanInSpaceEntity> peopleInSpace) {
        try (final var ps = getConnection().prepareStatement("INSERT INTO " + TABLE + " VALUES(?, ?, ?, ?)")) {
            getConnection().setAutoCommit(false);

            for (final HumanInSpaceEntity entity : peopleInSpace) {
                ps.setString(1, entity.getId().toString());
                ps.setString(2, entity.getName());
                ps.setString(3, entity.getCraft());
                ps.setLong(4, entity.getExpTime());
                ps.execute();
            }

            getConnection().commit();
        } catch (SQLException e) {
            try {
                getConnection().rollback();
            } catch (SQLException rollbackException) {
                loggerService.logError("savePeopleInSpace rollback failed!", rollbackException);
            }
            loggerService.logError("savePeopleInSpace with entities " + peopleInSpace + " failed!", e);
        } finally {
            try {
                getConnection().setAutoCommit(true);
            } catch (SQLException e) {
                loggerService.logError("Unable to set auto commit back to true!", e);
            }
        }
    }

    @Override
    public List<HumanInSpaceEntity> getPeopleInSpace() {
        final var peopleInSpace = new ArrayList<HumanInSpaceEntity>();
        try (final var ps = getConnection().prepareStatement("SELECT * FROM " + TABLE + " WHERE exp_time > ?")) {
            ps.setLong(1, Instant.now().getEpochSecond());
            final var resultSet = ps.executeQuery();
            while (resultSet.next()) {
                peopleInSpace.add(mapper.mapFromResultSet(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            loggerService.logError("getPeopleInSpace failed!", e);
        }
        return peopleInSpace;
    }

    private Connection getConnection() {
        return this.dbSetup.getDbConnection();
    }
}
