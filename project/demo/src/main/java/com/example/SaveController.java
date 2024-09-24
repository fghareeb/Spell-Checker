package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Optional;

/**
 * This is the controller for the save page, it contains functionality to either
 * overwrite the current file
 * or save the current file in a new location.
 */

public class SaveController {
    FileChooser fileChooser = new FileChooser();

    private File file;
    private Button h;

    @FXML

    /**
     * The overwriteCurrentFile() method allows the user to save the file by
     * overwriting in the same location.
     * It provides a warning and prompts the user to confirm their choice.
     */
    void overwriteCurrentFile() {
        String knownPath = Document.getInstance().getFilePath();
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to overwrite the current file?");

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                FileWriter writer = new FileWriter(knownPath);
                String allText = Document.getInstance().getText().toString();
                writer.write(allText);
                writer.close();

                // Show information alert
                Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                infoAlert.setTitle("Saved");
                infoAlert.setHeaderText(null);
                infoAlert.setContentText("Your file has been saved as: " + Document.getInstance().getFilePath());
                infoAlert.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
                // Handle exceptions
            }
        }
    }

    /**
     * This saveFileInNewLocation() method allows the user to save the file in a new
     * location either as .txt file
     * or a generic file type.
     */
    @FXML
    void saveFileInNewLocation() {
        configureFileChooser(fileChooser);
        File file = fileChooser.showSaveDialog(null); // You might need to replace'null' with a reference to your
        // primary stage
        if (file != null) {
            try {
                FileWriter writer = new FileWriter(file);
                String allText = Document.getInstance().getText().toString();
                writer.write(allText);
                writer.close();

                Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                infoAlert.setTitle("Saved");
                infoAlert.setHeaderText(null);
                infoAlert.setContentText("File saved successfully at: " +
                        file.getAbsolutePath());
                infoAlert.showAndWait();

            } catch (IOException e) {
                e.printStackTrace();
                // Handle exceptions
            }
        }
    }

    /**
     * The configureFileChooser() method allows the user to select the desired file
     * type.
     */
    private void configureFileChooser(FileChooser fileChooser) {
        fileChooser.setTitle("Save As");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
    }

}
