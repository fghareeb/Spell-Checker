package com.example;

import java.util.*;
import java.io.*;
import java.nio.file.*;

public class Words {

    // Define the delimiters as a regular expression in a private static variable
    private static String DELIMITERS = "\\s+|,"; // For example, whitespace characters

    public static List<String> readFileIntoWords(String filePath) {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Use the DELIMITERS variable to split the line into words
                String[] wordsArray = line.split(DELIMITERS);
                for (String word : wordsArray) {
                    if (!word.isEmpty()) {
                        words.add(word);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>(); // Return an empty list in case of error
        }
        return words;
    }

    // If you need to change the delimiters later on, you could add a setter method
    public static void setDelimiters(String delimiters) {
        DELIMITERS = delimiters;
    }

}