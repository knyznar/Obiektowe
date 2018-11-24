package zdjecia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;


public class Gallery extends Application {

    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.setTitle("Zdjęcia");
        String path;

        ScrollPane root = new ScrollPane();
        TilePane tilePane = new TilePane();
        tilePane.setHgap(20);
        tilePane.setVgap(20);

        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(stage);

        if(selectedDirectory == null){
            path = "/Lab7/zdjecia_kotow";
        }else{
            path = selectedDirectory.getAbsolutePath();
        }

        File folder = new File(path);
        ArrayList<File> listOfFiles = new ArrayList<>(Arrays.asList(folder.listFiles()));

        for (final File file : listOfFiles) {
            ImageView image;
            image = viewImage(file);
            tilePane.getChildren().addAll(image);
        }

        root.setContent(tilePane);
        primaryStage.setWidth(1100);
        primaryStage.setHeight(600);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ImageView viewImage(final File imageFile) {
        ImageView imageView = null;
        try {
            final Image image = new Image(new FileInputStream(imageFile),200,0,true,true);
            imageView = new ImageView(image);
            imageView.setFitWidth(200);

            imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    if(e.getButton().equals(MouseButton.PRIMARY)){
                        BorderPane borderPane = new BorderPane();
                        ImageView imageView = new ImageView();
                        Image image = new Image(new File(imageFile.toString()).toURI().toString());
                        imageView.setImage(image);
                        imageView.setFitHeight(stage.getHeight());
                        Stage newStage = new Stage();
                        newStage.setWidth(1000);
                        newStage.setHeight(1000);
                        borderPane.setCenter(imageView);
                        newStage.setTitle(imageFile.getName());
                        Scene scene = new Scene(borderPane);
                        newStage.setScene(scene);
                        newStage.show();
                    }
                }
            });
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return imageView;
    }

    public static void main(String[] args) {
        launch(args);
    }

}