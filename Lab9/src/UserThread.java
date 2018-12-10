import java.io.*;
import java.net.*;


public class UserThread extends Thread {
    private Socket socket;
    private ChatServer server;
    private PrintWriter writer;

    public UserThread(Socket socket, ChatServer server) {
        this.socket = socket;
        this.server = server;
    }

    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);

            String userName = reader.readLine();
            server.addUserName(userName);

            String serverMessage = "New user connected: [" + userName +"]";
            server.broadcast(serverMessage, this);

            String clientMessage;

            do {
                clientMessage = reader.readLine();
                serverMessage = "[" + userName + "]: " + clientMessage; //!!!!!!!!
                server.broadcast(serverMessage, this);
                sendMessage(clientMessage);
            } while (!clientMessage.equals("disconnect"));

            socket.close();


        } catch (IOException ex) {
            System.out.println("Error in UserThread: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    void sendMessage(String message) {
        writer.println(message);
    }
}
