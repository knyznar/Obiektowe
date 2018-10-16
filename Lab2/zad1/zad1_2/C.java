package zad1.zad1_2;
import zad1.zad1_1.*;

public class C extends B{
    public C(int Cnumber, String Cname){
        super(Cnumber, Cname);
    }
    void changeName(String name){name = "Name C";}

    public static void main(String[] args){
        A a = new A(1, "ASDF");
        a.callIncrement();
        System.out.println("a: " + a.getNumber());

        B b = new B(1, "ASDF");
        b.callIncrement();
        System.out.println("b: " + b.getNumber());
    }
}
