package com.drivedreal.drivedreal.domain.document;

public class HTMLDocument implements Document {

    private String content;

    public HTMLDocument(String content) {
        this.content = content;
    }

    @Override
    public void display() {
        System.out.println("Displaying HTML document:");
        System.out.println(content);
    }

    @Override
    public void print() {
        System.out.println("Printing HTML document...");
    }

    @Override
    public void save(String filename) {
        System.out.println("Saving HTML document as: " + filename);
    }
}

