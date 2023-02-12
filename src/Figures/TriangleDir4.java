package Figures;

import java.awt.Polygon;

public class TriangleDir4 {
    public enum Direction {UP, DOWN, LEFT, RIGHT};
    public static Polygon createTriangle(int x, int y, int width, int height, Direction direction) {
        switch(direction) {
            case UP:
                int[] xPointsUp = {x-width/2, x, x+width/2};
                int[] yPointsUp = {y, y-height, y};
                return new Polygon(xPointsUp, yPointsUp, 3);
            case DOWN:
                int[] xPointsDown = {x-width/2, x, x+width/2};
                int[] yPointsDown = {y, y+height, y};
                return new Polygon(xPointsDown, yPointsDown, 3);
            case LEFT:
                int[] xPointsRight = {x, x, x-width};
                int[] yPointsRight = {y-height/2, y+height/2, y};
                return new Polygon(xPointsRight, yPointsRight, 3);
            case RIGHT:
                int[] xPointsLeft = {x, x, x + width};
                int[] yPointsLeft = {y-height/2, y+height/2, y};
                return new Polygon(xPointsLeft, yPointsLeft, 3);
            default:
                return null;
        }
    }
}