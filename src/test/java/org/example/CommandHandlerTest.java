package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.stefanbirkner.systemlambda.SystemLambda.catchSystemExit;
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

    // TC-1-1: Initialize system with size 10x10
    @Test
    void testInitializeRobotWithSize10() {
        commandHandler.handleCommand("I 10");
        Robot robot = commandHandler.getRobot();

        assertNotNull(robot, "Robot should be initialized.");
        assertEquals(10, robot.getSize(), "Grid size should be 10.");
        assertEquals(0, robot.getX(), "Robot's initial X position should be 0.");
        assertEquals(0, robot.getY(), "Robot's initial Y position should be 0.");
        assertEquals(DirectionEnum.NORTH, robot.getDirection(), "Robot should initially face North.");
        assertFalse(robot.isPenDown(), "Pen should be up initially.");
    }

    // TC-1-2: Initialize system with size 10x10
    @Test
    void testInitializeRobotWithSize4() {
        commandHandler.handleCommand("I 4");
        Robot robot = commandHandler.getRobot();

        assertNotNull(robot, "Robot should be initialized.");
        assertEquals(4, robot.getSize(), "Grid size should be 10.");
        assertEquals(0, robot.getX(), "Robot's initial X position should be 0.");
        assertEquals(0, robot.getY(), "Robot's initial Y position should be 0.");
        assertEquals(DirectionEnum.NORTH, robot.getDirection(), "Robot should initially face North.");
        assertFalse(robot.isPenDown(), "Pen should be up initially.");
    }
    // TC-2-1: Print initial position
    @Test
    void testPrintInitialPosition() {
        commandHandler.handleCommand("I 10");
        commandHandler.handleCommand("C");
        System.out.flush();
        String actualOutput = outputStreamCaptor.toString().trim();
        String expectedOutput = "Position: 0, 0 - Pen: up - Facing: north";
        assertEquals(expectedOutput, actualOutput, "The printed status should match the expected format.");
    }
    // TC-2-2: Move robot and check position
    @Test
    void testMoveRobotAndCheckPosition() {
        commandHandler.handleCommand("I 5");
        commandHandler.handleCommand("M 3");
        commandHandler.handleCommand("C");

        System.out.flush();
        String actualOutput = outputStreamCaptor.toString().trim();
        String expectedOutput = "Position: 0, 3 - Pen: up - Facing: north";

        System.out.println("Actual Output: " + actualOutput);
        System.out.println("Expected Output: " + expectedOutput);
        assertEquals(expectedOutput, actualOutput, "The printed status should match the expected position and state.");
    }

    // TC-2-3: Move robot, lower pen, and check position
    @Test
    void testMoveRobotWithPenDownAndCheckPosition() {
        commandHandler.handleCommand("I 5");
        commandHandler.handleCommand("M 3");
        commandHandler.handleCommand("D");
        commandHandler.handleCommand("C");

        System.out.flush();
        String actualOutput = outputStreamCaptor.toString().trim();
        String expectedOutput = "Position: 0, 3 - Pen: down - Facing: north";

        System.out.println("Actual Output: " + actualOutput);
        System.out.println("Expected Output: " + expectedOutput);
        assertEquals(expectedOutput, actualOutput, "The printed status should match the expected position and state.");
    }
    // TC-3-1: Turn robot right
    @Test
    void testTurnRobotRight() {
        commandHandler.handleCommand("I 10");
        commandHandler.handleCommand("R");
        commandHandler.handleCommand("C");

        System.out.flush();
        String actualOutput = outputStreamCaptor.toString().trim();
        String expectedOutput = "Position: 0, 0 - Pen: up - Facing: east";

        System.out.println("Actual Output: " + actualOutput);
        System.out.println("Expected Output: " + expectedOutput);
        assertEquals(expectedOutput, actualOutput, "The printed status should match the expected position and state.");
    }

    @Test
    void testTurnRobotRight1() {
        commandHandler.handleCommand("I 10");
        commandHandler.handleCommand("R");
        commandHandler.handleCommand("R");
        commandHandler.handleCommand("C");

        System.out.flush();
        String actualOutput = outputStreamCaptor.toString().trim();
        String expectedOutput = "Position: 0, 0 - Pen: up - Facing: south";

        System.out.println("Actual Output: " + actualOutput);
        System.out.println("Expected Output: " + expectedOutput);
        assertEquals(expectedOutput, actualOutput, "The printed status should match the expected position and state.");
    }
    // TC-3-2: Move forward and turn left
    @Test
    void testMoveForwardAndTurnLeft() {
        commandHandler.handleCommand("I 10");
        commandHandler.handleCommand("M 3");
        commandHandler.handleCommand("L");
        commandHandler.handleCommand("C");

        System.out.flush();
        String actualOutput = outputStreamCaptor.toString().trim();
        String expectedOutput = "Position: 0, 3 - Pen: up - Facing: west";

        System.out.println("Actual Output: " + actualOutput);
        System.out.println("Expected Output: " + expectedOutput);
        assertEquals(expectedOutput, actualOutput, "The printed status should match the expected position and state.");
    }

    // TC-3-3: Moving forward at boundary positions
    @Test
    void testMoveForwardAtBoundary() {
        commandHandler.handleCommand("I 5");
        commandHandler.handleCommand("M 6");  // Attempting to move beyond the boundary
        commandHandler.handleCommand("C");

        System.out.flush();
        String actualOutput = outputStreamCaptor.toString().trim();
        String expectedOutput = "Position: 0, 4 - Pen: up - Facing: north"; // Should stop at the boundary

        System.out.println("Actual Output: " + actualOutput);
        System.out.println("Expected Output: " + expectedOutput);
        assertEquals(expectedOutput, actualOutput, "The printed status should match the expected boundary position.");
    }



    // TC-4-1: Lower the pen
    @Test
    void testLowerPen() {
        commandHandler.handleCommand("I 5");
        commandHandler.handleCommand("D");
        commandHandler.handleCommand("C");

        System.out.flush();
        String actualOutput = outputStreamCaptor.toString().trim();
        String expectedOutput = "Position: 0, 0 - Pen: down - Facing: north";

        System.out.println("Actual Output: " + actualOutput);
        System.out.println("Expected Output: " + expectedOutput);
        assertEquals(expectedOutput, actualOutput, "The printed status should match the expected pen state.");
    }
    // TC-4-2: Raise the pen
    @Test
    void testRaisePen() {
        commandHandler.handleCommand("I 5");
        commandHandler.handleCommand("U");
        commandHandler.handleCommand("C");

        System.out.flush();
        String actualOutput = outputStreamCaptor.toString().trim();
        String expectedOutput = "Position: 0, 0 - Pen: up - Facing: north";

        System.out.println("Actual Output: " + actualOutput);
        System.out.println("Expected Output: " + expectedOutput);
        assertEquals(expectedOutput, actualOutput, "The printed status should match the expected pen state.");
    }

    // TC-5-1: Keep the Pen Down state, move forward three steps, turn right, then move forward three steps, and print the map.
    @Test
    void testDrawPathWithPenDown() {
        commandHandler.handleCommand("I 5");
        commandHandler.handleCommand("D");
        commandHandler.handleCommand("M 3");
        commandHandler.handleCommand("R");
        commandHandler.handleCommand("M 3");
        commandHandler.handleCommand("P");
        System.out.flush();
        String output = outputStreamCaptor.toString();
        String expectedMap =
                "     " + "\n" +
                        "**** " + "\n" +
                        "*    " + "\n" +
                        "*    " + "\n" +
                        "*    " + "\n";
        expectedMap = expectedMap.replace("\n", System.lineSeparator());
        assertEquals(expectedMap, output, "The printed map should match the expected layout.");
    }

    // TC-5-2: Keep the Pen Up state, move forward three steps, turn right, then move forward three steps, and print the map.
    @Test
    void testMoveWithoutDrawing() {
        commandHandler.handleCommand("I 5");
        commandHandler.handleCommand("U");
        commandHandler.handleCommand("M 3");
        commandHandler.handleCommand("R");
        commandHandler.handleCommand("M 3");
        commandHandler.handleCommand("P");

        System.out.flush();
        String output = outputStreamCaptor.toString();
        String expectedMap =
                "     " + "\n" +
                        "     " + "\n"+
                        "     " + "\n" +
                        "     " + "\n"+
                        "     " + "\n";

        expectedMap = expectedMap.replace("\n", System.lineSeparator());
        assertEquals(expectedMap, output, "The printed map should be empty since the pen was up.");
    }

    // TC-5-3: Keep the Pen Down state and move for a certain distance, then switch to the Pen Up state and move for another distance.
    @Test
    void testDrawPartialPathWithPenUp() {
        commandHandler.handleCommand("I 5");
        commandHandler.handleCommand("D");
        commandHandler.handleCommand("M 3");
        commandHandler.handleCommand("R");
        commandHandler.handleCommand("U");
        commandHandler.handleCommand("M 3");
        commandHandler.handleCommand("P");

        System.out.flush();
        String output = outputStreamCaptor.toString();
        String expectedMap =
                "     " + "\n" +
                        "*    " + "\n" +
                        "*    " + "\n" +
                        "*    " + "\n" +
                        "*    " + "\n";

        expectedMap = expectedMap.replace("\n", System.lineSeparator());

        assertEquals(expectedMap, output, "The printed map should only contain the first drawn path before the pen was raised.");
    }

    // TC-6-1: Move forward, turn right, move forward again, then replay the commands.
    @Test
    void testReplayCommandsCheckFinalPosition() {
        commandHandler.handleCommand("I 5");
        commandHandler.handleCommand("M 3");
        commandHandler.handleCommand("R");
        commandHandler.handleCommand("M 3");
        commandHandler.handleCommand("H");  // Replay the commands

        Robot robot = commandHandler.getRobot();

        assertNotNull(robot, "Robot should be initialized.");
        assertEquals(3, robot.getX(), "Robot's final X position should be 3.");
        assertEquals(3, robot.getY(), "Robot's final Y position should be 3.");
        assertEquals(DirectionEnum.EAST, robot.getDirection(), "Robot should be facing EAST after replaying commands.");
    }


    @Test
    void testInvalidCommand() {
        commandHandler.handleCommand("INVALID");
        String output = outputStreamCaptor.toString().trim();
        System.out.flush();
        assertTrue(output.contains("Invalid command."), "Output should contain 'Invalid command.'");
    }
    @Test
    void testQuitCommand() throws Exception {
        int statusCode = catchSystemExit(() -> {
            commandHandler.handleCommand("Q");
        });
        assert (statusCode == 0);
    }
}
