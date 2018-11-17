package shapes;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

public class MyPanel extends Panel implements MouseListener, MouseMotionListener {
    public MyPanel() {
//        setPreferredSize(new Dimension(1000, 600));
        addMouseListener(this);
        addMouseMotionListener(this);
        shapelist = new LinkedList<>();
    }

    public LinkedList<Shape> shapelist;
    private Shape current=null;
    private double x,y;

    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();       //współrzędne w których kliknięto
        y = e.getY();
        for(Shape s: shapelist){
            if(s.contains(e.getPoint())){
                current = s;
                break;
            }
        }
        repaint();
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        current = null;
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        if(current != null){
            current.x = e.getX();
            current.y = e.getY();
            repaint();
        }
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mouseMoved(MouseEvent arg0) {
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(Shape s: shapelist){
            s.draw(g);
        }
    }
}
