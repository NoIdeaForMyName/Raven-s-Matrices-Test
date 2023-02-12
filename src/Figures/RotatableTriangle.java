package Figures;

import Figures.Figure;

import javax.swing.*;
import java.awt.*;

public class RotatableTriangle extends JComponent implements Figure {
    private int pos_x;
    private int pos_y;
    private int dim_x;
    private int dim_y;
    private double rotationAngle;

    public RotatableTriangle() {
        pos_x = 0;
        pos_y = 0;
        dim_x = 0;
        dim_y = 0;
        rotationAngle = 0;
    }

    private Polygon triangle;
    public RotatableTriangle(int x, int y, int width, int height, String direction) {
        switch (direction) {
            case "up" -> {
                int[] xPointsUp = {x, x + width / 2, x - width / 2};
                int[] yPointsUp = {y, y - height, y - height};
                triangle = new Polygon(xPointsUp, yPointsUp, 3);
            }
            case "down" -> {
                int[] xPointsDown = {x, x - width / 2, x + width / 2};
                int[] yPointsDown = {y, y + height, y + height};
                triangle = new Polygon(xPointsDown, yPointsDown, 3);
            }
            case "left" -> {
                int[] xPointsLeft = {x, x - width, x - width};
                int[] yPointsLeft = {y, y + height / 2, y - height / 2};
                triangle = new Polygon(xPointsLeft, yPointsLeft, 3);
            }
            case "right" -> {
                int[] xPointsRight = {x, x + width, x + width};
                int[] yPointsRight = {y, y - height / 2, y + height / 2};
                triangle = new Polygon(xPointsRight, yPointsRight, 3);
            }
            default ->
                    throw new IllegalArgumentException("Invalid direction. Please use 'up', 'down', 'left' or 'right'.");
        }
    }
    public Polygon getTriangle() {
        return triangle;
    }

    public void setRotationAngle(double angle) {
        rotationAngle = angle;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(Math.toRadians(rotationAngle), pos_x + dim_x / 2, pos_y + dim_y / 2);
        g2d.fillPolygon(new int[]{pos_x, pos_x+(dim_x/2), pos_x+dim_x}, new int[]{pos_y+dim_y, pos_y, pos_y+dim_y}, 3);
    }

    public enum Dir{
        Left,
        Right,
        Up,
        Down
    }

    @Override
    public void drawComponent(Graphics g, int pos_x, int pos_y, int dim_x, int dim_y) {

        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.dim_x = dim_x+2;
        this.dim_y = dim_y+2;
        paintComponent(g);
    }

    @Override
    public void drawComponent(Graphics g, int pos_x, int pos_y, int dim_x, int dim_y, Color color) {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.dim_x = dim_x+2;
        this.dim_y = dim_y+2;

        Color previousColor = g.getColor();
        g.setColor(color);

        paintComponent(g);

        g.setColor(previousColor);
    }
}