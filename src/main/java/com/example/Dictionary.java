package com.example;

        import java.io.File;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.Hashtable;
        import java.util.Scanner;


public class Dictionary {

    private static Hashtable<String, Boolean> dictionary;

    public Dictionary(String dictionaryFilePath) {
        dictionary = new Hashtable<>();
        loadDictionaryFromFile(dictionaryFilePath);
    }

    private void loadDictionaryFromFile(String dictionaryFilePath) {
        try {
            // Load dictionary words from file into Hashtable
            File file = new File(dictionaryFilePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                dictionary.put(scanner.nextLine().trim().toLowerCase(), true);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean containsWord(String word) {
        word = word.toLowerCase();
        if(dictionary.containsKey(word)) {
            System.out.println(word+ ": Correct Spelling");
            return true;
        }
        else{
            suggestCorrections(word);
            return false;
        }
    }

    public static boolean suggestCorrections(String word) {
        word = word.toLowerCase();

        // we create an arraylist which collects all the corrections
        ArrayList<String> suggestions = new ArrayList<>();
        return true; //returns true just for now
    }
}
