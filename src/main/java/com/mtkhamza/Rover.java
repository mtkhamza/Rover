package com.mtkhamza;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Rover {
    private int x;
    private int y;
    private Direction direction;

    public void processCommand(String command, int xPlateau, int yPlateau){
        for (char instruction : command.toCharArray()) {
            if (instruction == 'L') {
                turnLeft();
            } else if (instruction == 'R') {
                turnRight();
            } else if (instruction == 'M') {
                move(xPlateau, yPlateau);
            } else {
                throw new IllegalArgumentException("Invalid command: " + direction);
            }
        }
    }

    public void turnLeft() {
        switch (direction) {
            case NORTH:
                direction = Direction.WEST;
                break;
            case EAST:
                direction = Direction.NORTH;
                break;
            case SOUTH:
                direction = Direction.EAST;
                break;
            case WEST:
                direction = Direction.SOUTH;
                break;
        }
    }

    public void turnRight() {
        switch (direction) {
            case NORTH:
                direction = Direction.EAST;
                break;
            case EAST:
                direction = Direction.SOUTH;
                break;
            case SOUTH:
                direction = Direction.WEST;
                break;
            case WEST:
                direction = Direction.NORTH;
                break;
        }
    }

    public void move(int xPlateau, int yPlateau) {
        switch (direction) {
            case NORTH:
                y++;
                break;
            case EAST:
                x++;
                break;
            case SOUTH:
                y--;
                break;
            case WEST:
                x--;
                break;
        }
        checkIfRoverIsOutside(xPlateau, yPlateau);
    }

    public void checkIfRoverIsOutside(int xPlateau, int yPlateau) {
        if((x < 0) || (y < 0) || (x > xPlateau) || (y > yPlateau)){
            throw new IllegalArgumentException("Current Rover position has gone outside the plateau");
        }
    }

    @Override
    public String toString() {
        return x + " " + y + " " + direction ;
    }

}
