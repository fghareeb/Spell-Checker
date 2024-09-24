package com.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This is the controller for the information page, it contains the functionality to open
 * the about page, help page, and how to page..
 */

public class InformationController {

    /**
    * aboutPage(): This method launches the about page.
    */
    @FXML
    void aboutPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AboutPage.fxml"));
        Parent root = fxmlLoader.load();
        Stage infoStage = new Stage();
        infoStage.getIcons().add(new Image("/InfoImage.png"));
        infoStage.setTitle("About Page");
        Scene scene = new Scene(root);
        infoStage.setScene(scene);
        infoStage.show();
    }

    /**
    * helpPage(): This method launches the help page.
    */
    @FXML
    void helpPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("HelpPage.fxml"));
        Parent root = fxmlLoader.load();
        Stage infoStage = new Stage();
        infoStage.getIcons().add(new Image("/InfoImage.png"));
        infoStage.setTitle("Help Page");
        Scene scene = new Scene(root);
        infoStage.setScene(scene);
        infoStage.show();
    }

    /**
    * howToPage(): This method launches the how to page.
    */
    @FXML
    void howToPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("HowToUse.fxml"));
        Parent root = fxmlLoader.load();
        Stage infoStage = new Stage();
        infoStage.getIcons().add(new Image("/InfoImage.png"));
        infoStage.setTitle("How to Use");
        Scene scene = new Scene(root);
        infoStage.setScene(scene);
        infoStage.show();
    }
}
