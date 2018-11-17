package shapes;

import java.awt.*;

public class Kwadrat extends Shape {

    public Kwadrat(String name, int x, int y, int width) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
    }

    public boolean contains(Point p){
        return(p.getX()>=x && p.getX()<=x+width && p.getY()>=y && p.getY()<=y+width);
    }
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.blue);
        g2d.fillRect(x,y,width, width);
    }
}
