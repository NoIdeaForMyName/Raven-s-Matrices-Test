package GUI;

import Figures.Figure;
import Tests.Test_1;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Program_1 {


//    add(new Panel());
//    setTitle("Raven's Matrices Test by Michal Maksanty");
//    setDefaultCloseOperation(EXIT_ON_CLOSE);
//    setResizable(false);
//    pack();
//    setVisible(true);
//    setLocationRelativeTo(null);

    private final int WIDTH = 1200;
    private final int HEIGHT = 700;
    private final int PIC_WIDTH = 150;
    private final int PIC_HEIGHT = 100;
    private JPanel questionPanel = new JPanel(new FlowLayout());
    private JPanel answerPanel = new JPanel(new FlowLayout());
    private JPanel[] questions;
    private Figure figure_a;
    private Figure figure_b;
    private int amount_a;
    private int amount_b;

    public Program_1(int amount) {
//        setPreferredSize(new Dimension(WIDTH, HEIGHT));
//        setFocusable(true);
        //questionPanel.setSize(WIDTH, HEIGHT/3);
        //answerPanel.setSize(WIDTH, HEIGHT/3 * 2);
        questionPanel.setFocusable(true);
        answerPanel.setFocusable(true);

        questions = new JPanel[amount];

        Test_1 test = new Test_1();

        figure_a = test.getFigure_a();
        figure_b = test.getFigure_b();

        amount_a = test.getAmount_a();
        amount_b = test.getAmount_b();

        for (int i = 0; i < questions.length; i++) {

            questions[i] = new JPanel();
            questions[i].setSize(new Dimension(PIC_WIDTH, PIC_HEIGHT)); //bylo setSize
            questions[i].add(new MyGraphics(questions[i]));
            questionPanel.add(questions[i]);

            questions[i].setFocusable(true);


        }
    }

    public JPanel getQuestionPanel() {
        return questionPanel;
    }

    public JPanel getAnswerPanel() {
        return answerPanel;
    }



    public class MyGraphics extends JComponent {

        private static final long serialVersionUID = 1L;
        private JPanel panel;
        private final ArrayList<int[]> figures_position = new ArrayList<>();
        private static final int size = 5;

        public MyGraphics(JPanel panel) {
            setPreferredSize(new Dimension(PIC_WIDTH, PIC_HEIGHT));
            this.panel = panel;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            //g.fillRect(0, 50, 200, 100);
            g.drawRect(panel.getX() + 10, panel.getY() + 10, PIC_WIDTH-20, PIC_HEIGHT-20);
            drawSelector(g, figure_a ,amount_a);
            drawSelector(g, figure_b, amount_b);
            System.out.println(panel.getX() + " " + panel.getY());
        }

        public void drawSelector(Graphics g, Figure figure, int amount) {
            Random random = new Random();
            int curr_x;
            int curr_y;
            double radius = (Math.sqrt(2)*size)/2;
            boolean good_pos = false;

            for (int i = 0; i < amount; i++) {

                curr_x = random.nextInt(0, PIC_WIDTH-size);
                curr_y = random.nextInt(0, PIC_HEIGHT-size);
                while (!good_pos) {
                    good_pos = true;
                    for (int[] f_pos : figures_position) {
                        if (Math.sqrt(Math.pow(curr_x - f_pos[0], 2) + Math.pow(curr_y - f_pos[1], 2)) <= 2*radius) {
                            good_pos = false;
                            curr_x = random.nextInt(0, PIC_WIDTH-size);
                            curr_y = random.nextInt(0, PIC_HEIGHT-size);
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
