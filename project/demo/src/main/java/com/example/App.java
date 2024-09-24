package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

	/**
	 * App is a JavaFX application that loads the main page of the
	 * spell checker application
	 * 
	 * It extents the JavaFX Application class
	 */
public class App extends Application {
	
	/**
	 * Starts JavaFX application.
	 * Start method called after application has been initialized.
	 * 
	 * @param stage Primary stage for this app, where the application
	 * scene can be set
	 * 
	 * @throws IOException If FXML file for main page cannot be loaded
	 */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.getIcons().add(new Image("/spellcheck.png"));
        stage.setTitle("Spell Checker!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}