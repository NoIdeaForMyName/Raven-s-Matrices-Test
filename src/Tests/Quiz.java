package Tests;

import Figures.Figure;
import Figures.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Random;

public abstract class Quiz implements ActionListener
{
    public final int PIC_WIDTH = 150;
    public final int PIC_HEIGHT = 100;
    protected JPanel questionPanel = new JPanel(new FlowLayout());
    protected JPanel textPanel = new JPanel(new BorderLayout());
    protected JPanel answerPanel = new JPanel(new FlowLayout());
    protected JPanel confirmPanel = new JPanel();
    protected JPanel[] questions;
    protected JPanel[] answers;
    protected JPanel[] buttons;
    protected ButtonGroup buttonGroup = new ButtonGroup();

    public int good_answer_index;

    public BaseGraphics baseGraphics;

    protected Random random = new Random();

    public int amountOfAnswers;
    private JButton confirmButton;

    public boolean correctAnswerChosen = false;

    public Quiz(int amountOfQuestions, int amountOfAnswers, int m, int n) {

//        setPreferredSize(new Dimension(WIDTH, HEIGHT));
//        setFocusable(true);
        //questionPanel.setMaximumSize(new Dimension(WIDTH, HEIGHT/3));
        //answerPanel.setPreferredSize(new Dimension(200, HEIGHT/3 * 2));

        this.amountOfAnswers = amountOfAnswers;

        questions = new JPanel[amountOfQuestions];
        answers = new JPanel[amountOfAnswers];
        buttons = new JPanel[amountOfAnswers];

        questionPanel.setLayout(new GridLayout(m, n));

        // tutaj answer for loop:
        JLabel text = new JLabel("Choose correct answer:");

        //text.setMinimumSize(new Dimension(700, 30));
        textPanel.setPreferredSize(new Dimension(500, 100));
        text.setFont(new Font("Arial", Font.PLAIN, 30));
        text.setHorizontalAlignment(SwingConstants.CENTER);
        textPanel.add(BorderLayout.SOUTH, text);

        good_answer_index = random.nextInt(0, amountOfAnswers);

        for (int i = 0; i < amountOfQuestions; i++) {
            questions[i] = new JPanel();
            questionPanel.add(questions[i]);
        }

        for (int i = 0; i < amountOfAnswers; i++) {

            answers[i] = new JPanel();
            answers[i].setLayout(new BoxLayout(answers[i], BoxLayout.Y_AXIS));

            buttons[i] = new JPanel();
            buttons[i].add(new JRadioButton(String.valueOf((char) (65+i)))); //A - 65
            buttonGroup.add((JRadioButton) buttons[i].getComponent(0));
            buttons[i].setPreferredSize(new Dimension(PIC_WIDTH, 40));

            //answers[i].setSize(new Dimension(PIC_WIDTH, PIC_HEIGHT));

            answers[i].add(buttons[i]);
            answerPanel.add(answers[i]);

        }

        //button potwierdz
        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(this);
        confirmPanel.add(confirmButton);
    }

    public JPanel getQuestionPanel() {
        return questionPanel;
    }

    public JPanel getAnswerPanel() {
        return answerPanel;
    }

    public JPanel getTextPanel() {
        return textPanel;
    }

    public JPanel getConfirmPanel() {
        return confirmPanel;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CheckIfRightAnswer();
    }

    public boolean CheckIfRightAnswer(){
        int selectedButtonIndex = 0;
        for (int i = 0; i < amountOfAnswers; i++)
        {
            if (((JRadioButton) (buttons[i].getComponent(0))).isSelected()){
                selectedButtonIndex = i;
                correctAnswerChosen = selectedButtonIndex == good_answer_index;
            }
        }
        return correctAnswerChosen;
    }

    public class BaseGraphics extends JComponent {

        private JPanel panel;
        public ArrayList<Figure> figures = new ArrayList<>();

        public BaseGraphics() {
            setPreferredSize(new Dimension(PIC_WIDTH, PIC_HEIGHT));
        }

        public Color color = Color.BLACK;

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.drawRect(0, 0, PIC_WIDTH-1, PIC_HEIGHT-1);
        }
    }
}
