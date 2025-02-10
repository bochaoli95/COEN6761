package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DirectionEnumTest {

    @Test
    void testGetDescription() {
        assertEquals("north", DirectionEnum.NORTH.getDescription());
        assertEquals("east", DirectionEnum.EAST.getDescription());
        assertEquals("south", DirectionEnum.SOUTH.getDescription());
        assertEquals("west", DirectionEnum.WEST.getDescription());
    }

    @Test
    void testTurnRight() {
        assertEquals(DirectionEnum.EAST, DirectionEnum.NORTH.turnRight());
        assertEquals(DirectionEnum.SOUTH, DirectionEnum.EAST.turnRight());
        assertEquals(DirectionEnum.WEST, DirectionEnum.SOUTH.turnRight());
        assertEquals(DirectionEnum.NORTH, DirectionEnum.WEST.turnRight());
    }

    @Test
    void testTurnLeft() {
        assertEquals(DirectionEnum.WEST, DirectionEnum.NORTH.turnLeft());
        assertEquals(DirectionEnum.SOUTH, DirectionEnum.WEST.turnLeft());
        assertEquals(DirectionEnum.EAST, DirectionEnum.SOUTH.turnLeft());
        assertEquals(DirectionEnum.NORTH, DirectionEnum.EAST.turnLeft());
    }
}
