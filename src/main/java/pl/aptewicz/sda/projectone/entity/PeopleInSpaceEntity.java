package pl.aptewicz.sda.projectone.entity;

import java.util.List;

public class PeopleInSpaceEntity {
    private final long id;

    private final int number;

    private final List<HumanInSpaceEntity> people;

    public PeopleInSpaceEntity(long id, int number, List<HumanInSpaceEntity> people) {
        this.id = id;
        this.number = number;
        this.people = people;
    }

    public int getNumber() {
        return number;
    }

    public List<HumanInSpaceEntity> getPeople() {
        return people;
    }

    public long getId() {
        return id;
    }

    public static class HumanInSpaceEntity {
        private final String craft;

        private final String name;

        HumanInSpaceEntity(String craft, String name) {
            this.craft = craft;
            this.name = name;
        }

        public String getCraft() {
            return craft;
        }

        public String getName() {
            return name;
        }
    }
}
