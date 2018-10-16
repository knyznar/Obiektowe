package zad4;
import java.util.*;

public class Test {
    public static void main(String[] Args){
        LinkedList<Prostokat> figury = new LinkedList<Prostokat>();
        Scanner odczyt;
        odczyt = new Scanner(System.in);
        boolean exit = false;

        while(!exit){
            System.out.print("1 - wczytaj prostokąt\n" +
                    "2 - wyswietl wszystkie prostokaty\n" +
                    "3 - oblicz sume pol wszystkich prostokatow\n" +
                    "4 - zakoncz\n");
            int wybor = odczyt.nextInt();
            switch(wybor){
                case 1:
                    double a,b;
                    System.out.print("Podaj a: ");
                    a = odczyt.nextDouble();
                    System.out.print("Podaj b: ");
                    b = odczyt.nextDouble();
                    Prostokat p = new Prostokat(a,b);
                    figury.add(p);
                    break;
                case 2:
                    for(int i=0; i<figury.size(); ++i){
                        System.out.println("Prostokat: "+ figury.get(i).getA()+" x "+figury.get(i).getB());
                    }
                    break;
                case 3:
                    int suma=0;
                    for(int i=0; i<figury.size(); ++i){
                        suma += figury.get(i).area();
                    }
                    System.out.println("Suma: "+suma);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Niepoprawna komenda");
                    break;
            }
        }
    }
}
