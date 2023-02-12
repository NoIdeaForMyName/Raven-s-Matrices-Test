package Tests;

import Figures.*;
import Figures.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

public class Quiz3 extends Quiz
{

    public Quiz3(int amountOfQuestions, int amountOfAnswers, int m, int n) {
        super(amountOfQuestions, amountOfAnswers, m, n);

        Test3 test = new Test3(false, m);

        Figure[][] figures = test.getFigures();
        Color[][] colors = test.getFigureColors();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int index = i * n + j;

                if (index != amountOfQuestions - 1){
                    questions[index].add(new MyGraphics(figures[i][j], colors[i][j]));
                }
                else{
                    questions[index].add(new BaseGraphics());
                }
            }
        }

        GenerateAnswers(figures[m - 1][2], colors[m - 1][2]);
    }

    public void GenerateAnswers(Figure correctFigure, Color correctColor) {
        for (int i = 0; i < amountOfAnswers; i++) {

            if (i != good_answer_index) {
                boolean goodFakeAnswer = false;
                while (!goodFakeAnswer) {
                    Figure fakeFigure = new Figure[] { new Triangle(), new Rectangle(), new Circle() }[random.nextInt(3)];

                    Color additionalFakeColor = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
                    // change 3 to 4 to not ignore additionalFakeColor
                    Color fakeColor = new Color[] { Color.RED, Color.GREEN, Color.BLUE, additionalFakeColor }[random.nextInt(3)];

                    if (!(correctFigure.getClass().equals(fakeFigure.getClass()) && areColorsSimilar(fakeColor, correctColor, 10))) {
                        goodFakeAnswer = true;

                        answers[i].add(new MyGraphics(fakeFigure, fakeColor));
                    }
                }
            }
            else
            {
                answers[i].add(new MyGraphics(correctFigure, correctColor));
            }
        }
    }

    boolean areColorsSimilar(Color c, Color v, float x){
        double distance = (c.getBlue() - v.getBlue())*(c.getBlue() - v.getBlue()) +
                (c.getGreen() - v.getGreen())*(c.getGreen() - v.getGreen()) +
                (c.getRed() - v.getRed())*(c.getRed() - v.getRed());
        return distance < x;
    }

    class MyGraphics extends BaseGraphics
    {
        private JPanel panel;
        private static final int size = 50;

        Figure figure;

        public MyGraphics(Figure figure, Color color)
        {
            super();

            this.figure = figure;
            this.color = color;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            drawSelector(g, figure, color);
        }

        public void drawSelector(Graphics g, Figure figure, Color color)
        {
            figure.drawComponent(g, 75 - size / 2, 50 - size / 2, size, size, color);
        }
    }
}
