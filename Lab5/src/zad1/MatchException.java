package zad1;

public class MatchException extends Exception{
    private static String message = "Wrong subtitle format";

    public MatchException(int lineNumber, String line) {
        super();
        message = "Nr linii:" + Integer.toString(lineNumber) + " " + line;
    }

    public String getMessage(){
        return message;
    }
}
