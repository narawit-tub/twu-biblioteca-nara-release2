package com.twu.biblioteca.bibliotecharelease2.services;

import com.twu.biblioteca.bibliotecharelease2.domain.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibaryService {

    private ArrayList<Book> books;

    public LibaryService() {
        // get books from source
        books = new ArrayList<>();
        List<Book> bookList = Arrays.asList(
                new Book("Work life balance", "nara", "2016"),
                new Book("Japan is hard", "mormew", "2015"));
        books.addAll(bookList);
    }

    public LibaryService(ArrayList<Book> books) {
        this.books = books;
    }

    public ArrayList<Book> getAvailableBook() {
        // filter only available book

        return books;
    }

    public Book checkoutBook(String bookName) {
        Book checkedOutBook = null;

        for (Book book : books) {
            if (book.getBookName().equals(bookName)) {
                checkedOutBook = book;
                book.setAvailable(false);
                break;
            }
        }

        return (checkedOutBook != null) ? checkedOutBook : null;
    }

    public Book returnBook(String bookName) {
        Book returnedBook = null;

        for (Book book : books) {
            if (book.getBookName().equals(bookName)) {
                returnedBook = book;
                book.setAvailable(true);
                break;
            }
        }

        return returnedBook;
    }

    public Integer getNumberOfBooks() {
        return books.toArray().length;
    }

    public Integer getNumberOfAvailableBooks() {
        return books.stream()
                .filter(book -> book.getAvailable())
                .collect(Collectors.toList())
                .toArray().length;
    }
}
