package shapes;

import java.awt.*;

public class Trojkat extends Shape {
    private int height;

    public Trojkat(String name, int x, int y, int width, int height) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.green);
        Polygon trojkat = new Polygon(new int[]{ x, x+width/2, x+width}, new int[]{ (y + height), y, (y + height) }, 3);
        g2d.fillPolygon(trojkat);
    }

    @Override
    public boolean contains(Point p) {
        Polygon trojkat = new Polygon(new int[]{ x, x+width/2, x+width}, new int[]{ (y + height), y, (y + height) }, 3);
        return trojkat.contains(p);
    }
}
