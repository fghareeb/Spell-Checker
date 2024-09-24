package com.example;

import java.util.ArrayList;

/**
 * Error class provides methods to check and correct spelling, detect
 * double words, and check for capitlzation in a list of words.
 * 
 */
public class Error {

    /**
     * Checks spelling of the given word
     * If the word exists in the dictionary, it returns true if correct,
     * else suggests corrections.
     * 
     * @param word The word to be checked
     * 
     * @return true if correct, suggestions otherwise
     */
    public static boolean checkSpelling(String word, int index) {
        String pl = word;
        word = word.toLowerCase();
        new Dictionary();

        if (Dictionary.Dictionary != null && Dictionary.Dictionary.containsKey(word)) { // this means our dictionary
                                                                                        // contained the word
            // System.out.println(word + ": Correct Spelling"); // only for now to see if
            // the code works
            return true;
        } else if (Dictionary.userDictionary != null && Dictionary.userDictionary.containsKey(word)) { // this means our
                                                                                                       // dictionary
            // contained the word
            // System.out.println(word + ": Correct Spelling"); // only for now to see if
            // the code works
            return true;
        } else if (Dictionary.ignoreDictionary != null && Dictionary.ignoreDictionary.containsKey(word)) { // this means
                                                                                                           // our
                                                                                                           // dictionary
            // contained the word
            // System.out.println(word + ": Correct Spelling"); // only for now to see if
            // the code works
            return true;
        } else { // this means the dictionary did not contain our wanted word
            suggestCorrections(pl);
            return false;
        }
    }

    /**
     * Suggests corrections for misspelled word.
     * 
     * @param word Word to be checked
     * 
     * @return true if correction found, false otherwise
     */
    public static boolean suggestCorrections(String word) {
        try {
            Node current = Document.getInstance().getText().search(word);

            word = word.toLowerCase();
            // System.out.println(word);

            if (current == null) {
                // System.out.println("No suggestions found (node is null)");
                return false;
            }

            // System.out.println(word + ": Incorrect Spelling"); // only for now to see if
            // the code works
            // we create an arraylist which collects all the corrections
            // from the four functions
            ArrayList<String> suggestions = new ArrayList<>();
            ArrayList<String> temp1 = correctSpellingSubstitution(word);

            for (int i = 0; i < temp1.size(); i++)
                suggestions.add(temp1.get(i));

            String s = correctSpellingWithOmission(word);
            if (s != null)
                suggestions.add(s);
            ArrayList<String> temp = correctSpellingWithInsertion(word);
            for (int i = 0; i < temp.size(); i++)
                suggestions.add(temp.get(i));
            s = correctSpellingWithReversal(word);
            if (s != null)
                suggestions.add(s);

            if (suggestions.isEmpty()) {
                s = splitIntoWordsIfValid(word);
                if (s != null)
                    suggestions.add(s);
            }

            // we print our corrections if they exist and return true
            if (!suggestions.isEmpty()) {
                current.setSuggestions(suggestions);
                for (int i = 0; i < suggestions.size(); i++) {
                    // System.out.print(" " + suggestions.get(i) + ",");
                }
                // System.out.println(" " + suggestions.get(suggestions.size() - 1));
                return true;
            }
            // this means no corrections were found
            else
                return false;
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * Corrects spelling of a word by substituing letters
     * Tries all possible substituions to find a word in the dictionary
     * 
     * @param word Word to be corrected
     */
    static ArrayList<String> correctSpellingSubstitution(String word) {
        // this loop runs such that it checks every permutation by replacing
        // characters in the string by every character from a to z
        ArrayList<String> corrections = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j < 26; j++) {
                String temp = word.substring(0, i) + ((char) ('a' + j)) + word.substring(i + 1);
                if (Dictionary.Dictionary.containsKey(temp)) {
                    corrections.add(temp);
                }
            }
        }
        // the code reaching this part means no suggestion was found
        return corrections;
    }

    /**
     * Corrects the spelling of the word by omission
     * 
     * @param word Word to be corrected
     */
    static String correctSpellingWithOmission(String word) {
        // this loop runs such that it iterates over every char in the string
        // and omits it one by one
        for (int i = 0; i < word.length(); i++) {
            String temp = word.substring(0, i) + word.substring(i + 1);
            if (Dictionary.Dictionary.containsKey(temp))
                return temp;
        }
        return null;
    }

    /**
     * Corrects spelling of word by insertion
     * 
     * @param word word to be corrected
     */
    static ArrayList<String> correctSpellingWithInsertion(String word) {
        ArrayList<String> corrections = new ArrayList<>();
        // we have made such a nested loop that it inserts every alphabet
        // in spots 0 to k+i to check if it is in the dictionary
        for (int i = 0; i <= word.length(); i++) {
            for (int j = 0; j < 26; j++) {
                String temp = word.substring(0, i) + ((char) ('a' + j)) + word.substring(i);
                if (Dictionary.Dictionary.containsKey(temp))
                    corrections.add(temp);
            }
        }
        // if no corrections are found, the arraylist will be empty
        return corrections;
    }

    /**
     * Corrects spelling of a word by reversal
     * 
     * @param word Word to be checked
     */
    static String correctSpellingWithReversal(String word) {
        // this for loop creates every permutation of strings produced
        // by swapping pairs in the given word and checks if they exist in dictionary
        for (int i = 0; i < word.length() - 1; i++) {
            String temp = word.substring(0, i) + word.charAt(i + 1) + word.charAt(i) + word.substring(i + 2);
            if (Dictionary.Dictionary.containsKey(temp))
                return temp;
        }
        // this means no swapped pair gave a word in the dictionary
        return null;
    }

    /**
     * Corrects word by splitting into valid words
     * 
     * @param word Word to be split and corrected
     */

    public static String splitIntoWordsIfValid(String word) {
        for (int i = 1; i < word.length(); i++) {
            String firstPart = word.substring(0, i);
            String secondPart = word.substring(i);

            // Check if both parts are valid words
            if (isValidWord(firstPart) && isValidWord(secondPart)) {
                return ("Split into " + firstPart + " " + secondPart);

            }
        }
        return "";
    }

    /**
     * Checks if passed in word is valid
     * 
     * @param word Word to be checked
     */
    private static boolean isValidWord(String word) {

        return Dictionary.Dictionary.containsKey(word.toLowerCase()) ||
                Dictionary.userDictionary.containsKey(word.toLowerCase()) ||
                Dictionary.ignoreDictionary.containsKey(word.toLowerCase());
    }

    /**
     * Checks for double words
     * 
     * @param current checks for double words using node
     */
    public static boolean checkDouble(Node current) {
        if ((current != null) && (current.next != null)) {

            if ((((String) current.getData()).toLowerCase()).equals(((String) current.next.getData()).toLowerCase())) {

                ArrayList<String> suggestions = new ArrayList<>();
                suggestions.add("Remove Repeat");
                current.setSuggestionsOther(suggestions);

                return true;
            }
            return false;

        }

        return false;
    }

    /**
     * Checks for valid capitalization
     * 
     * @param current node used to check for valid capitalization
     */
    public static void checkCapitalization(Node current) {
        ArrayList<String> suggestions = new ArrayList<>();
        if ((current.prev == null || current.prev.getIsLast())
                && !isFirstLetterCapitalized((String) current.getData())) {
            current.setCapitalizationError(true);
            String temp = (String) current.getData();
            temp = Character.toUpperCase(temp.charAt(0)) + temp.substring(1);
            suggestions.add(temp);
            current.setSuggestionsCap(suggestions);
        } else if (!current.prev.getIsLast()) {
            for (int i = 0; i < ((String) current.getData()).length(); i++) {
                int index = 0;
                if (Character.isUpperCase(((String) current.getData()).charAt(i))) {
                    current.setCapitalizationError(true);
                    String temp = (String) current.getData();
                    temp = temp.toLowerCase();
                    suggestions.add(temp);

                    temp = temp.toUpperCase();
                    suggestions.add(temp);
                    if (current.getData().equals(temp)) {
                        current.setCapitalizationError(false);

                        return;
                    }
                    if (index == 0) {
                        current.setSuggestionsCap(suggestions);
                        index++;

                    }
                    return;
                }
            }
        } else if ((current.prev == null
                || current.prev.getIsLast() && isFirstLetterCapitalized((String) current.getData()))) {
            for (int i = 1; i < ((String) current.getData()).length(); i++) {
                if (Character.isUpperCase(((String) current.getData()).charAt(i))) {
                    current.setCapitalizationError(true);
                    String temp = (String) current.getData();
                    temp = temp.toLowerCase();
                    temp = Character.toUpperCase(temp.charAt(0)) + temp.substring(1);
                    suggestions.add(temp);
                    current.setSuggestionsCap(suggestions);
                }
            }
        }

    }

    /**
     * Checks to see if first word is capitalized
     * 
     * @param word word to be checked
     */
    public static boolean isFirstLetterCapitalized(String word) {
        return !word.isEmpty() && Character.isUpperCase(word.charAt(0));
    }

}
