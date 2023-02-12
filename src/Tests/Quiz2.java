package Tests;

import Figures.RotatableTriangle;
import Figures.TriangleDir4;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Quiz2 extends Quiz
{

    public Quiz2(int amountOfQuestions, int amountOfAnswers, int m, int n) {
        super(amountOfQuestions, amountOfAnswers, m, n);

        Test2 test = new Test2();

        boolean[][][] rects = test.getRect();
        boolean[][] triangles_inside = test.getTriangles_inside();

        RotatableTriangle figure_a = (RotatableTriangle) test.getFigure_a();

        for (int i = 0; i < n; i++) {
            boolean[][] rectsVerse = rects[i];
            for (int j = 0; j < m; j++) {
                int index = i * n + j;
                if (index != amountOfQuestions - 1){
                    questions[index].add(new MyGraphics(rectsVerse[j], triangles_inside[i], figure_a));
                }
                else{
                    questions[index].add(new BaseGraphics());
                }
            }
        }

        GenerateAnswers(figure_a, rects[2][2], triangles_inside[2], m, n);
    }

    public void GenerateAnswers(RotatableTriangle a, boolean[] sidesCorrect, boolean[] sidesInsideCorrect, int m, int n) {
        for (int i = 0; i < amountOfAnswers; i++) {

            if (i != good_answer_index) {
                boolean goodFakeAnswer = false;
                while (!goodFakeAnswer) {
                    boolean[] fakeSides = new boolean[] {random.nextBoolean(), random.nextBoolean(),
                            random.nextBoolean(), random.nextBoolean()};
                    boolean[] fakeInsides = new boolean[] {random.nextBoolean(), random.nextBoolean()};

                    if (!(Arrays.equals(sidesCorrect, fakeSides) && Arrays.equals(sidesInsideCorrect, fakeInsides))) {
                        goodFakeAnswer = true;

                        answers[i].add(new MyGraphics(fakeSides, fakeInsides, a));
                    }
                }
            } else {
                answers[i].add(new MyGraphics(sidesCorrect, sidesInsideCorrect, a));
            }
        }
    }

    class MyGraphics extends BaseGraphics
    {
        private JPanel panel;
        private static final int size = 15;
        private final boolean[] sides;
        private final boolean[] trianglesInside;

        public RotatableTriangle triangle;

        public MyGraphics(boolean[] sides, boolean[] trianglesInside, RotatableTriangle a)
        {
            super();

            this.sides = sides;
            this.trianglesInside = trianglesInside;

            triangle = a;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.drawRect(35, 25, 80, 50);

            drawSelector(g, trianglesInside, triangle);
        }

        public void drawSelector(Graphics g, boolean[] trianglesInside, RotatableTriangle figure) {
            Polygon triangle;
            if (sides[0]){
                triangle = TriangleDir4.createTriangle(75,25,size,size, trianglesInside[0]? TriangleDir4.Direction.UP : TriangleDir4.Direction.DOWN);
                g.fillPolygon(triangle);
            }
            if (sides[1]){
                triangle = TriangleDir4.createTriangle(75,75,size,size, trianglesInside[0]? TriangleDir4.Direction.UP : TriangleDir4.Direction.DOWN);
                g.fillPolygon(triangle);
            }
            if (sides[2]){
                triangle = TriangleDir4.createTriangle(35,50,size,size, trianglesInside[1]? TriangleDir4.Direction.LEFT : TriangleDir4.Direction.RIGHT);
                g.fillPolygon(triangle);
            }
            if (sides[3]){
                triangle = TriangleDir4.createTriangle(115,50,size,size, trianglesInside[1]? TriangleDir4.Direction.LEFT : TriangleDir4.Direction.RIGHT);
                g.fillPolygon(triangle);
            }
        }
    }
}
