package GUI;

import java.awt.*;
import javax.swing.*;

public class Test {

    public class MyGraphics extends JComponent {

        private static final long serialVersionUID = 1L;

        MyGraphics() {
            setPreferredSize(new Dimension(200, 200));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.fillRect(0, 50, 150, 100);
        }
    }

    public void createGUI() {
        final JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        Button button = new Button("1");
        Button button2 = new Button("2");
        //panel.add(new MyGraphics());

        frame.getContentPane().add(BorderLayout.WEST, panel2);
        frame.getContentPane().add(BorderLayout.EAST, panel);

        //panel.add(button);
        panel.add(new MyGraphics());
        panel2.add(button2);
        panel2.add(new MyGraphics());

        frame.pack();
        //frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//
//            @Override
//            public void run() {
//                Test GUI = new Test();
//                GUI.createGUI();
//            }
//        });
        Test gui = new Test();
        gui.createGUI();
    }
}