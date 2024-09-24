// package com.example;

// import org.junit.jupiter.api.*;
// import static org.junit.jupiter.api.Assertions.*;

// import java.nio.file.Files;
// import java.nio.file.Path;

// public class DocumentTest {

// private static final Path TEST_DIRECTORY =
// Path.of(System.getProperty("java.io.tmpdir"));

// private static Path testFile;

// private static final String contentTest = "Hello, world!\nThis is a test.\n";

// private static final String fileNameTest = "testDocument.txt";

// private static final boolean test = false;

// @BeforeAll
// static void setup() throws Exception {
// // Create a test file with some content
// testFile = Files.createTempFile(TEST_DIRECTORY, "test", ".txt");

// Files.writeString(testFile, contentTest);
// }

// @AfterAll
// static void tearDown() throws Exception {
// // Delete the test file
// Files.deleteIfExists(testFile);
// }

// @Test
// void testDocumentCreation() {
// // Test the constructor and file reading
// Document doc = new Document(testFile.toString(), fileNameTest, test);

// // Assert that the correct number of lines was read
// assertEquals(2, doc.getLineCount());

// // Assert that the correct number of words was read
// assertEquals(6, doc.getWordsCount());

// // Assert that the correct number of characters was read
// assertEquals(22, doc.getCharacterCount());
// }

// @Test
// void testFilePathAndName() {
// // Test setting and getting the file path and name
// Document doc = new Document(testFile.toString(), fileNameTest, test);

// assertEquals(testFile.toString(), doc.getFilePath());

// assertEquals(fileNameTest, doc.getFileName());
// }

// @Test
// void testDocumentSingleton() {
// // Test the singleton instance
// Document doc = Document.setInstance(testFile.toString(), fileNameTest, test);

// Document sameDoc = Document.getInstance();

// assertSame(doc, sameDoc);
// }

// @Test
// void testCharacterCount() {
// // Test character count
// Document doc = new Document(testFile.toString(), fileNameTest, test);

// assertEquals(22, doc.getCharacterCount());
// }

// @Test
// void testMarkupFlag() {

// Document doc = new Document(testFile.toString(), fileNameTest, test);

// assertFalse(doc.getisMarkup());

// // Change the markup flag
// doc.setisMarkup(true);

// assertTrue(doc.getisMarkup());
// }

// @Test
// void testRefreshingDocument() {

// Document doc = Document.setInstance(testFile.toString(), fileNameTest, test);

// Document.refresh(testFile.toString(), fileNameTest, test);

// Document refreshedDoc = Document.getInstance();

// assertNotSame(doc, refreshedDoc);
// }
// }