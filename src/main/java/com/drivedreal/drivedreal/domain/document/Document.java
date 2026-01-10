package com.drivedreal.drivedreal.domain.document;

public abstract class Document {

    protected String id_document;
    protected String document_type;
    protected String format;

    public Document(String id_document, String document_type, String format) {
        this.id_document = id_document;
        this.document_type = document_type;
        this.format = format;
    }

    public abstract void display();

    public abstract void print();

    public void save(String path) {
        System.out.println("Saving document to path: " + path);
    }

    public String getId_document() {
        return id_document;
    }

    public String getDocument_type() {
        return document_type;
    }

    public String getFormat() {
        return format;
    }
}
