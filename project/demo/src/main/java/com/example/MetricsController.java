package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * This is the controller for the settings page, it displays the fields for the metrics; the number of characters, words,
 * spelling mistakes, grammatical mistakes, spelling corrections, and grammatical corrections.
 */

public class MetricsController {

    @FXML
    private TextField text1;

    @FXML
    private TextField text2;

    @FXML
    private TextField text3;

    @FXML
    void correctionApplied() {

    }

    @FXML
    void docInfo() {
    }

    @FXML
    void errorsDetected() {

    }
    /**
     * This method sets the metrics.
     */
    public void done(ActionEvent event) {
        text1.setText("#Characters: " + Document.getInstance().getCharacterCount() + " #Lines: "
                + Document.getInstance().getLineCount() + " #Words: " + Document.getInstance().getWordsCount());
        text2.setText("Spelling Mistakes: "
                + String.valueOf((ApplicationController.spellNum))
                + " Grammatic Mistakes: "
                + String.valueOf((ApplicationController.gramNum)));
        text3.setText("Spelling corrections: "
                + String.valueOf((ApplicationController.spellNum) - ApplicationController.currentSpellNum)
                + " Grammatic corrections: "
                + String.valueOf((ApplicationController.gramNum) - ApplicationController.currentGramNum));
    }

}
