package graph;

import javax.swing.*;

public class Program extends JFrame {
    public Program() {
        JFrame frame = new JFrame("Graph Drawer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GraphPanel());
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Program program = new Program();
    }
}
