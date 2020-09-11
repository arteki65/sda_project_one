package pl.aptewicz.sda.projectone.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PeopleInSpaceDto {
    private final int number;

    private final List<HumanInSpace> people;

    @JsonCreator
    public PeopleInSpaceDto(@JsonProperty("number") int number, @JsonProperty("people") List<HumanInSpace> people) {
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

        @JsonCreator
        public HumanInSpace(@JsonProperty("craft") String craft, @JsonProperty("name") String name) {
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
