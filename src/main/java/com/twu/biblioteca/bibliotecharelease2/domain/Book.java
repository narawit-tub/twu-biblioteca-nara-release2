package com.twu.biblioteca.bibliotecharelease2.domain;

public class Book {
    private String author;
    private String publicationYear;
    private String bookName;
    private Boolean isAvailable;

    public Book(String bookName, String author, String publicationYear) {
        this.author = author;
        this.publicationYear = publicationYear;
        this.bookName = bookName;
        this.isAvailable = true;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getPublicationYear() {
        return this.publicationYear;
    }

    public String getBookName() {
        return this.bookName;
    }

    public Boolean getAvailable() {
        return this.isAvailable;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAvailable(Boolean available) {
        this.isAvailable = available;
    }

    public String getInformationDetail() {
        return String.format("%s (%s) by %s - %s", this.getBookName(), this.getPublicationYear(), this.getAuthor(), this.isAvailable ? "available" : "not available");
    }

    public int compareTo(Book o) {
        return 0;
    }
}
