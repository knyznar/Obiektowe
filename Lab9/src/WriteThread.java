import java.io.*;
import java.net.*;


public class WriteThread extends Thread {
    private PrintWriter writer;
    private Socket socket;
    private ChatClient client;

    public WriteThread(Socket socket, ChatClient client) {
        this.socket = socket;
        this.client = client;

        try {
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException ex) {
            System.out.println("Error getting output stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void run() {

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userName = null;
        try {
            userName = stdIn.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.setUserName(userName);

        String userInput="";

        do {
            try {
                userInput = stdIn.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            writer.println("[" + userName + "]: " + userInput);
        } while (!userInput.equals("disconnect"));

        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println("Error writing to server: " + ex.getMessage());
        }
    }
}