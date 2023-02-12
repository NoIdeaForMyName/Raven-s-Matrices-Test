package Figures;

import javax.swing.*;
import java.awt.*;

public class Rectangle extends JComponent implements Figure {

    private int pos_x;
    private int pos_y;
    private int dim_x;
    private int dim_y;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    Color color = Color.BLACK;

    public Rectangle() {
        pos_x = 0;
        pos_y = 0;
        dim_x = 0;
        dim_y = 0;
    }

    public Rectangle(int x, int y, int dim_x, int dim_y) {
        pos_x = x;
        pos_y = y;
        this.dim_x = dim_x;
        this.dim_y = dim_y;
    }

    public Rectangle(int x, int y, int dim_x, int dim_y, Color color) {
        pos_x = x;
        pos_y = y;
        this.dim_x = dim_x;
        this.dim_y = dim_y;
        this.color = color;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(pos_x, pos_y, dim_x, dim_y);
    }

    @Override
    public void drawComponent(Graphics g, int pos_x, int pos_y, int dim_x, int dim_y) {

        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.dim_x = dim_x;
        this.dim_y = dim_y;

        paintComponent(g);
    }

    public void drawComponent(Graphics g, int pos_x, int pos_y, int dim_x, int dim_y, Color color) {

        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.dim_x = dim_x;
        this.dim_y = dim_y;

        Color previousColor = g.getColor();
        g.setColor(color);

        paintComponent(g);

        g.setColor(previousColor);
    }
}
