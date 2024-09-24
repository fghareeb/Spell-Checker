package com.example;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Controller class for handling suggestion related actions in user inteface.
 * Manages interactions related to managing and offering suggestions for text
 * input.
 */
public class SuggestionsController {
    private ApplicationController app;
    private Node current;
    private int type;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnIgnoreAll;

    @FXML
    private Button btnIgnoreOnce;

    @FXML
    private Button btnManual;

    @FXML
    private Button btnSuggest;

    @FXML
    private ComboBox<String> cboSuggestion;

    @FXML
    private Label lblMistake;

    @FXML
    private TextField txtManual;

    /**
     * Handles action to add suggestion.
     * Method triggered by user action.
     * 
     * @param event Action event that triggers this method.
     */

    @FXML
    void add(ActionEvent event) {

        String filePath = Dictionary.userPath;
        String stringToAppend = lblMistake.getText();

        try {
            // Create a FileWriter in append mode
            FileWriter fw = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);

            // Append the string and a newline
            bw.write(stringToAppend);
            bw.newLine();

            // Close the BufferedWriter
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        app.check();

        Stage stage = (Stage) cboSuggestion.getScene().getWindow();

        stage.close();

    }

    /**
     * Handles action to delete a suggestion.
     * Method triggered by user action.
     * 
     * @param event Action event that triggers this method.
     */

    @FXML
    void delete(ActionEvent event) {
        current.setData("");
        current.setError(false);
        Document.getInstance().getText().remove(current);

        app.check();

        Stage stage = (Stage) cboSuggestion.getScene().getWindow();

        stage.close();
    }

    @FXML
    /**
     * Handles action to ignore all occurances of a mistake.
     * Activated by clicking ignore All button.
     * 
     * @param event Action event that triggers this method.
     */

    public void ignoreAll(ActionEvent event) {

        // Specify the file path and the string you want to append
        String filePath = Dictionary.ignorePath;
        String stringToAppend = lblMistake.getText(); // Replace with the string you want to append

        try {
            // Create a FileWriter in append mode
            FileWriter fw = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);

            // Append the string and a newline
            bw.write(stringToAppend);
            bw.newLine();

            // Close the BufferedWriter
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        app.check();

        Stage stage = (Stage) cboSuggestion.getScene().getWindow();

        stage.close();

    }

    /**
     * Handles action to ignore mistake once.
     * Triggered by clicking ignore once button.
     * 
     * @param event Action event that triggers this method.
     */

    @FXML
    void ignoreOnce(ActionEvent event) {
        if (this.type == 0) {
            current.setError(false);
        } else if (this.type == 1) {
            current.setCapitalizationError(false);
        } else if (this.type == 2) {
            current.setOtherError(false);
        }

        Stage stage = (Stage) cboSuggestion.getScene().getWindow();
        app.check();
        stage.close();

    }

    /**
     * Handles manual input of a suggestion.
     * Activated by user action.
     * 
     * @param event Action event that triggers this method.
     */

    @FXML
    void manual(ActionEvent event) {
        current.setData(txtManual.getText());
        app.check();

        Stage stage = (Stage) cboSuggestion.getScene().getWindow();

        stage.close();
    }

    /**
     * Handles action to suggest corrections.
     * Triggered by suggest button which suggests corrections for mistakes.
     * 
     * @param event Action event that triggers this method.
     */

    @FXML
    void suggest(ActionEvent event) {

        String selectedItem = String.valueOf(cboSuggestion.getValue());
        if (selectedItem.equals("Remove Repeat")) {
            this.delete(event);
            return;
        }

        if (selectedItem.startsWith("Split into ")) {
            int startIndex = "Split into ".length();
            int endIndex = selectedItem.indexOf(" ", startIndex); // Find the index of the next space

            if (endIndex == -1) { // In case there's no space character
                System.out.println("hello del");

                this.delete(event);
                return; // Return after delete if no space found
            }

            String extractedSubstring = selectedItem.substring(startIndex, endIndex);

            // Handling the rest of the string after the extracted substring
            startIndex = endIndex + 1; // Start from the next character after the space
            String secondHalf = selectedItem.substring(startIndex);

            // Rest of your logic

            char punct = ' ';
            boolean isLast = false;
            boolean newLine = false;

            if (current.getIsLast()) {
                punct = current.getPunct();
                isLast = current.getIsLast();
                current.setIsLast(false);
            }
            if (current.getLine()) {
                newLine = current.getLine();
                current.setLine(false);
            }

            current.setData(extractedSubstring);

            Node next = new Node(secondHalf, false, false, null, punct, isLast, newLine, false, false, null, null);
            Document.getInstance().getText().addAfter(current, next); // Adding the new node after the current node
            current.setError(false);
            app.check();

            Stage stage = (Stage) cboSuggestion.getScene().getWindow();

            stage.close();
            return;
        }

        if (selectedItem != null && !selectedItem.equals("null")) {

            current.setData(selectedItem);
            if (this.type == 0) {
                current.setError(false);
            } else if (this.type == 1) {
                current.setCapitalizationError(false);
            } else if (this.type == 2) {
                current.setOtherError(false);
            }
        } else {
            // Handle the case where nothing is selected
            System.out.println("No item is selected.");
        }
        app.check();

        Stage stage = (Stage) cboSuggestion.getScene().getWindow();

        stage.close();

    }

    /**
     * Sets the suggestions in the ComboBox.
     *
     * @param suggestions An ArrayList of strings representing the suggestions to be
     *                    displayed in the ComboBox.
     */
    public void setSuggestions(ArrayList<String> suggestions) {
        ObservableList<String> observableSuggestions = FXCollections.observableArrayList(suggestions);
        cboSuggestion.setItems(observableSuggestions);
    }

    /**
     * Initializes the UI components based on the provided parameters.
     *
     * @param current The current Node to be displayed.
     * @param app     The ApplicationController for managing application logic.
     * @param mistake The mistake description to be displayed.
     * @param type    An integer representing the type of initialization.
     *                (e.g., to distinguish different initialization scenarios)
     */
    public void initialize(Node current, ApplicationController app, String mistake, int type) {
        this.current = current;
        lblMistake.setText(mistake);
        this.type = type;
        this.app = app;
    }

}
