package com.example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ErrorTest {
    @Test
    void testCheckSpellingCorrectWord() {
        // Assuming the word "test" exists in the dictionary
        assertTrue(Error.checkSpelling("test", 0));
//
    }
    @Test
    void testCheckSpellingIncorrectWord() {
        // Assuming the word "xyz" doesn't exist in the dictionary
        assertFalse(Error.checkSpelling("xyz", 0));
    }

    @Test
    void testCorrectSpellingSubstitution() {
        List<String> expected = new ArrayList<>(List.of("cafe", "rafe", "safe", "nife", "nace", "nake", "nale", "name", "nane", "nape", "nare", "nate", "nave", "naze"));
        List<String> result = new ArrayList<>(Error.correctSpellingSubstitution("nafe"));
        // Sort both lists before comparison to ensure they are in the same order
        expected.sort(String::compareTo);
        result.sort(String::compareTo);
        assertEquals(expected, result);
    }
    
    @Test
    void testIsFirstLetterCapitalized() {
        assertTrue(Error.isFirstLetterCapitalized("Hello"));
        assertFalse(Error.isFirstLetterCapitalized("world"));
        assertFalse(Error.isFirstLetterCapitalized(""));
    } 



    @Test
    void testCorrectSpellingWithOmission() {
        assertEquals("you", Error.correctSpellingWithOmission("youu"));
    }

    @Test
    void testCorrectSpellingWithInsertion() {
        List<String> expected = new ArrayList<>(List.of("hwy", "rwy", "swy", "way", "wey", "why", "woy", "wry", "wye", "wyn"));
        List<String> result = new ArrayList<>(Error.correctSpellingWithInsertion("wy"));
        
        expected.sort(String::compareTo);
        result.sort(String::compareTo);
        assertEquals(expected, result);
    }
    

    @Test
    void testCorrectSpellingWithReversal() {
        assertEquals("stop", Error.correctSpellingWithReversal("tsop"));
    }
}
