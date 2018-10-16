package zad4;

public class Prostokat extends Kwadrat{
    private double b;
    public Prostokat(double a, double b) {
        super(a);
        this.b = b;
    }
    public void setB(double b) {
        this.b = b;
    }
    public double getB() {
        return b;
    }
    public double area(){
        return getA()*b;
    }
    public boolean isBigger(Prostokat p){
        return p.area()>this.area();
    }
}
