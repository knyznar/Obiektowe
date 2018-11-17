package shapes;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Prostokat extends Shape {
    int height;

    public Prostokat(String name, int x, int y, int width, int height) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean contains(Point p){
        return(p.getX()>=x && p.getX()<=x+width && p.getY()>=y && p.getY()<=y+height);
    }

    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);
        g2d.fillRect(x,y,width, height);
    }
}

