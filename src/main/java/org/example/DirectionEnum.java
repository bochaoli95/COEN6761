package org.example;

public enum DirectionEnum {
    NORTH("north"),
    EAST("east"),
    SOUTH("south"),
    WEST("west");

    private final String description;

    DirectionEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public DirectionEnum turnRight() {
        return values()[(this.ordinal() + 1) % 4];
    }

    public DirectionEnum turnLeft() {
        return values()[(this.ordinal() + 3) % 4];
    }
}
