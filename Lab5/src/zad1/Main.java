package zad1;

public class Main {
    public static void main(String[] argv){
        try {
            Subtitles.delay(argv[0], argv[1], Integer.parseInt(argv[2]), Integer.parseInt(argv[3]));
        } catch (FrameException | MatchException e) {
            e.getMessage();
            System.out.print(e.getStackTrace());
        }
    }
}
