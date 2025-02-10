package org.example;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Robot {
    private int[][] map;
    private int size;
    private boolean penDown;
    private DirectionEnum direction;
    private int x, y;
    private RobotUI ui;


    public Robot(int size) {
        this.size = size;
        x = 0;
        y = 0;
        direction = DirectionEnum.NORTH;
        penDown = false;
        map = new int[size][size];
        ui = new RobotUI(this, size);
    }

    public boolean isPenDown(){
         return penDown;
    }

    public int[][] getMap() {
        return map;
    }

    public int getX() {
        return x;
    }

    public DirectionEnum getDirection() {
        return direction;
    }

    public int getY() {
        return y;
    }

    public void downPen() {
        penDown = true;
    }

    public void upPen() {
        penDown = false;
    }

    public void turnRight() {
        direction = direction.turnRight();
    }

    public void turnLeft() {
        direction = direction.turnLeft();
    }

    public void showStatus() {
        String statusResult = "Position: " + x + ", " + y + " - Pen: " + (penDown ? "down" : "up")
                + " - Facing: " + direction.getDescription();
        System.out.println(statusResult);
    }

    public void printMap() {
        IntStream.range(0, size)
                .mapToObj(y -> IntStream.range(0, size)
                        .mapToObj(x -> map[size - 1 - y][x] == 1 ? "*" : " ")
                        .collect(Collectors.joining("")))
                .forEach(System.out::println);
    }


    public void move(int steps) {
        if (penDown) map[y][x] = 1;
        IntStream.range(0, steps).forEach(i -> {
            switch (direction) {
                case NORTH:
                    if (y + 1 < size) y++;
                    break;
                case EAST:
                    if (x + 1 < size) x++;
                    break;
                case SOUTH:
                    if (y - 1 >= 0) y--;
                    break;
                case WEST:
                    if (x - 1 >= 0) x--;
                    break;
            }
            if (penDown) {
                map[y][x] = 1;
            }
        });
    }
    public void updateUI() { ui.updateUI(); }



}