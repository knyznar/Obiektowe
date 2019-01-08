package sample;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.*;
import java.util.*;

class Server implements Runnable {
    private Scanner in;
    private PrintStream out;
    private Socket s;

    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    ArrayList<String> players = new ArrayList<>();
    ArrayList<Integer> wyniki = new ArrayList<>();


    public Server(Socket s) throws IOException {
        this(s.getInputStream(),s.getOutputStream());
        this.s = s;
    }

    public Server(InputStream input, OutputStream output) {
        in = new Scanner(input);
        out = new PrintStream(output);
    }


    public void connectToDB() {
        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/knyznar",
                            "knyznar", "s39kUtEmziorNP4z");
        } catch (SQLException ex) {
            if (conn == null)

                if(conn==null) {
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addDataToDB(ArrayList<String> players, ArrayList<Integer> wyniki){
        try {
            String newValue = ("'"+players+"'"+",'"+wyniki);
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO books VALUES("+ newValue+");");
        }catch (SQLException ex){
            // handle any errors
        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore
                stmt = null;
            }
        }
    }

    public void run() {
        connectToDB();
        while(!Thread.currentThread().isInterrupted()) {
            String newPlayer = in.nextLine();
            players.add(newPlayer);
            wyniki.add(0);
        }
        addDataToDB(players, wyniki);
        try {
            out.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}