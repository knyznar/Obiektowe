package shapes;

import java.awt.*;

public class Kolo extends Shape {
    public Kolo(String name, int x, int y, int width) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
    }
    public boolean contains(Point p){
        return Math.pow((int)(p.getX()-(x+width/2)),2) + Math.pow((int)(p.getY()-(y+width/2)),2) < Math.pow(width/2, 2);
    }

    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.blue);
        g2d.fillOval(x,y,width, width);
    }
}
