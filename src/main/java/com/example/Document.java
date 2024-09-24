package com.example;

import java.nio.file.*;
import java.io.IOException;
import java.util.List;

public class Document {
    private int charactersNum;
    private int linesNum;
    private int wordsNum;
    private List<String> words;
    private boolean isMarkUp;
    private String fileName;

    // No-Args constructor
    Document() {

    }

    // parameterized constructor
    Document(String filepath, String fileName, Boolean isMarkUp) {
        this.words = Words.readFileIntoWords(filepath);
        this.charactersNum = getCharacterCount(filepath);
        this.linesNum = getLineCount(filepath);
        this.wordsNum = getWordsCount();
        this.fileName = fileName;
        this.isMarkUp = isMarkUp;
    }

    public int getCharacterCount() {
        return charactersNum;
    }

    public int getLineCount() {
        return linesNum;
    }

    private int getCharacterCount(String filepath) {
        try {
            // Read the content of the file into a String
            String content = new String(Files.readAllBytes(Paths.get(filepath)));
            // Return the length of the String, which is the number of characters
            return content.length();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0; // In case of an error, return 0
    }

    // getter method to get: Line count
    private int getLineCount(String filepath) {
        try {
            // Use Files.lines() to get a Stream of lines, and count them
            return (int) Files.lines(Paths.get(filepath)).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0; // In case of an error, return 0
    }

    // getter method to get: Words count
    public int getWordsCount() {
        wordsNum = words.size();
        return wordsNum;
    }

    // getter method to get: the Text of the document
    public List<String> getText() {
        return words;
    }

    // getter method to get: File name
    public String getFileName() {
        return fileName;
    }

    // Strill need to finish
    // Setter method to update the Text in the document
    public void updateText(String newtext) {
    }

    // getter method to get: markup value 0 or 1
    public boolean getisMarkup() {
        return isMarkUp;
    }

    // setter method to set: markup value 0 or 1
    public void setisMarkup(boolean newValue) {
        isMarkUp = newValue;
    }

}
