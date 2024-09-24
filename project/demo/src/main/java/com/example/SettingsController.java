package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Class for managing sttings related actions in user interface.
 * Handes interactions related to dictionary settings, which alows users to add
 * or remove
 * words and reset dictionaries.
 */

public class SettingsController {

    @FXML
    private CheckBox applicationDictionaryCheckBox;

    @FXML
    private ComboBox<String> combo;

    @FXML
    private TextField delimitersTxtField;

    @FXML
    private CheckBox userDictionaryCheckBox;

    @FXML
    private TextArea userWordField;

    /**
     * Adds a word to the delimiter List.
     * Method called in response to user action.
     * 
     * @param event Event triggered by action.
     */
    @FXML
    void addDelimiters(ActionEvent event) {
        Words.setDelimiters(delimitersTxtField.getText());
        delimitersTxtField.setText("Re-upload file to see effect");
        Words.writeStringToFile(Words.DELIMITERS);

    }

    /**
     * Adds a word to the comboBox.
     * 
     * @param original words to populate with
     */
    void setcbo(String original) {
        String[] values = original.split("\\|");
        List<String> valuesList = Arrays.asList(values);

        ObservableList<String> observableSuggestions = FXCollections.observableArrayList(valuesList);
        combo.setItems(observableSuggestions);
        delimitersTxtField.setText("Re-upload file to see effect");

    }

    /**
     * Adds a word to the dictionary.
     * Method called in response to user action.
     * 
     * @param event Event triggered by action.
     */

    @FXML
    void addToDictionary(ActionEvent event) {

        String filePath = Dictionary.userPath;
        String stringToAppend = userWordField.getText();

        try {
            // Create a FileWriter in append mode
            FileWriter fw = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);

            // Append the string and a newline
            bw.write(stringToAppend);
            bw.newLine();

            // Close the BufferedWriter
            bw.close();
            userWordField.setText("Done! please update the document to see the changes.");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Resets changes done to delimiters
     * 
     * @param event Event triggered by action.
     */
    @FXML
    void restDel(ActionEvent event) {
        Words.restDelimiters();
        delimitersTxtField.setText("Re-upload file to see effect");
        Words.writeStringToFile(Words.DELIMITERS);

    }

    /**
     * Applies changes done which dictionaries are selected
     * 
     * @param event Event triggered by action.
     */
    @FXML
    void done(ActionEvent event) {
        if (!applicationDictionaryCheckBox.isSelected()) {
            Dictionary.mainPath = "demo/src/main/resources/ignoreAll.txt";
        } else {
            Dictionary.mainPath = "demo/src/main/resources/words_alpha.txt";
        }

        if (!userDictionaryCheckBox.isSelected()) {
            Dictionary.userPath = "demo/src/main/resources/ignoreAll.txt";
        } else {
            Dictionary.userPath = "demo/src/main/resources/user.txt";

        }

    }

    /**
     * Removes a Delimiter
     * 
     * @param event Event triggered by action.
     */
    @FXML
    void removeDelimiters(ActionEvent event) {
        Words.DELIMITERS = Words.DELIMITERS.replaceAll((String) combo.getValue(), "");
        Words.writeStringToFile(Words.DELIMITERS);

    }

    /**
     * Removes word from the dictionary.
     * Method called when a user performs an action to remove a word
     * from the dictionary.
     * 
     * @param event Event triggered by action.
     */

    @FXML
    void removeFromDictionary(ActionEvent event) {
        String filePath = Dictionary.userPath;
        String stringToRemove = userWordField.getText();

        try {
            // Read the existing content of the file
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                // Check if the line contains the string to be removed
                if (!line.equals(stringToRemove)) {
                    // If not, append it to the content
                    content.append(line).append(System.lineSeparator());
                }
            }

            // Close the BufferedReader
            br.close();

            // Write the updated content back to the file
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content.toString());

            // Close the BufferedWriter
            bw.close();
            userWordField.setText("Done! please update the document to see the changes.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Resets dictionary to default state.
     * Called in response to user action for ressetting dictionary.
     * 
     * @param event Evenet triggered by action.
     */

    @FXML
    void resetDictionary(ActionEvent event) {
        Dictionary.restUserDic();
        userWordField.setText("Done! please update the document to see the changes.");

    }

}
