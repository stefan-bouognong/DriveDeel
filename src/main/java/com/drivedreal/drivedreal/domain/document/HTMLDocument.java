package com.drivedreal.drivedreal.domain.document;

public class HTMLDocument extends Document {

    private String html_content;

    public HTMLDocument(String id_document, String document_type, String html_content) {
        super(id_document, document_type, "HTML");
        this.html_content = html_content;
    }

    @Override
    public void display() {
        System.out.println("Displaying HTML document:");
        System.out.println(html_content);
    }

    @Override
    public void print() {
        System.out.println("Printing HTML document...");
    }

    public String getHtml_content() {
        return html_content;
    }
}
