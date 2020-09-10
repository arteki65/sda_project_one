package pl.aptewicz.sda.projectone.entity;

public class HumanInSpaceEntity {
    private final String craft;

    private final String name;

    public HumanInSpaceEntity(String craft, String name) {
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
    public String toString() {
        return "HumanInSpaceEntity{" + "craft='" + craft + '\'' + ", name='" + name + '\'' + '}';
    }
}

