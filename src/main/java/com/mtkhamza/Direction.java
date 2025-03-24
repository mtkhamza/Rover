package com.mtkhamza;

public enum Direction {
    NORTH('N'), EAST('E'), SOUTH('S'), WEST('W');

    private final char value;

    Direction(char c) {
        this.value = c;
    }

    public static Direction fromCode(char code) {
        for (Direction direction : Direction.values()) {
            if (direction.value == code) {
                return direction;
            }
        }
        throw new IllegalArgumentException("Invalid direction code: " + code);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }


}
