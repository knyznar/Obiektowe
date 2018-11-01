package lab4;

import java.io.*;
import java.util.Scanner;


public class Cryptographer {
    static void cryptfile(File fileToOpen, PrintWriter fileToWrite, Algorithm algo){
        Scanner sc = null;
        try {
            sc = new Scanner(fileToOpen);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNextLine()) {
            Scanner s2 = new Scanner(sc.nextLine());
            while (s2.hasNext()) {
                String s = s2.next();
                fileToWrite.print(algo.crypt(s));
                fileToWrite.print(" ");
            }
            fileToWrite.println();
        }
        fileToWrite.close();

    }

     static void decryptfile(File fileToOpen, PrintWriter fileToWrite, Algorithm algo){
         Scanner sc = null;
         try {
             sc = new Scanner(fileToOpen);
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }
         while (sc.hasNextLine()) {
             Scanner s2 = new Scanner(sc.nextLine());
             while (s2.hasNext()) {
                 String s = s2.next();
                 fileToWrite.print(algo.decrypt(s));
                 fileToWrite.print(" ");
             }
             fileToWrite.println();
         }
         fileToWrite.close();
     }
}






