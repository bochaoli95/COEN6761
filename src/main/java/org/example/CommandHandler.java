package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandHandler {
    private Robot robot;
    private List<String> commandsHistory;

    public CommandHandler() {
        commandsHistory = new ArrayList<>();
    }

//    public CommandHandler(Integer size) {
//        this.robot = new Robot(size);
//        commandsHistory = new ArrayList<>();
//    }


    public Robot getRobot() {
        return robot;
    }

//    public void setRobot(Robot robot) {
//        this.robot = robot;
//    }

    public void handleCommand(String command) {
        commandsHistory.add(command);
        executeCommand(command);
        robot.updateUI();
    }

    public void executeCommand(String command) {
        String SPACE = " ";
        String commandAction = command.split(SPACE)[0].toUpperCase();
        switch (commandAction) {
            case "I":
                robot = new Robot(Integer.parseInt(command.split(SPACE)[1]));
                break;
            case "D":
                robot.downPen();
                break;
            case "U":
                robot.upPen();
                break;
            case "M":
                int steps = Integer.parseInt(command.split(SPACE)[1]);
                robot.move(steps);
                break;
            case "L":
                robot.turnLeft();
                break;
            case "R":
                robot.turnRight();
                break;
            case "C":
                robot.showStatus();
                break;
            case "P":
                robot.printMap();
                break;
            case "H":
                commandsHistory.remove(commandsHistory.size() - 1);
                replayHistory();
                break;
            case "Q":
                System.out.println("Exiting program.");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid command.");
                break;
        }
    }

    private void replayHistory() {
        System.out.println("Replaying history...");
        for (String cmd : commandsHistory) {
            System.out.println("> " + cmd);
            executeCommand(cmd);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandHandler processor = new CommandHandler();
        while (true) {
            System.out.print("> Enter command: ");
            String command = scanner.nextLine().trim();
            processor.handleCommand(command);

        }
    }

}
