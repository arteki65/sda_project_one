package pl.aptewicz.sda.projectone.view;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PeopleInSpaceView {
    private final int number;

    private final List<HumanInSpaceView> people;

    public PeopleInSpaceView(int number, List<HumanInSpaceView> people) {
        this.number = number;
        this.people = people;
    }

    public String getInfoAboutPeopleInSpace() {
        return String.format("Currently there are %d people in space:\n%s", this.number, this.people.stream()
                .map(humanInSpace -> humanInSpace.getName() + " on craft " + humanInSpace.getCraft() + "\n")
                .collect(Collectors.joining()));
    }

    public static class HumanInSpaceView {
        private final String craft;

        private final String name;

        public HumanInSpaceView(String craft, String name) {
            this.craft = craft;
            this.name = name;
        }

        public String getCraft() {
            return craft;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof HumanInSpaceView))
                return false;
            HumanInSpaceView that = (HumanInSpaceView) o;
            return Objects.equals(craft, that.craft) && Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(craft, name);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof PeopleInSpaceView))
            return false;
        PeopleInSpaceView that = (PeopleInSpaceView) o;
        return number == that.number && Objects.equals(people, that.people);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, people);
    }
}
