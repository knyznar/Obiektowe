package zad1;

public class Main {
    public static int delay = 5;
    public static int fps = 24;
    public static void main(String[] argv){
        try {
            Subtitles.delay(argv[0], argv[1], delay, fps);//Integer.parseInt(argv[2]), Integer.parseInt(argv[3]));
        } catch (FrameException | MatchException e) {
            e.getMessage();
        }
    }
}
