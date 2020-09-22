package pl.aptewicz.sda.projectone.repository;

import pl.aptewicz.sda.projectone.entity.IssPositionEntity;

import java.util.List;

// TODO: create class which implement this interface
public interface IssPositionRepository {

    void saveIssPosition(IssPositionEntity entity);

    List<IssPositionEntity> getIssPosition();

}
