package com.example;

import java.util.*;
import java.io.*;
import java.nio.file.*;

/**
 * Words class provides functionality for reading text from a file.
 * 
 */
public class Words {

    public static String DELIMITERS = "\\s+"; // Delimiters for splitting text into words

    /**
     * Sets new delimiters for splitting text into words.
     *
     * @param newDelimiters the new delimiters to be appended
     */
    public static void setDelimiters(String newDelimiters) {
        DELIMITERS += "|" + newDelimiters;
    }

    /**
     * Writes the specified string content to the configuration file.
     *
     * @param content The string content to be written to the configuration file.
     */
    public static void writeStringToFile(String content) {
        try {
            Path path = Paths.get("demo/src/main/resources/configuration.txt");
            Files.writeString(path, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readStringFromFile() {
        try {
            Path path = Paths.get("demo/src/main/resources/configuration.txt");
            return Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Deletes a specific delimiter from the current delimiters.
     *
     * @param delimiterToRemove the delimiter to be removed
     */
    public static void deleteDelimiter(String delimiterToRemove) {
        // Use regex to remove the specified delimiter from the current delimiters
        DELIMITERS = DELIMITERS.replaceFirst(delimiterToRemove, "");
    }

    public static void restDelimiters() {
        // Use regex to remove the specified delimiter from the current delimiters
        DELIMITERS = "\\s+";
    }

    /**
     * Reads file from file path and processes it into a list of words
     * 
     * @param filePath path of file to be read
     * @param markup   indicates whether file is a markup or not
     * @return Linkedlist of node objects, each reresenting a word
     */
    public static LinkedList<String> readFileIntoWords(String filePath, boolean markup) {
        String del = readStringFromFile();
        DELIMITERS = del;
        LinkedList<String> words = new LinkedList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] wordsArray = line.split(DELIMITERS);
                for (int i = 0; i < wordsArray.length; i++) {
                    String word = wordsArray[i];
                    if (!word.isEmpty()) {
                        boolean isTag = false;
                        if (markup) {
                            isTag = word.startsWith("<");

                        }
                        char punct = 'x'; // default punctuation character
                        boolean error = false;
                        ArrayList<String> suggestions = null; // default suggestions
                        ArrayList<String> suggestionsCap = null; // default suggestions

                        ArrayList<String> suggestionsOther = null; // default suggestions

                        boolean isLast = false;
                        boolean capitalizationError = false;
                        boolean otherError = false;
                        boolean newLine = (i == wordsArray.length - 1); // Set newLine to true if this is the last word
                                                                        // in the line

                        // Check for punctuation at the end of the word
                        if (word.endsWith(".") || word.endsWith("?") || word.endsWith("!")) {
                            isLast = true;
                            punct = word.charAt(word.length() - 1);
                            word = word.substring(0, word.length() - 1); // Remove the punctuation from the word
                        }

                        Node node = new Node(word, isTag, error, suggestions, punct, isLast, newLine,
                                capitalizationError, otherError, suggestionsCap, suggestionsOther);
                        words.add(node);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new LinkedList<>(); // Return an empty list in case of error
        }
        return words;
    }
}