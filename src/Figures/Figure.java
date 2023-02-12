package Figures;

import java.awt.*;
import java.util.ArrayList;

public interface Figure {

    //protected ArrayList<Figure> figures = new ArrayList<>();
    //public abstract void paintComponent(Graphics g);
    public abstract void drawComponent(Graphics g, int pos_x, int pos_y, int dim_x, int dim_y);

    //public abstract void paintComponent(Graphics g);

}
