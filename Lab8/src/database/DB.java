package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.*;

public class DB{
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public void connect() {
        int tries=0;
        while(tries<3){
            try {
                conn =
                        DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/knyznar",
                                "knyznar", "s39kUtEmziorNP4z");
                break;
            } catch (SQLException ex) {
                if (conn == null)
                    tries++;

                if(conn==null && tries==2) {
                    System.out.println("Brak połączenia po 3 próbach");
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ObservableList getAllData(){
        connect();
        ObservableList<Book> content = FXCollections.observableArrayList();

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM books");

            while(rs.next()){
                content.add(new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
            return content;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // zwalniamy zasoby, które nie będą potrzebne
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
        return null;
    }
    public ObservableList getDataByAuthor(String author){
        connect();
        ObservableList<Book> content = FXCollections.observableArrayList();

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM books WHERE author like '" +author+ "';");

            while(rs.next()){
                content.add(new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
            return content;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // zwalniamy zasoby, które nie będą potrzebne
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
        return null;
    }
    public ObservableList getDataByLastName(String lastName){
        connect();
        ObservableList<Book> content = FXCollections.observableArrayList();

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM books WHERE author like '% " +lastName+ "';");

            while(rs.next()){
                content.add(new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
            return content;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // zwalniamy zasoby, które nie będą potrzebne
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
        return null;
    }
    public ObservableList getDataByISBN(String isbn){
        connect();
        ObservableList<Book> content = FXCollections.observableArrayList();

            try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM books WHERE isbn like '" +isbn+ "';");

            while(rs.next()){
                content.add(new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
            return content;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // zwalniamy zasoby, które nie będą potrzebne
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
        return null;
    }
    public void addBook(Book book){
        try {
            String newValue = ("'"+book.getIsbn()+"'"+",'"+book.getTitle()+"','"+book.getAuthor()+"',"+book.getYear());
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO books VALUES("+ newValue+");");
        }catch (SQLException ex){
            // handle any errors
        }finally {
            // zwalniamy zasoby, które nie będą potrzebne
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

}
