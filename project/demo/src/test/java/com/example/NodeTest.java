package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class NodeTest {

    @Test
    public void testNodeCreationAndGetters() {
        // Setup data for the Node
        String data = "test";
        
        boolean isTag = true;
        
        boolean error = true;
        
        ArrayList<String> suggestions = new ArrayList<>();
        
        suggestions.add("suggestion");
        
        char punct = '.';
        boolean isLast = true;
        
        boolean newLine = true;
        
        boolean capitalizationError = true;
        
        boolean otherError = true;
        ArrayList<String> suggestionsCap = new ArrayList<>();
        
        suggestionsCap.add("cap");
        
        ArrayList<String> suggestionsOther = new ArrayList<>();
        
        suggestionsOther.add("other");

        // Create a Node instance
        Node<String> node = new Node<>(data, isTag, error, suggestions, punct, isLast, newLine, capitalizationError, otherError, suggestionsCap, suggestionsOther);

        // Assert that getters return the correct values
        
        assertEquals(data, node.getData());
        assertEquals(isTag, node.getIsTag());
        assertEquals(error, node.isError());
        assertEquals(suggestions, node.getSuggestions());
        assertEquals(punct, node.getPunct());
        assertEquals(isLast, node.getIsLast());
        assertEquals(newLine, node.getLine());
        assertEquals(capitalizationError, node.isCapitalizationError());
        assertEquals(otherError, node.isOtherError());
    }

    @Test
    public void testSetters() {
        // Create a Node instance with initial values
        Node<String> node = new Node<>("initial", false, false, new ArrayList<>(), ' ', false, false, false, false, new ArrayList<>(), new ArrayList<>());

        // Set new values
        String newData = "newData";
        node.setData(newData);
        node.setError(true);
        node.setIsLast(true);
        node.setLine(true);
        node.setPunct('!');
        node.setCapitalizationError(true);
        node.setOtherError(true);

        ArrayList<String> newSuggestions = new ArrayList<>();
        newSuggestions.add("newSuggestion");
        node.setSuggestions(newSuggestions);

        ArrayList<String> newSuggestionsCap = new ArrayList<>();
        
        newSuggestionsCap.add("newCap");
        
        node.setSuggestionsCap(newSuggestionsCap);

        ArrayList<String> newSuggestionsOther = new ArrayList<>();
        
        newSuggestionsOther.add("newOther");
        
        node.setSuggestionsOther(newSuggestionsOther);

        // Assert that setters correctly set the values
        assertEquals(newData, node.getData());
        
        assertTrue(node.isError());
        
        assertTrue(node.getIsLast());
        
        assertTrue(node.getLine());
        
        assertEquals('!', node.getPunct());
        
        assertTrue(node.isCapitalizationError());
        
        assertTrue(node.isOtherError());
        
        assertEquals(newSuggestions, node.getSuggestions());
        
        assertEquals(newSuggestionsCap, node.getSuggestionsCap());
        
        assertEquals(newSuggestionsOther, node.getSuggestionsOther());
    }

}
