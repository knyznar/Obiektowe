package shapes;

import javax.swing.*;
import java.awt.*;

public class Program extends JFrame {

    public Program(Panel panel) {
        super("ShapeDrawer");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setSize(450, 450);
        setVisible(true);
    }

    public static void main(String[] args) {
        MyPanel panel = new MyPanel();

        panel.shapelist.add(new Kwadrat("kwadrat",10, 10, 50));
        panel.shapelist.add(new Prostokat("prostokat", 70, 10, 50,30));
        panel.shapelist.add(new Kwadrat("kwadrat1",10, 80, 40));
        panel.shapelist.add(new Prostokat("prostokat1",70,80, 40, 20));
        panel.shapelist.add(new Kolo("kolo1",70,130, 40));
        panel.shapelist.add(new Trojkat("trojkat", 150, 70, 50, 60));

        Program program = new Program(panel);
    }
}
