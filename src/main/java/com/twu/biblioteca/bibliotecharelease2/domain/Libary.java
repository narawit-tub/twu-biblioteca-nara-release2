package com.twu.biblioteca.bibliotecharelease2.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component("libary")
public class Libary implements LibaryInterface{
    private ArrayList<Book> books;

    public Libary(ArrayList books) {
        this.books = books;
    }

    public ArrayList<Book> getBooks() {
        return this.books;
    }
}
