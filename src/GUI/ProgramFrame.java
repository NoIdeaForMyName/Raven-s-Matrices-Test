package GUI;

import Tests.Quiz1;
import Tests.Quiz;
import Tests.Quiz2;
import Tests.Quiz3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class ProgramFrame extends JFrame {

    Random random;
    Quiz quiz;

    JPanel quizPanel;

    int score = 0;
    public ProgramFrame() {
        random = new Random();

        quizPanel = new JPanel();
        add(quizPanel);

        DisplayNewQuiz();

        setTitle("Raven's Matrices Test by 3 muszkieterów");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 650);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);

        repaint();
        revalidate();

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.out.println("TWOJE IQ WYNOSI: " + score);
                e.getWindow().dispose();
            }
        });
    }

    public void DisplayNewQuiz()
    {
        remove(quizPanel);

        int x = random.nextInt(4);
        if (x == 3)
        {
            FinalSequencesTest test = new FinalSequencesTest();
            quizPanel = test.panel;


            test.submit.addActionListener(e -> {
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                DisplayNewQuiz();
                                score += test.punktacja;
                                System.out.println(score);
                            }
                        },
                        2000
                );
            });

            add(quizPanel);
        }
        else
        {
            quiz = switch (x) {
                default -> new Quiz1(6, 6, 1, 7);
                case 1 -> new Quiz2(9, 5, 3, 3);
                case 2 -> new Quiz3(9, 6, 3, 3);
            };

            quizPanel = new JPanel();

            JPanel questionPanel = quiz.getQuestionPanel();
            JPanel answerPanel = quiz.getAnswerPanel();
            JPanel textPanel = quiz.getTextPanel();
            JPanel confirmPanel = quiz.getConfirmPanel();

            quizPanel.setLayout(new BoxLayout(quizPanel, BoxLayout.Y_AXIS));

            quizPanel.add(questionPanel);
            quizPanel.add(textPanel);
            quizPanel.add(answerPanel);
            quizPanel.add(confirmPanel);

            quiz.getConfirmButton().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    score += quiz.CheckIfRightAnswer()? 1 : 0;
                    DisplayNewQuiz();
                    System.out.println(score);
                }
            } );

            add(quizPanel);
        }

        repaint();
        revalidate();
    }
}
