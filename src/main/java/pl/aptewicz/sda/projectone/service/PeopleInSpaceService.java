package pl.aptewicz.sda.projectone.service;

import pl.aptewicz.sda.projectone.dto.PeopleInSpaceDto;
import pl.aptewicz.sda.projectone.entity.HumanInSpaceEntity;
import pl.aptewicz.sda.projectone.repository.HumanInSpaceRepository;
import pl.aptewicz.sda.projectone.service.http.OpenNotifyConnector;
import pl.aptewicz.sda.projectone.service.mapper.HumanInSpaceEntityMapper;

import java.util.List;
import java.util.stream.Collectors;

public class PeopleInSpaceService {

    private final OpenNotifyConnector openNotifyConnector;

    private final HumanInSpaceRepository humanInSpaceRepository;

    private final HumanInSpaceEntityMapper humanInSpaceEntityMapper;

    public PeopleInSpaceService(OpenNotifyConnector openNotifyConnector, HumanInSpaceRepository humanInSpaceRepository,
                                HumanInSpaceEntityMapper humanInSpaceEntityMapper) {
        this.openNotifyConnector = openNotifyConnector;
        this.humanInSpaceRepository = humanInSpaceRepository;
        this.humanInSpaceEntityMapper = humanInSpaceEntityMapper;
    }

    public PeopleInSpaceDto getPeopleInSpace() throws Exception {
        final var peopleInSpaceFromDb = this.humanInSpaceRepository.getPeopleInSpace();
        if (!peopleInSpaceFromDb.isEmpty()) {
            return new PeopleInSpaceDto(peopleInSpaceFromDb.size(),
                    peopleInSpaceFromDb.stream().map(humanInSpaceEntityMapper::mapToDto).collect(Collectors.toList()));
        }

        final var result = this.openNotifyConnector.getPeopleInSpace();
        result.ifPresent(dto -> {
            List<HumanInSpaceEntity> peopleInSpaceEntities =
                    dto.getPeople().stream().map(humanInSpaceEntityMapper::mapFromDto).collect(Collectors.toList());
            this.humanInSpaceRepository.savePeopleInSpace(peopleInSpaceEntities);
        });
        return result.orElseThrow(() -> new Exception("Unable to get info about people in space."));
    }
}
