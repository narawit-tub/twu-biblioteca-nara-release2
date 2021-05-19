package com.twu.biblioteca.bibliotecharelease2.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component("dummyLibary")
public class DummyLibary implements LibaryInterface {
    private ArrayList<Book> books;

    public ArrayList<Book> getBooks() {
        List<Book> bookList = Arrays.asList(
                new Book("Work life balance", "nara", "2016"),
                new Book("Japan is hard", "mormew", "2015"));
        ArrayList<Book> booksArrayList = new ArrayList<>();
        booksArrayList.addAll(bookList);
        return booksArrayList;
    }
}
