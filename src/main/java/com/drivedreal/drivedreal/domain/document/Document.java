package com.drivedreal.drivedreal.domain.document;

public class Document {

    private String name;
    private String format;

    public Document() {}

    public Document(String name, String format) {
        this.name = name;
        this.format = format;
    }

    public String getName() {
        return name;
    }

    public String getFormat() {
        return format;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
