package com.example;

public class FileHandler {
    private String filepath;
    private Document doc;

    // Loading the file uploaded into a document object
    public Document loadFile(String filepath, String fileName, boolean fileMarkUp) {
        this.filepath = filepath;
        doc = new Document(this.filepath, fileName, fileMarkUp);
        return doc;
    }

    // saving the document
    public void saveFile(Document doc) {

    }

}
