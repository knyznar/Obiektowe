package zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Subtitles {

    public static void delay(final String in, final String out, int delay, int fps) throws FrameException, MatchException {
        File fileToOpen = new File(in);
        Pattern compiledPattern = Pattern.compile("\\{(\\d+)\\}\\{(\\d+)\\}(.+)");
        Scanner sc = null;

        try {
            sc = new Scanner(fileToOpen);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        PrintWriter fileToWrite = null;
        try {
            fileToWrite = new PrintWriter(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            Matcher matcher = compiledPattern.matcher(line);
            if (matcher.matches()) {
                int first = (Integer.parseInt(matcher.group(1)) + delay * 1000 / fps);
                int second = (Integer.parseInt(matcher.group(2)) + delay * 1000 / fps);

                if(first > second) throw new FrameException();
                else fileToWrite.println("{" + Integer.toString(first) + "}{" + Integer.toString(second)+"}" + matcher.group(3));
            } else {
                throw new MatchException();
            }
        }
        fileToWrite.close();
    }
}


//        String s1= "";
//        String s2="";
//        String line;
//
//        while (sc.hasNextLine()) {
//            Scanner l = new Scanner(sc.nextLine());
//            line = l.nextLine();
//            int firstOpenBracket = line.indexOf('{');
//            int firstCloseBracket = line.indexOf('}');
//            int secondOpenBracket = line.indexOf('{', firstOpenBracket+1);
//            int secondCloseBracket = line.indexOf('}', firstCloseBracket+1);
//            s1 = line.substring(firstOpenBracket+1, firstCloseBracket);
//            s2 = line.substring(secondOpenBracket+1, secondCloseBracket);
//
//            System.out.println("s1 " + s1);
//            System.out.println("s2 " + s2);
//            s1 = String.valueOf(Integer.parseInt(s1) + delay/1000*fps);
//            s2 = String.valueOf(Integer.parseInt(s2) + delay/1000*fps);
//            System.out.println("ees1 " + s1);
//            System.out.println("ees2 " + s2);
//
//            fileToWrite.println("{" + s1 +"}"+"{"+ s2 +"}"+ line.substring(secondCloseBracket+1));
//            }
//        fileToWrite.close();
//
//        }



