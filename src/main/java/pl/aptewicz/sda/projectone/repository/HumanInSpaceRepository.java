package pl.aptewicz.sda.projectone.repository;

import pl.aptewicz.sda.projectone.entity.HumanInSpaceEntity;

import java.util.List;

public interface HumanInSpaceRepository {

    void savePeopleInSpace(List<HumanInSpaceEntity> peopleInSpace);

    List<HumanInSpaceEntity> getPeopleInSpace();
}
