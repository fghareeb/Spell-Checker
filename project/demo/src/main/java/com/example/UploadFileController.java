package com.example;

import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

/**
 * This is the controller for the upload page, it contains functionality to
 * upload a document and tick a checkbox
 * that determines whether the text document is in a markup language.
 */

public class UploadFileController {
    private ApplicationController app;
    FileChooser fileChooser = new FileChooser();
    private File file;

    @FXML
    private CheckBox markup;
    // private FileHandler fileHandler = new FileHandler();

    /**
     * Handles the action when the "Done" button is clicked.
     * Resets the dictionary's ignore list, sets the document instance, and closes
     * the stage.
     */
    @FXML
    void done() {
        Dictionary.restIgnoreAll();

        if (file != null) {

            Document.setInstance(this.file.getPath(), file.getName(), markup.isSelected());
            System.out.println(Document.getInstance().getLineCount());
        }
        Stage stage = (Stage) markup.getScene().getWindow();

        stage.close();
        app.check();

    }

    @FXML
    void markupListener() {

    }

    /**
     * Opens a FileChooser dialog to select and set the file.
     */
    @FXML
    void uploadFile() {
        file = fileChooser.showOpenDialog(new Stage());

    }

    /**
     * Initializes the controller with the main application controller.
     *
     * @param app The ApplicationController for managing application logic.
     */
    public void initialization(ApplicationController app) {
        this.app = app;
    }

}