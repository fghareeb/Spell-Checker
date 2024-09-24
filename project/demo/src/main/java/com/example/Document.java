package com.example;

import java.nio.file.*;
import java.io.IOException;

/**
 * This class creates an instance of the document uploaded in the program.
 * This can help manipoulate and change various aspects of it.
 */
public class Document {
    private static Document instance;

    private int charactersNum;
    private int linesNum;
    private int wordsNum;
    private LinkedList words;
    private boolean isMarkUp;
    private String fileName;
    private String filePath;

    /**
     * constructor with no args
     */
    Document() {

    }

    /**
     * constructor to initiate the class
     */
    Document(String filePath, String fileName, boolean isMarkUp) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.isMarkUp = isMarkUp;
        this.words = Words.readFileIntoWords(filePath, isMarkUp);
        setCharacterCount();
        this.linesNum = getLineCount(filePath);
        setWordsCount();
    }

    /**
     * This creates the instance to be accessed through out the program.
     */
    public static Document setInstance(String filePath, String fileName, boolean isMarkUp) {
        return (instance = new Document(filePath, fileName, isMarkUp));

    }

    /**
     * Getter method;
     * sends the instance of this document that is currently active.
     */
    public static Document getInstance() {

        return instance;
    }

    /**
     * Getter method;
     * Gives the character count
     */
    public int getCharacterCount() {
        return charactersNum;
    }

    /**
     * Getter method;
     * Gives the line count
     */
    public int getLineCount() {
        return linesNum;
    }

    /**
     * Setter method;
     * Initiates the character count
     */
    private void setCharacterCount() {

        // Read the content of the file into a String
        String content = words.toString().replace("[", "").replace("]", "");
        // Return the length of the String, which is the number of characters
        content = content.replaceAll("\\s", "");
        this.charactersNum = content.length();

    }

    /**
     * Getter method;
     * Gives the line count
     * used with files rather than instance of the document
     * it was implemented for checking reasons
     */
    private int getLineCount(String filepath) {
        try {
            // Use Files.lines() to get a Stream of lines, and count them
            return (int) Files.lines(Paths.get(filepath)).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0; // In case of an error, return 0
    }

    /**
     * Setter method;
     * Initiates the line count
     */
    public void setLine(int temp) {
        this.linesNum = temp;
    }

    /**
     * Getter method;
     * Gives the words count
     */
    public int getWordsCount() {
        return wordsNum;
    }

    /**
     * Setter method;
     * Initiates the words count
     */
    public void setWordsCount() {
        wordsNum = words.getSize();
    }

    /**
     * Getter method;
     * Returns a linkedList containing all the words in the form of Nodes
     */
    public LinkedList getText() {
        return words;
    }

    /**
     * Getter method;
     * Returns file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Updating method;
     * Set words count update
     * Set characters count after update
     */
    public void updateText(String newtext) {
        setWordsCount();
        setCharacterCount();

    }

    /**
     * Getter method;
     * Returns an answer to wether this Document should be treated as a markup
     * language or not
     */
    public boolean getisMarkup() {
        return isMarkUp;
    }

    /**
     * Setter method;
     * Decides wether this is a markup file or not based on usr input
     */
    public void setisMarkup(boolean newValue) {
        isMarkUp = newValue;
    }

    /**
     * Getter method;
     * Returns file path of where this document was uploaded from
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Setter method;
     * Saves file path of where this document was uploaded from
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Refresh method;
     * starts a new instance of a new file to switch active document from one way to
     * another.
     */
    public static void refresh(String filePath, String fileName, boolean isMarkUp) {
        instance = new Document(filePath, fileName, isMarkUp);
    }

}
