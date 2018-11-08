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
                int first = (Integer.parseInt(matcher.group(1)) + (delay * 1000 / fps));
                int second = (Integer.parseInt(matcher.group(2)) + (delay * 1000 / fps));

                if(first > second) throw new FrameException();
                else fileToWrite.println("{" + Integer.toString(first) + "}{" + Integer.toString(second)+"}" + matcher.group(3));
            } else {
                throw new MatchException();
            }
        }
        fileToWrite.close();
    }
}
