package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class CommandHandlerTest {
    private CommandHandler commandHandler;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        commandHandler = new CommandHandler();
        System.setOut(new PrintStream(outputStreamCaptor));
        commandHandler.handleCommand("I 10");
    }

    @Test
    void testInitializeRobot() {
        assertNotNull(commandHandler, "CommandHandler should be initialized.");
    }

    @Test
    void testPenDown() {
        commandHandler.handleCommand("D");
        assertTrue(commandHandler.getRobot().isPenDown(), "Pen should be down after 'D'.");
    }

    @Test
    void testPenUp() {
        commandHandler.handleCommand("D");
        commandHandler.handleCommand("U");
        assertFalse(commandHandler.getRobot().isPenDown(), "Pen should be up after 'U'.");
    }

    @Test
    void testMoveForward() {
        commandHandler.handleCommand("M 3");
        Robot robot = commandHandler.getRobot();
        assertEquals(3, robot.getY(), "Robot should move 3 steps north.");
    }

    @Test
    void testTurnLeft() {
        commandHandler.handleCommand("L");
        assertEquals(DirectionEnum.WEST, commandHandler.getRobot().getDirection(), "Robot should face WEST after 'L'.");
    }

    @Test
    void testTurnRight() {
        commandHandler.handleCommand("R");
        assertEquals(DirectionEnum.EAST, commandHandler.getRobot().getDirection(), "Robot should face EAST after 'R'.");
    }

    @Test
    void testPrintStatus() {
        commandHandler.handleCommand("D");
        commandHandler.handleCommand("M 3");
        commandHandler.handleCommand("R");
        commandHandler.handleCommand("C");

        System.out.flush();
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("Position: 0, 3"), "Output should contain correct position (0, 3).");
        assertTrue(output.contains("Pen: down"), "Output should indicate pen is down.");
        assertTrue(output.contains("Facing: east"), "Output should indicate direction is EAST.");
    }


    @Test
    void testPrintMap() {
        commandHandler.handleCommand("D");
        commandHandler.handleCommand("M 4");
        commandHandler.handleCommand("R");
        commandHandler.handleCommand("M 3");
        commandHandler.handleCommand("P");
        System.out.flush();
        String output = outputStreamCaptor.toString();
        String expectedMap =
                "          " + "\n" +
                        "          " + "\n" +
                        "          " + "\n" +
                        "          " + "\n" +
                        "          " + "\n" +
                        "****      " + "\n" +
                        "*         " + "\n" +
                        "*         " + "\n" +
                        "*         " + "\n" +
                        "*         " + "\n";
        expectedMap = expectedMap.replace("\n", System.lineSeparator());
        assertEquals(expectedMap, output, "The printed map should match the expected layout.");
    }


    @Test
    void testReplayHistory() {
        commandHandler.handleCommand("D");
        commandHandler.handleCommand("M 3");
        commandHandler.handleCommand("H");
        Robot robot = commandHandler.getRobot();
        assertEquals(3, robot.getY(), "Replay should move the robot back to position 4.");
    }

    @Test
    void testInvalidCommand() {
        commandHandler.handleCommand("INVALID");
        String output = outputStreamCaptor.toString().trim();
        System.out.flush();
        assertTrue(output.contains("Invalid command."), "Output should contain 'Invalid command.'");
    }
}
