package zad1;

public class FrameException extends Exception {
    private static String message = "Incorrect parentheses";

    public String getMessage(){
        return message;
    }
}
