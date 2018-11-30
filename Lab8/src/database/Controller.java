package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML private TableView<Book> tableView;
    @FXML private TableColumn<Book, String> isbn;
    @FXML private TableColumn<Book, String> title;
    @FXML private TableColumn<Book, String> author;
    @FXML private TableColumn<Book, String> year;
    @FXML private Button viewAll;
    @FXML private Button byAuthorButton;
    @FXML private Button byISBNButton;
    @FXML private Button addBookButton;
    @FXML private TextField authorTextField;
    @FXML private TextField ISBNTextField;

    DB dataBase = new DB();
    public ObservableList<Book> books = FXCollections.observableArrayList();

    @FXML private void viewAllData(){
        ObservableList<Book> newBooks = dataBase.getAllData();
        books.removeAll(books);
        for (Book book: newBooks) {
            books.add(book);
        }
    }
    @FXML private void searchByAuthor(){
        ObservableList<Book> newBooks;
        if (authorTextField.getText().contains(" ")) {
            newBooks = dataBase.getDataByAuthor(authorTextField.getText());
        } else {
            newBooks = dataBase.getDataByLastName(authorTextField.getText());
        }
        books.removeAll(books);
        for (Book book: newBooks) {
            books.add(book);
        }
    }
    @FXML private void searchByISBN(){
        ObservableList<Book> newBooks = dataBase.getDataByISBN(ISBNTextField.getText());
        books.removeAll(books);
        for (Book book: newBooks) {
            books.add(book);
        }
    }
    @FXML private void addBook() throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("addBook.fxml"));
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setTitle("Add new book");
        newStage.setScene(newScene);
        newStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        author.setMinWidth(150);
        isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        isbn.setMinWidth(100);
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        title.setMinWidth(150);
        year.setCellValueFactory(new PropertyValueFactory<>("year"));
        year.setMinWidth(100);
        tableView.setItems(books);
    }
}
