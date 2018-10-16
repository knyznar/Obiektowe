package zad1.zad1_1;

public class B extends A{
    public B(int Bnumber, String Bname){
        super(Bnumber, Bname);
    }
    protected void decrement(){number-=2;}
    void changeName(){name = "Name B";}
    private void increment(){number += 2;}
}