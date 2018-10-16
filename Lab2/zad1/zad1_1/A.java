package zad1.zad1_1;

public class A{
    protected int number;
    String name;

    public A(int _number, String _name){
        this.number = _number;
        this.name = _name;
    }
    public int getNumber() {
        return number;
    }
    public String getName(){
        return name;
    }
    public void callDecrement(){decrement();}
    public void callChangeName(){changeName();}
    public void callIncrement(){increment();}
    private void increment(){number++;}
    protected void decrement(){number--;}
    void changeName(){name = "name A";}
}