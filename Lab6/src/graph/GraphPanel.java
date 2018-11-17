package graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GraphPanel extends JPanel {
    private Box layout;
    private JPanel graphPanel;
    private JTextField rangeFrom, rangeTo;
    private JTextField w0, w1, w2, w3, w4, w5;
    private JTextField dx;                 //wspolczynnik próbkowania
    private JLabel lrangeFrom, lrangeTo;
    private JLabel lw0, lw1, lw2, lw3, lw4, lw5;
    private JLabel ldx;
    private JButton button;

    private ArrayList<Double> values;

    public GraphPanel() {
        values = null;
        lrangeFrom = new JLabel("zakres od: ");
        lrangeTo = new JLabel("zakres do: ");
        lw0 = new JLabel("w0: ");
        lw1 = new JLabel("w1: ");
        lw2 = new JLabel("w2: ");
        lw3 = new JLabel("w3: ");
        lw4 = new JLabel("w4: ");
        lw5 = new JLabel("w5: ");
        ldx = new JLabel("współczynnik próbkowania: ");

        rangeFrom = new JTextField();
        rangeFrom.setText("0");
        rangeTo = new JTextField();
        rangeTo.setText("10");

        w0 = new JTextField();
        w0.setText("0");
        w1 = new JTextField();
        w1.setText("0");
        w2 = new JTextField();
        w2.setText("0");
        w3 = new JTextField();
        w3.setText("0");
        w4 = new JTextField();
        w4.setText("0");
        w5 = new JTextField();
        w5.setText("0");
        dx = new JTextField();
        dx.setText("0.1");
        button = new JButton("Rysuj");
        button.addActionListener(new ButtonListener());

        rangeFrom.addKeyListener(nonDigit());
        rangeTo.addKeyListener(nonDigit());
        w0.addKeyListener(nonDigit());
        w1.addKeyListener(nonDigit());
        w2.addKeyListener(nonDigit());
        w3.addKeyListener(nonDigit());
        w4.addKeyListener(nonDigit());
        w5.addKeyListener(nonDigit());
        dx.addKeyListener(nonDigit());

        layout = new Box(BoxLayout.Y_AXIS);
        layout.add(lrangeFrom);
        layout.add(rangeFrom);
        layout.add(lrangeTo);
        layout.add(rangeTo);
        layout.add(ldx);
        layout.add(dx);
        layout.add(lw0);
        layout.add(w0);
        layout.add(lw1);
        layout.add(w1);
        layout.add(lw2);
        layout.add(w2);
        layout.add(lw3);
        layout.add(w3);
        layout.add(lw4);
        layout.add(w4);
        layout.add(lw5);
        layout.add(w5);
        layout.add(Box.createRigidArea(new Dimension(0, 20)));
        layout.add(button);

        graphPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(700, 700);
            }

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                int width = getWidth();
                int height = getHeight();
                int point0 = height / 2;

                g.setColor(Color.gray);
                g.drawLine(0,point0,width,point0);

                if(values != null) {
                    double from = Double.parseDouble(rangeFrom.getText());
                    double to = Double.parseDouble(rangeTo.getText());

                    if(to < 0.0) {
                        g.drawLine(width-1,0,width-1, height);
                    }
                    else if(from > 0.0) {
                        g.drawLine(0,0,0, height);
                    }
                    else {
                        int from_int = (int)Math.round(from);
                        int to_int = (int)Math.round(to);
                        int y_axis = width * Math.abs(from_int) / (to_int - from_int);

                        g.drawLine(y_axis,0,y_axis, height);
                    }

                    double max = Math.abs(values.get(0));
                    for (int i = 1; i < values.size(); ++i) {
                        if (Math.abs(values.get(i)) > max)
                            max = Math.abs(values.get(i));
                    }
                    double pdy = max / (height / 2);
                    g.setColor(Color.BLACK);
                    int x1 = 0;
                    int y1 = point0 - Math.round((float)(values.get(0) / pdy));
                    for (int i = 1; i < values.size(); ++i) {
                        double real_x2 = ((double)width / (double)values.size()) * (double)i;
                        int x2 = (int)Math.round(real_x2);
                        int y2 = point0 - Math.round((float) (values.get(i) / pdy));
                        g.drawLine(x1, y1, x2, y2);
                        x1 = x2;
                        y1 = y2;
                    }
                }
                else
                {
                    g.drawLine(width/2,0,width / 2, height);
                }

            }
        };
        graphPanel.setBackground(Color.white);

        add(graphPanel);
        add(layout);
    }

    public KeyAdapter nonDigit(){
        KeyAdapter keyAdapter = new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                if (!Character.isDigit(e.getKeyChar()) && e.getKeyChar()!='-'&& e.getKeyChar()!='.'){
                    e.consume();
                }
            }
        };
        return keyAdapter;
    }

    public class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            double from, to;
            double W0, W1, W2, W3, W4, W5;
            double delta;
            from = Double.parseDouble(rangeFrom.getText());
            to = Double.parseDouble(rangeTo.getText());

            if(Character.isDigit(w0.getText().charAt(0))) {
                W0 = Double.parseDouble(w0.getText());
            }else{
                W0=0;
            }
            W1 = Double.parseDouble(w1.getText());
            W2 = Double.parseDouble(w2.getText());
            W3 = Double.parseDouble(w3.getText());
            W4 = Double.parseDouble(w4.getText());
            W5 = Double.parseDouble(w5.getText());
            delta = Double.parseDouble(dx.getText());
            values = new ArrayList<>();
            for(double x=from; x <= to; x += delta){
                values.add(W5*Math.pow(x,5)+W4*Math.pow(x,4)+W3*Math.pow(x,3)+W2*Math.pow(x,2)+W1*x+W0);
            }
            repaint();
        }
    }
}
