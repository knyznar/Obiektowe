package zad1;

public class FrameException extends Exception {
    private static String message = "Incorrect values in frames";

    public FrameException(int lineNumber, String line) {
        super();
        message = "Nr linii:" + Integer.toString(lineNumber) + " " + line;
    }

    public String getMessage() { return message; }
}
