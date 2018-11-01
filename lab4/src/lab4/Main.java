package lab4;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] argv){
        File fileToOpen = new File(argv[0]);
        PrintWriter fileToWrite = null;
        try {
            fileToWrite = new PrintWriter(argv[1]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String choice, algorithm;
        Scanner readChoice = new Scanner(System.in);
        System.out.print("1 - szyfrowanie\n2 - deszyfrowanie\n");
        choice = readChoice.nextLine();
        System.out.print("1 - ROT11\n2 - Polibiusz\n");
        Scanner readAlgo = new Scanner(System.in);
        algorithm = readAlgo.nextLine();

        if(choice.equals("1")){
            if(algorithm.equals("1")) Cryptographer.cryptfile(fileToOpen, fileToWrite, new ROT11());
            else if(algorithm.equals("2")) Cryptographer.cryptfile(fileToOpen, fileToWrite, new Polibiusz());
        }
        else if(choice.equals("2")){
            if(algorithm.equals("1")) Cryptographer.decryptfile(fileToOpen, fileToWrite, new ROT11());
            else if(algorithm.equals("2")) Cryptographer.decryptfile(fileToOpen, fileToWrite, new Polibiusz());
        }

    }
}
