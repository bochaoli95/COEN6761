package org.example;

import javax.swing.*;
import java.awt.*;

public class RobotUI extends JPanel {
    private final Robot robot;
    private final int cellSize = 50;
    private final int gridSize;

    public RobotUI(Robot robot, int gridSize) {
        this.robot = robot;
        this.gridSize = gridSize;
        JFrame frame = new JFrame("Robot Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(gridSize * cellSize + 50, gridSize * cellSize + 80);
        frame.add(this);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, gridSize * cellSize, gridSize * cellSize);
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                g.setColor(Color.LIGHT_GRAY);
                g.drawRect(j * cellSize, (gridSize - 1 - i) * cellSize, cellSize, cellSize);

                if (robot.getMap()[i][j] == 1) {
                    g.setColor(Color.BLACK);
                    g.fillRect(j * cellSize + 10, (gridSize - 1 - i) * cellSize + 10, cellSize - 20, cellSize - 20);
                }
            }
        }
        int robotX = robot.getX() * cellSize;
        int robotY = (gridSize - 1 - robot.getY()) * cellSize;
        g.setColor(Color.RED);
        g.fillOval(robotX + 5, robotY + 5, cellSize - 10, cellSize - 10);
        drawArrow(g, robotX + cellSize / 2, robotY + cellSize / 2, robot.getDirection());
    }

    private void drawArrow(Graphics g, int x, int y, DirectionEnum direction) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.ORANGE);
        g2.setStroke(new BasicStroke(3));
        int arrowSize = 10;
        int[] xPoints = new int[3];
        int[] yPoints = new int[3];

        switch (direction) {
            case NORTH:
                xPoints = new int[]{x, x - arrowSize, x + arrowSize};
                yPoints = new int[]{y - arrowSize, y + arrowSize, y + arrowSize};
                break;
            case EAST:
                xPoints = new int[]{x + arrowSize, x - arrowSize, x - arrowSize};
                yPoints = new int[]{y, y - arrowSize, y + arrowSize};
                break;
            case SOUTH:
                xPoints = new int[]{x, x - arrowSize, x + arrowSize};
                yPoints = new int[]{y + arrowSize, y - arrowSize, y - arrowSize};
                break;
            case WEST:
                xPoints = new int[]{x - arrowSize, x + arrowSize, x + arrowSize};
                yPoints = new int[]{y, y - arrowSize, y + arrowSize};
                break;
        }

        g2.fillPolygon(xPoints, yPoints, 3);
    }

    public void updateUI() {
        repaint();
    }
}
