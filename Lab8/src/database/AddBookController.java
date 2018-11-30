package database;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddBookController {
    @FXML private TextField author;
    @FXML private TextField isbn;
    @FXML private TextField title;
    @FXML private TextField year;
    @FXML private Button addBook;
    DB dataBase = new DB();

    @FXML public void addBook(){
        dataBase.connect();
        dataBase.addBook(new Book(isbn.getText(), title.getText(), author.getText(),Integer.parseInt(year.getText())));
    }

}
