package com.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Hashtable;

public class DictionaryTest {

    private Dictionary dictionary;

    @TempDir
    Path tempDir;

    @BeforeEach
    public void setUp() {

        Dictionary.Dictionary = new Hashtable<>();
        Dictionary.userDictionary = new Hashtable<>();
        Dictionary.ignoreDictionary = new Hashtable<>();

        // Set the dictionary paths to use the temp directory
        Dictionary.mainPath = tempDir.resolve("words_alpha.txt").toString();
        Dictionary.userPath = tempDir.resolve("user.txt").toString();
        Dictionary.ignorePath = tempDir.resolve("ignoreAll.txt").toString();

        dictionary = new Dictionary();
    }

    @Test
    public void testLoadMainDictionaryFromFile() {
        // Test loading main dictionary
        String mainTestWord = "mainword";
        createTestFile(Dictionary.mainPath, mainTestWord);
        dictionary.loadDictionaryFromFile(Dictionary.mainPath, 0);
        assertTrue(Dictionary.Dictionary.containsKey(mainTestWord));

    }

    @Test
    public void testLoadUserDictionaryFromFile() {

        // Test loading user dictionary
        String userTestWord = "userword";
        createTestFile(Dictionary.userPath, userTestWord);
        dictionary.loadDictionaryFromFile(Dictionary.userPath, 1);
        assertTrue(Dictionary.userDictionary.containsKey(userTestWord));

    }

    @Test
    public void testLoadIgnoreDictionaryFromFile() {
        // Test loading ignore dictionary
        String ignoreTestWord = "ignoreword";
        createTestFile(Dictionary.ignorePath, ignoreTestWord);
        dictionary.loadDictionaryFromFile(Dictionary.ignorePath, 2);
        assertTrue(Dictionary.ignoreDictionary.containsKey(ignoreTestWord));
    }

    private void createTestFile(String filePath, String content) {
        try {
            Files.writeString(Path.of(filePath), content);
        } catch (IOException e) {
            fail("Failed to create test file", e);
        }
    }

}
