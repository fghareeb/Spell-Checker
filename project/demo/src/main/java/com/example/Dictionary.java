package com.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;
import java.io.BufferedWriter;

	/**
	 * This class represents a dictionary that loads words from a file and stores them into a hashtable.
	 * It is also used to check if a word exists in the dictionary.
	 */

public class Dictionary {

	/**
	 * Hashtable to store dictionary words. Each word stored as a key with a Boolean value.
	 */
	
    public static Hashtable<String, Boolean> Dictionary;
    public static Hashtable<String, Boolean> userDictionary;
    public static Hashtable<String, Boolean> ignoreDictionary;

    public static String mainPath = "demo/src/main/resources/words_alpha.txt";
    public static String userPath = "demo/src/main/resources/user.txt";
    public static String ignorePath = "demo/src/main/resources/ignoreAll.txt";

    /**
     * Dictionary class constructor that intializes dictionary and loads word from the specified user file.
     */
    
    public Dictionary() {
        Dictionary = new Hashtable<String, Boolean>();
        userDictionary = new Hashtable<String, Boolean>();
        ignoreDictionary = new Hashtable<String, Boolean>();

        loadDictionaryFromFile(mainPath, 0);

        loadDictionaryFromFile(userPath, 1);

        loadDictionaryFromFile(ignorePath, 2);

    }

    /**
     * Load words from file into dictionary. Each word in file is expected to be on a seperate line.
     * @param dictionaryFilePath The file path of the dictionary file to load.
     */

    public void loadDictionaryFromFile(String dictionaryFilePath, int index) {
        try {
            // Load dictionary words from file into Hashtable
            File file = new File(dictionaryFilePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                if (index == 0) {
                    Dictionary.put(scanner.nextLine().trim().toLowerCase(), true);

                } else if (index == 1) {
                    userDictionary.put(scanner.nextLine().trim().toLowerCase(), true);

                } else if (index == 2) {
                    ignoreDictionary.put(scanner.nextLine().trim().toLowerCase(), true);

                }
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Resets the user dictionary by clearing the stored words.
     * This method overwrites user dictionary file with an empty string.
     */

    public static void restUserDic() {
        try {
            // Create a FileWriter in overwrite mode
            FileWriter fw = new FileWriter(userPath, false); // false to overwrite

            // Create a BufferedWriter
            BufferedWriter bw = new BufferedWriter(fw);

            // Write an empty string to overwrite the file
            bw.write("");

            // Close the BufferedWriter
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    /**
     * Resets ignore all dictionary by clearing all the stored words.
     */

    public static void restIgnoreAll() {
        try {
            // Create a FileWriter in overwrite mode
            FileWriter fw = new FileWriter(ignorePath, false); // false to overwrite

            // Create a BufferedWriter
            BufferedWriter bw = new BufferedWriter(fw);

            // Write an empty string to overwrite the file
            bw.write("");

            // Close the BufferedWriter
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
