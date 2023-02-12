package Figures;

import javax.swing.*;
import java.awt.*;

public class Triangle extends JComponent implements Figure {

    private int pos_x;
    private int pos_y;
    private int dim_x;
    private int dim_y;
    public Triangle() {
        pos_x = 0;
        pos_y = 0;
        dim_x = 0;
        dim_y = 0;
    }

    public void paintComponent(Graphics g) {
        g.drawPolygon(new int[]{pos_x, pos_x+(dim_x/2), pos_x+dim_x}, new int[]{pos_y+dim_y, pos_y, pos_y+dim_y}, 3);
    }

    @Override
    public void drawComponent(Graphics g, int pos_x, int pos_y, int dim_x, int dim_y) {

    }
}
