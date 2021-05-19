package com.twu.biblioteca.bibliotecharelease2.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Libary {
    private ArrayList<Book> books;

    Libary(ArrayList books) {
        this.books = books;
    }

    public ArrayList<Book> getBooks() {
        return this.books;
    }
}
