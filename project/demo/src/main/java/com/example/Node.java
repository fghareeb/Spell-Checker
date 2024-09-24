package com.example;

import java.util.ArrayList;

	/**
	 * Generic class that represents an element in a data structure 
	 * containing various attributes.
	 * Class was desgined to store data, along with error flags and more
	 * 
	 * @param <T> The type of data the node stores
	 */
public class Node<T> {
    private T data;
    public Node<T> next;
    public Node<T> prev;
    private boolean error;
    private boolean isTag; // Boolean field to indicate if it's a tag
    private ArrayList<String> suggestions;
    private ArrayList<String> suggestionsCap;
    private ArrayList<String> suggestionsOther;

    private char punct;
    private boolean isLast;
    private boolean newLine;
    private boolean capitalizationError;
    private boolean otherError;

    /**
     * Constructs a new Node with specified data and attributes.
     *
     * @param data The data stored in the node.
     * @param isTag Indicates if the node represents a tag.
     * @param error Indicates if there is an error in the node.
     * @param suggestions List of suggested corrections.
     * @param punct Punctuation associated with the node.
     * @param isLast Indicates if this is the last node.
     * @param newLine Indicates if this node represents a new line.
     * @param capitalizationError Indicates if there is a capitalization error.
     * @param otherError Indicates if there is some other type of error.
     * @param suggestionsCap List of suggested capitalization corrections.
     * @param suggestionsOther List of other suggested corrections.
     */
    
    public Node(T data, boolean isTag, boolean error, ArrayList<String> suggestions, char punct, boolean isLast,
            boolean newLine, boolean capitalizationError, boolean otherError, ArrayList<String> suggestionsCap,
            ArrayList<String> suggestionsOther) {
        this.data = data;
        this.isTag = isTag;
        this.error = error;
        this.suggestions = suggestions;
        this.isLast = isLast;
        this.punct = punct;
        this.newLine = newLine;
        this.capitalizationError = capitalizationError;
        this.otherError = otherError;
    }

    /**
     * Checks for error
     * 
     * @return the data of this node
     */
    public boolean isError() {
        return error;
    }

    /**
     * Checks for capitilization error
     * 
     * @return data of this node
     */
    public boolean isCapitalizationError() {
        return capitalizationError;
    }

    /**
     * Checks for other error
     * 
     * @return data of this node
     */
    public boolean isOtherError() {
        return otherError;
    }

    /**
     * Getter method for tag
     * 
     * @return if tag
     */
    public boolean getIsTag() {
        return isTag;
    }

    /**
     * Setter method for error
     * 
     * @param error error to be set
     */
    public void setError(boolean error) {
        this.error = error;
    }

    /**
     * Setter method for capitilization error
     * 
     * @param error error to be set
     */
    public void setCapitalizationError(boolean error) {
        this.capitalizationError = error;
    }
    
    /**
     * Setter method for other errors
     * 
     * @param error error to be set
     */

    public void setOtherError(boolean error) {
        this.otherError = error;
    }

    /**
     * Getter methods for sugestions
     * 
     * @return the suggestions
     */
    public ArrayList<String> getSuggestions() {
        return suggestions;
    }

    /**
     * Getter methods for sugestionsCap
     * 
     * @return the suggestions cap
     */
    public ArrayList<String> getSuggestionsCap() {
        return suggestionsCap;
    }

    /**
     * Getter methods for other sugestions
     * 
     * @return the other suggestions
     */
    public ArrayList<String> getSuggestionsOther() {
        return suggestionsOther;
    }

    /**
     * Setter method for suggestions
     * 
     * @param suggestions suggestions to be set
     */
    public void setSuggestions(ArrayList<String> suggestions) {
        this.suggestions = suggestions;
    }

    /**
     * Setter method for suggestions cap
     * 
     * @param suggestions suggestions cap to be set
     */
    
    public void setSuggestionsCap(ArrayList<String> suggestions) {
        this.suggestionsCap = suggestions;
    }
    
    /**
     * Setter method for other suggestions
     * 
     * @param suggestions other suggestions to be set
     */

    public void setSuggestionsOther(ArrayList<String> suggestions) {
        this.suggestionsOther = suggestions;
    }

    /**
     * Getter method to return punctuation
     * 
     * @return punctuations
     */
    public char getPunct() {
        return punct;
    }

    /**
     * Getter method to check if last
     * 
     * @returns if if last
     */
    public boolean getIsLast() {
        return isLast;
    }

    /**
     * Setter method to set if last
     * 
     * @param isLast sets as last
     */
    public void setIsLast(boolean isLast) {
        this.isLast = isLast;
    }

    /**
     * Getter method to get line
     * 
     * @returns new line
     */
    public boolean getLine() {
        return newLine;
    }

    /**
     * Setter method to set line
     * 
     * @param line line to set
     */
    public void setLine(boolean line) {
        this.newLine = line;
    }

    /**
     * Getter method to get data of type T
     * 
     * @return data
     */
    public T getData() {
        return data;
    }

    /**
     * Setter method to set data of type T
     * 
     * @param data data to be set
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Setter method to set punctuation
     * 
     * @param punct punctuation to be set
     */
    public void setPunct(char punct) {
        this.punct = punct;
    }

}
