// package com.example;

// import org.junit.jupiter.api.*;
// import static org.junit.jupiter.api.Assertions.*;
// import java.nio.file.*;
// import java.io.*;

// public class WordsTest {

// private static final String TEST_FILE_PATH = "testFile.txt";
// private static final String CONFIG_FILE_PATH =
// "demo/src/main/resources/configuration.txt";

// @BeforeEach
// void setUp() throws IOException {
// // Setup code to create a test file
// Files.writeString(Paths.get(TEST_FILE_PATH), "Hello world! This is a test.");
// }

// @AfterEach
// void tearDown() throws IOException {
// // Code to delete the test file after each test
// Files.deleteIfExists(Paths.get(TEST_FILE_PATH));
// Files.deleteIfExists(Paths.get(CONFIG_FILE_PATH));
// }

// @Test
// void testSetAndDeleteDelimiters() {
// Words.setDelimiters(",");
// assertTrue(Words.DELIMITERS.contains(","));
// Words.deleteDelimiter(",");
// assertFalse(Words.DELIMITERS.contains(","));
// }

// @Test
// void testWriteAndReadStringToFile() {
// String testContent = "Test content";
// Words.writeStringToFile(testContent);
// assertEquals(testContent, Words.readStringFromFile());
// }

// @Test
// @Test
// void testReadFileIntoWords() {
// // ... previous setup code ...

// LinkedList<Node> words = Words.readFileIntoWords(TEST_FILE_PATH, false);
// assertNotNull(words);
// assertFalse(words.isEmpty());
// assertEquals("Hello", words.getHead().getData().getWord()); // Adjust based
// on your Node class implementation
// }

// // Additional tests can be added to cover more scenarios and edge cases.
// }
