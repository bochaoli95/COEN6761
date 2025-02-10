package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RobotTest {
    private Robot robot;
    private final int SIZE = 10;

    @BeforeEach
    public void setUp() {
        robot = new Robot(SIZE);
    }

    @Test
    public void testInitialPosition() {
        assertEquals(0, robot.getX());
        assertEquals(0, robot.getY());
        assertEquals(DirectionEnum.NORTH, robot.getDirection());
        assertFalse(robot.isPenDown());
    }

    @Test
    public void testPenUpAndDown() {
        robot.downPen();
        assertTrue(robot.isPenDown());

        robot.upPen();
        assertFalse(robot.isPenDown());
    }

    @Test
    public void testTurnRight() {
        robot.turnRight();
        assertEquals(DirectionEnum.EAST, robot.getDirection());

        robot.turnRight();
        assertEquals(DirectionEnum.SOUTH, robot.getDirection());

        robot.turnRight();
        assertEquals(DirectionEnum.WEST, robot.getDirection());

        robot.turnRight();
        assertEquals(DirectionEnum.NORTH, robot.getDirection());
    }

    @Test
    public void testTurnLeft() {
        robot.turnLeft();
        assertEquals(DirectionEnum.WEST, robot.getDirection());

        robot.turnLeft();
        assertEquals(DirectionEnum.SOUTH, robot.getDirection());

        robot.turnLeft();
        assertEquals(DirectionEnum.EAST, robot.getDirection());

        robot.turnLeft();
        assertEquals(DirectionEnum.NORTH, robot.getDirection());
    }

    @Test
    public void testMoveWithoutPen() {
        robot.move(2);
        assertEquals(0, robot.getX());
        assertEquals(2, robot.getY());

        int[][] map = robot.getMap();
        for (int i = 0; i <= 2; i++) {
            assertEquals(0, map[i][0]);
        }
    }

    @Test
    public void testMoveWithPen() {
        robot.downPen();
        robot.move(3);

        int[][] map = robot.getMap();
        assertEquals(1, map[0][0]);
        assertEquals(1, map[1][0]);
        assertEquals(1, map[2][0]);
        assertEquals(1, map[3][0]);
    }

    @Test
    public void testBoundaryMove() {
        robot.move(SIZE);
        assertEquals(0, robot.getX());
        assertEquals(SIZE - 1, robot.getY());
    }

    @Test
    public void testMoveBeyondBoundary() {
        robot.move(SIZE + 5);
        assertEquals(0, robot.getX());
        assertEquals(SIZE - 1, robot.getY());
    }

    @Test
    public void testMoveEastWithinBounds() {
        robot.turnRight();
        robot.move(SIZE - 2);
        assertEquals(SIZE - 2, robot.getX());
        assertEquals(0, robot.getY());
    }

    @Test
    public void testMoveSouthBeyondBounds() {
        robot.turnRight();
        robot.turnRight();
        robot.move(SIZE + 3);
        assertEquals(0, robot.getX());
        assertEquals(0, robot.getY());
    }

    @Test
    public void testMoveWestAndStopAtBoundary() {
        robot.turnRight();
        robot.move(SIZE - 1);
        robot.turnRight();
        robot.turnRight();
        robot.move(SIZE + 2);
        assertEquals(0, robot.getX());
        assertEquals(0, robot.getY());
    }

    @Test
    public void testMoveNorthBeyondBoundary() {
        robot.move(SIZE + 5);
        assertEquals(0, robot.getX());
        assertEquals(SIZE - 1, robot.getY());
    }

    @Test
    public void testMoveWestWithPen() {
        robot.downPen();
        robot.turnRight();
        robot.move(SIZE - 1);
        robot.turnRight();
        robot.turnRight();
        robot.move(SIZE - 1);
        int[][] map = robot.getMap();
        assertEquals(1, map[0][0]);
        assertEquals(1, map[0][SIZE - 1]);
    }
}
