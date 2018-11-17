package graph;

import javax.swing.*;
import java.awt.*;
public class Program extends JFrame {

    private static final int N = 500;
    private static final int A = 1;
    private static final int B = 0;
    private static final int C = 0;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame frame = new JFrame("Quadratic Slider");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new GraphPanel());
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
