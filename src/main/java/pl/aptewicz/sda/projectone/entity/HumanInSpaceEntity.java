package pl.aptewicz.sda.projectone.entity;

import java.util.UUID;

public class HumanInSpaceEntity {
    private final UUID id;

    private final String craft;

    private final String name;

    private final long expTime;

    public HumanInSpaceEntity(UUID id, String craft, String name, long expTime) {
        this.id = id;
        this.craft = craft;
        this.name = name;
        this.expTime = expTime;
    }

    public String getCraft() {
        return craft;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public long getExpTime() {
        return expTime;
    }

    @Override
    public String toString() {
        return "HumanInSpaceEntity{" + "craft='" + craft + '\'' + ", name='" + name + '\'' + '}';
    }
}

