package com.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationController {
        @FXML
        private TextField charLength;
        @FXML
        private TextField fileName;
        @FXML
        private TextField grammaticalError;
        @FXML
        private TextField lines;
        @FXML
        private TextField spellingMistake;
        @FXML
        private TextArea textArea;
        @FXML
        private TextField wordsCount;

        @FXML
        public void openSettings() {

        }

        @FXML
        public void save() throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(SpellCheckerApplication.class.getResource("SavePage.fxml"));
                Parent root = fxmlLoader.load();
                Stage infoStage = new Stage();
                infoStage.getIcons().add(new Image("/save.png"));
                infoStage.setTitle("Save");
                Scene scene = new Scene(root);
                infoStage.setScene(scene);
                infoStage.show();
        }

        @FXML
        public void uploadFile() throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(
                                SpellCheckerApplication.class.getResource("UploadFilePage.fxml"));
                Parent root = fxmlLoader.load();
                Stage infoStage = new Stage();
                infoStage.getIcons().add(new Image("/fileUpload.png"));
                infoStage.setTitle("Upload File");
                Scene scene = new Scene(root);
                infoStage.setScene(scene);
                infoStage.show();
        }

        @FXML
        public void viewInfo() throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(SpellCheckerApplication.class.getResource("InfoPage.fxml"));
                Parent root = fxmlLoader.load();
                Stage infoStage = new Stage();
                infoStage.getIcons().add(new Image("/InfoImage.png"));
                infoStage.setTitle("Info Page");
                Scene scene = new Scene(root);
                infoStage.setScene(scene);
                infoStage.show();
        }

        @FXML
        public void viewMetrics() throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(SpellCheckerApplication.class.getResource("MetricsPage.fxml"));
                Parent root = fxmlLoader.load();
                Stage infoStage = new Stage();
                infoStage.getIcons().add(new Image("/metrics.png"));
                infoStage.setTitle("Metrics Information");
                Scene scene = new Scene(root);
                infoStage.setScene(scene);
                infoStage.show();
        }
}