package Tests;

import Figures.Figure;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Quiz1 extends Quiz {
    public Quiz1(int amountOfQuestions, int amountOfAnswers, int m, int n) {
        super(amountOfQuestions, amountOfAnswers, m, n);

        Test1 test = new Test1();

        Figure figure_a = test.getFigure_a();
        Figure figure_b = test.getFigure_b();
        //figure_a.setColor()

        int amount_a = test.getAmount_a();
        int amount_b = test.getAmount_b();


        for (int i = 0; i < questions.length; i++) {

            //questions[i].setSize(new Dimension(PIC_WIDTH, PIC_HEIGHT)); //bylo setSize
            if (i+1 != questions.length)
            {
                questions[i].add(new MyGraphics(amount_a++, amount_b--, figure_a, figure_b)); // ZOBACZ JAKI TRIK MAKSANTY
            }
            else {
                questions[i].add(new MyGraphics(0, 0, figure_a, figure_b));
            }
        }

        GenerateAnswers(figure_a, figure_b, amount_a, amount_b);
    }

    public void GenerateAnswers(Figure a, Figure b, int goodAnswerNum1, int goodAnswerNum2)
    {
        for (int i = 0; i < amountOfAnswers; i++) {

            if (i != good_answer_index)
            {
                boolean goodFakeAnswer = false;
                while (!goodFakeAnswer){
                    int x = random.nextInt(0, 10);
                    int y = random.nextInt(0, 10);

                    if (x != goodAnswerNum1 || y != goodAnswerNum2)
                    {
                        goodFakeAnswer = true;

                        answers[i].add(new MyGraphics(x, y, a, b));
                    }
                }
            }
            else{
                answers[i].add(new MyGraphics(goodAnswerNum1, goodAnswerNum2, a, b));
            }
        }
    }

    public class MyGraphics extends BaseGraphics {

        private JPanel panel;
        private final ArrayList<int[]> figures_position = new ArrayList<>();
        private static final int size = 10;
        private final int am_a;
        private final int am_b;

        public MyGraphics(int am_a, int am_b, Figure a, Figure b)
        {
            super();

            this.am_a = am_a;
            this.am_b = am_b;

            figures.add(a);
            figures.add(b);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            drawSelector(g, figures.get(0), am_a);
            drawSelector(g, figures.get(1), am_b);
        }

        public void drawSelector(Graphics g, Figure figure, int amount) {
            Random random = new Random();
            int curr_x = 0;
            int curr_y = 0;
            double radius = (Math.sqrt(2)*size)/2;
            //System.out.println("RADIUS: " + radius); //r = 7.071
            boolean good_pos;

            for (int i = 0; i < amount; i++) {

                good_pos = false;

                while (!good_pos) {
                    curr_x = random.nextInt(5, PIC_WIDTH-size-5);
                    curr_y = random.nextInt(5, PIC_HEIGHT-size-5);
                    good_pos = true;
                    for (int[] f_pos : figures_position) {
                        if (Math.sqrt(Math.pow(curr_x - f_pos[0], 2) + Math.pow(curr_y - f_pos[1], 2)) <= 2*radius)
                        {
                            good_pos = false;
                            break;
                        }
                    }
                }

                figure.drawComponent(g, curr_x, curr_y, size, size);
                figures_position.add(new int[]{curr_x, curr_y});
            }
        }
    }
}
