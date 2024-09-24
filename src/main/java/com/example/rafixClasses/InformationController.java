package com.example.rafixClasses;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class InformationController {

    @FXML
    void aboutPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SpellCheckerApplication.class.getResource("AboutPage.fxml"));
        Parent root = fxmlLoader.load();
        Stage infoStage = new Stage();
        infoStage.getIcons().add(new Image("/InfoImage.png"));
        infoStage.setTitle("About Page");
        Scene scene = new Scene(root);
        infoStage.setScene(scene);
        infoStage.show();
    }

    @FXML
    void helpPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SpellCheckerApplication.class.getResource("HelpPage.fxml"));
        Parent root = fxmlLoader.load();
        Stage infoStage = new Stage();
        infoStage.getIcons().add(new Image("/InfoImage.png"));
        infoStage.setTitle("Help Page");
        Scene scene = new Scene(root);
        infoStage.setScene(scene);
        infoStage.show();
    }

    @FXML
    void howToPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SpellCheckerApplication.class.getResource("HowToUse.fxml"));
        Parent root = fxmlLoader.load();
        Stage infoStage = new Stage();
        infoStage.getIcons().add(new Image("/InfoImage.png"));
        infoStage.setTitle("How to Use");
        Scene scene = new Scene(root);
        infoStage.setScene(scene);
        infoStage.show();
    }
}
