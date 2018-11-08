package zad1;

public class FrameException extends Exception {
    private static String message = "Incorrect values in frames";

    public String getMessage() { return message; }
}
