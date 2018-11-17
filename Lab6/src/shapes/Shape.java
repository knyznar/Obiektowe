package shapes;

import java.awt.*;

public abstract class Shape {
    public String name;
    int width;
    int x,y;
    public abstract boolean contains(Point p);
    public abstract void draw(Graphics g);
}

