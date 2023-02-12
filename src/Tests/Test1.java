package Tests;

import Figures.Circle;
import Figures.Figure;
import Figures.Rectangle;
import Figures.Triangle;

import java.util.Random;

public class Test1 {

    private Figure figure_a = null;
    private Figure figure_b = null;

    private int amount_a;
    private int amount_b;

    public Test1() {

        Random random = new Random();
        switch (random.nextInt(0, 3)) {
            case 0 -> figure_a = new Rectangle();
            case 1 -> figure_a = new Triangle();
            case 2 -> figure_a = new Circle();
        }
        while (figure_b == null) {
            switch (random.nextInt(0, 3)) {
                case 0 -> {
                    if (!(figure_a instanceof Rectangle))
                        figure_b = new Rectangle();
                }
                case 1 -> {
                    if (!(figure_a instanceof Triangle))
                        figure_b = new Triangle();
                }
                case 2 -> {
                    if (!(figure_a instanceof Circle))
                        figure_b = new Circle();
                }
            }
        }

        amount_a = random.nextInt(0, 2)*2 + 1;
        amount_b = random.nextInt(3, 5)*2;

    }

    public Figure getFigure_a() {
        return figure_a;
    }

    public Figure getFigure_b() {
        return figure_b;
    }

    public int getAmount_a() {
        return amount_a;
    }

    public int getAmount_b() {
        return amount_b;
    }
}
