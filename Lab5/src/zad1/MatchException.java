package zad1;

public class MatchException extends Exception{
    private static String message = "Wrong subtitle format";

    public String getMessage(){
        return message;
    }
}
