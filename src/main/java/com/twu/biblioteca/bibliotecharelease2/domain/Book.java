package com.twu.biblioteca.bibliotecharelease2.domain;

public class Book {
    private String author;
    private String pullicationYear;
    private String bookName;
    private Boolean isAvailable;

    public Book(String bookName, String author, String pullicationYear) {
        this.author = author;
        this.pullicationYear = pullicationYear;
        this.bookName = bookName;
        this.isAvailable = true;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getPullicationYear() {
        return this.pullicationYear;
    }

    public String getBookName() {
        return this.bookName;
    }

    public Boolean getAvailable() {
        return this.isAvailable;
    }

    public void setAvailable(Boolean available) {
        this.isAvailable = available;
    }

    public String getInformationDetail() {
        return String.format("%s (%s) by %s - %s", this.getBookName(), this.getPullicationYear(), this.getAuthor(), this.isAvailable ? "available" : "not available");
    }

    public int compareTo(Book o) {
        return 0;
    }
}
