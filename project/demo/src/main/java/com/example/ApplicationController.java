package com.example;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * This is the main controller for the application, it contains the
 * functionality to open
 * the settings page, info page, upload page, save page, and metrics page. It
 * also contains
 * the necessary functionality to display the uploaded text and traverse through
 * it showing each
 * error along with the suggested corrections.
 */

public class ApplicationController {
        public static int repeats;
        public static int spellNum;
        public static int gramNum;
        public static int currentSpellNum;
        public static int currentGramNum;

        private int initialIndex = 0;
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
        private Button btn;
        @FXML
        private ListView<String> listDouble;
        @FXML
        private ListView<String> listGrammar;
        @FXML
        private ListView<String> listSpelling;

        /**
         * openSettings(): This method launches the settings page.
         */
        @FXML
        public void openSettings() throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Settings.fxml"));
                Parent root = fxmlLoader.load();
                SettingsController controller = fxmlLoader.getController();
                controller.setcbo(Words.DELIMITERS);

                Stage infoStage = new Stage();
                infoStage.getIcons().add(new Image("/settings.png"));
                infoStage.setTitle("settings");
                Scene scene = new Scene(root);
                infoStage.setScene(scene);
                infoStage.show();

        }

        /**
         * save(): This method launches the save page.
         */
        @FXML
        public void save() throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("SavePage.fxml"));
                Parent root = fxmlLoader.load();
                Stage infoStage = new Stage();
                infoStage.getIcons().add(new Image("/save.png"));
                infoStage.setTitle("Save");
                Scene scene = new Scene(root);
                infoStage.setScene(scene);
                infoStage.show();
        }

        /**
         * uploadFile(): This method launches the upload page.
         */
        @FXML
        public void uploadFile() throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(
                                App.class.getResource("UploadFilePage.fxml"));
                Parent root = fxmlLoader.load();
                UploadFileController upload = fxmlLoader.getController();
                upload.initialization(this);
                Stage infoStage = new Stage();
                infoStage.getIcons().add(new Image("/fileUpload.png"));
                infoStage.setTitle("Upload File");
                Scene scene = new Scene(root);
                infoStage.setScene(scene);
                infoStage.show();
                textArea.setText("Please Upload a file to Start ....");

        }

        /**
         * viewInfo(): This method launches the info page.
         */
        @FXML
        public void viewInfo() throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("InfoPage.fxml"));
                Parent root = fxmlLoader.load();
                Stage infoStage = new Stage();
                infoStage.getIcons().add(new Image("/InfoImage.png"));
                infoStage.setTitle("Info Page");
                Scene scene = new Scene(root);
                infoStage.setScene(scene);
                infoStage.show();
        }

        /**
         * viewMetrics(): This method launches the metrics page.
         */
        @FXML
        public void viewMetrics() throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MetricsPage.fxml"));
                Parent root = fxmlLoader.load();

                Stage infoStage = new Stage();
                infoStage.getIcons().add(new Image("/metrics.png"));
                infoStage.setTitle("Metrics Information");
                Scene scene = new Scene(root);
                infoStage.setScene(scene);
                infoStage.show();
        }

        /**
         * This method takes the uploaded document, gets the metrics line count,
         * character count, and word count.
         * It creates a list with each node representing a word. Each node is checked
         * for spelling, capitalization,
         * and double words. Each error and its type is recorded. Once checking is over,
         * the metrics spelling error count
         * and grammar error count are recorded.
         */
        public void check() {
                repeats++;
                // Set the "Loading..." text immediately
                textArea.setText("Loading...");

                // Create a task for the background operation
                Task<Void> backgroundTask = new Task<Void>() {
                        @Override
                        protected Void call() {
                                // Copy the contents of your load() method here

                                String fileNameStr = Document.getInstance().getFileName();
                                int lineCount = Document.getInstance().getLineCount();
                                int charCount = Document.getInstance().getCharacterCount();
                                int wordCount = Document.getInstance().getWordsCount();

                                ObservableList<String> items = FXCollections.observableArrayList();
                                ObservableList<String> items2 = FXCollections.observableArrayList();
                                ObservableList<String> items3 = FXCollections.observableArrayList();

                                Node current = Document.getInstance().getText().getHead();
                                for (int i = 0; i < Document.getInstance().getText().getSize(); i++) {
                                        if (!current.getIsTag()) {

                                                current.setError((!Error.checkSpelling((String) current.getData(), 0))
                                                                || (!Error.checkSpelling((String) current.getData(), 1))
                                                                || !Error.checkSpelling((String) current.getData(), 2));

                                                current.setOtherError(Error.checkDouble(current));

                                                Error.checkCapitalization(current);
                                        }

                                        if (current.isError()) {
                                                items.add((String) current.getData());
                                        }
                                        if (current.isCapitalizationError()) {
                                                items2.add((String) current.getData());
                                        }
                                        if (current.isOtherError()) {
                                                items3.add((String) current.getData() + " "
                                                                + (String) current.getData());

                                        }

                                        current = current.next;

                                }

                                // Update the JavaFX components using Platform.runLater
                                Platform.runLater(() -> {
                                        fileName.setText(fileNameStr);
                                        lines.setText(String.valueOf(lineCount));
                                        charLength.setText(String.valueOf(charCount));
                                        wordsCount.setText(String.valueOf(wordCount));

                                        listSpelling.setItems(items);
                                        listGrammar.setItems(items2);
                                        listDouble.setItems(items3);
                                        textArea.setText(Document.getInstance().getText().toString().replace("[", "")
                                                        .replace("]", ""));

                                        currentSpellNum = listSpelling.getItems().size();
                                        currentGramNum = listGrammar.getItems().size() + listDouble.getItems().size();
                                        metric(currentSpellNum, currentGramNum);

                                        spellingMistake.setText(String.valueOf(currentSpellNum));
                                        grammaticalError.setText(String.valueOf(currentGramNum));

                                });

                                return null;
                        }
                };

                // Run the task in a new thread
                new Thread(backgroundTask).start();
        }

        public void metric(int spell, int gram) {
                if (repeats == 1) {
                        spellNum = spell;
                        gramNum = gram;
                }
        }

        /**
         * This method shows the list of suggestions for each error.
         */
        public void initialize() throws IOException {
                // Existing initialization code

                listSpelling.setOnMouseClicked((MouseEvent event) -> {
                        String selectedItem = listSpelling.getSelectionModel().getSelectedItem();

                        if (selectedItem != null) {
                                Node current = Document.getInstance().getText().search(selectedItem);
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("Suggestions.fxml"));
                                Parent root;
                                try {
                                        root = loader.load();
                                        SuggestionsController controller = loader.getController();
                                        controller.initialize(current, this, selectedItem, 0);
                                        if (current.getSuggestions() != null) {
                                                ArrayList<String> suggestions = current.getSuggestions();
                                                controller.setSuggestions(suggestions);
                                        }
                                        Stage stage = new Stage();
                                        stage.setScene(new Scene(root));
                                        stage.show();
                                } catch (IOException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                }

                        }
                });

                listGrammar.setOnMouseClicked((MouseEvent event) -> {
                        String selectedItemBox2 = listGrammar.getSelectionModel().getSelectedItem();

                        if (selectedItemBox2 != null) {
                                Node current = Document.getInstance().getText().search(selectedItemBox2);
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("Suggestions.fxml"));
                                Parent root;
                                try {
                                        root = loader.load();
                                        SuggestionsController controller = loader.getController();
                                        controller.initialize(current, this, selectedItemBox2, 1);
                                        if (current.getSuggestionsCap() != null) {
                                                ArrayList<String> suggestions = current.getSuggestionsCap();
                                                controller.setSuggestions(suggestions);
                                        }
                                        Stage stage = new Stage();
                                        stage.setScene(new Scene(root));
                                        stage.show();
                                } catch (IOException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                }

                        }

                });

                listDouble.setOnMouseClicked((MouseEvent event) -> {
                        String selectedItemBox3 = listDouble.getSelectionModel().getSelectedItem();
                        String substring = selectedItemBox3.contains(" ")
                                        ? selectedItemBox3.substring(0, selectedItemBox3.indexOf(' '))
                                        : selectedItemBox3;

                        if (selectedItemBox3 != null) {
                                Node current = Document.getInstance().getText().search(substring);
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("Suggestions.fxml"));
                                Parent root;
                                try {
                                        root = loader.load();
                                        SuggestionsController controller = loader.getController();
                                        controller.initialize(current, this, selectedItemBox3, 2);
                                        if (current.getSuggestionsOther() != null) {
                                                ArrayList<String> suggestions = current.getSuggestionsOther();
                                                controller.setSuggestions(suggestions);
                                        }
                                        Stage stage = new Stage();
                                        stage.setScene(new Scene(root));
                                        stage.show();
                                } catch (IOException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                }

                        }

                });
        }

        private void showAlert(String title, String content) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle(title);
                alert.setHeaderText(null);
                alert.setContentText(content);
                alert.showAndWait();
        }

}
