package pl.aptewicz.sda.projectone.dto;

import java.util.List;

public class PeopleInSpaceDto {
    private final int number;

    private final List<HumanInSpace> people;

    public PeopleInSpaceDto(int number, List<HumanInSpace> people) {
        this.number = number;
        this.people = people;
    }

    public int getNumber() {
        return number;
    }

    public List<HumanInSpace> getPeople() {
        return people;
    }

    public static class HumanInSpace {
        private final String craft;

        private final String name;

        HumanInSpace(String craft, String name) {
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
