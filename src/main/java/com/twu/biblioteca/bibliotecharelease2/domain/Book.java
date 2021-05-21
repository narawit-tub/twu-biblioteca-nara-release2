package com.twu.biblioteca.bibliotecharelease2.domain;

public class Book extends LibaryMedia implements LibaryMediaInterface{

    public Book(String maker, String productName, String publicationYear) {
        super(maker, productName, publicationYear);
        setMediaType(Media_type.Book);
    }

    @Override
    public String getInformationDetail() {
        return String.format("%s (%s) by %s - %s", this.getProductName(), this.getPublicationYear(), this.getMaker(), this.getAvailable() ? "available" : "not available");
    }
}
