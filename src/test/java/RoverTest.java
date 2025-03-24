import com.mtkhamza.Direction;
import com.mtkhamza.Rover;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoverTest {

    private Rover rover;

    @BeforeEach
    void setUp() {
        rover = new Rover(1, 3, Direction.NORTH);
    }

    @Test
    @DisplayName("Test initial position and direction")
    void testInitialState() {
        assertEquals(1, rover.getX());
        assertEquals(3, rover.getY());
        assertEquals(Direction.NORTH, rover.getDirection());
    }

    @Test
    @DisplayName("Test turning left from NORTH")
    void testTurnLeftFromNorth() {
        rover.turnLeft();
        assertEquals(Direction.WEST, rover.getDirection());
    }

    @Test
    @DisplayName("Test turning left from WEST")
    void testTurnLeftFromWest() {
        rover.setDirection(Direction.WEST);
        rover.turnLeft();
        assertEquals(Direction.SOUTH, rover.getDirection());
    }

    @Test
    @DisplayName("Test turning right from NORTH")
    void testTurnRightFromNorth() {
        rover.turnRight();
        assertEquals(Direction.EAST, rover.getDirection());
    }

    @Test
    @DisplayName("Test turning right from EAST")
    void testTurnRightFromEast() {
        rover.setDirection(Direction.EAST);
        rover.turnRight();
        assertEquals(Direction.SOUTH, rover.getDirection());
    }

    @Test
    @DisplayName("Test moving NORTH")
    void testMoveNorth() {
        rover.move(5, 5);
        assertEquals(1, rover.getX());
        assertEquals(4, rover.getY());
    }

    @Test
    @DisplayName("Test moving EAST")
    void testMoveEast() {
        rover.setDirection(Direction.EAST);
        rover.move(5, 5);
        assertEquals(2, rover.getX());
        assertEquals(3, rover.getY());
    }

    @Test
    @DisplayName("Test moving SOUTH")
    void testMoveSouth() {
        rover.setDirection(Direction.SOUTH);
        rover.move(5, 5);
        assertEquals(1, rover.getX());
        assertEquals(2, rover.getY());
    }

    @Test
    @DisplayName("Test moving WEST")
    void testMoveWest() {
        rover.setDirection(Direction.WEST);
        rover.move(5, 5);
        assertEquals(0, rover.getX());
        assertEquals(3, rover.getY());
    }

    @Test
    @DisplayName("Test moving outside plateau boundaries")
    void testMoveOutsidePlateau() {
        rover.setDirection(Direction.SOUTH);
        assertThrows(IllegalArgumentException.class, () -> rover.move(1, 1));
    }

    @Test
    @DisplayName("Test processing valid command string")
    void testProcessValidCommand() {
        rover.processCommand("LMLMLMLMM", 5, 5);
        assertEquals(1, rover.getX());
        assertEquals(4, rover.getY());
        assertEquals(Direction.NORTH, rover.getDirection());
    }

    @Test
    @DisplayName("Test processing invalid command string")
    void testProcessInvalidCommand() {
        assertThrows(IllegalArgumentException.class, () -> rover.processCommand("LMXA", 5, 5));
    }

    @Test
    @DisplayName("Test toString method")
    void testToString() {
        assertEquals("1 3 N", rover.toString());
        rover.setX(3);
        rover.setY(4);
        rover.setDirection(Direction.EAST);
        assertEquals("3 4 E", rover.toString());
    }

    @Test
    @DisplayName("Test complete rover movement sequence")
    void testCompleteMovementSequence() {
        rover.processCommand("MMRMM", 5, 5);
        assertEquals(3, rover.getX());
        assertEquals(5, rover.getY());
        assertEquals(Direction.EAST, rover.getDirection());
    }

    @Test
    @DisplayName("Test rover doesn't move outside plateau after valid command")
    void testValidMovementWithinPlateau() {
        rover.processCommand("MMM", 6, 6);
        assertEquals(1, rover.getX());
        assertEquals(6, rover.getY());
    }
}