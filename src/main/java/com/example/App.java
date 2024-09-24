package com.example;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/example/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws FileNotFoundException {
        FileHandler fileHandler = new FileHandler();
        String filePath = "C:\\Users\\fuadg\\OneDrive\\Desktop\\LocalGroup1\\project2212\\test.txt";// change it to
                                                                                                    // yours for the
                                                                                                    // time being
        Document doc = fileHandler.loadFile(filePath, "firstFile", false);

        // Printing each word on a new line
        for (String word : doc.getText()) {
            System.out.println(word);
        }

        System.out.println(doc.getWordsCount());
        System.out.println(doc.getCharacterCount());
        System.out.println(doc.getLineCount());

        launch();

    }

}