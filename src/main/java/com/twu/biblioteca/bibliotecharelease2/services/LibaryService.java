package com.twu.biblioteca.bibliotecharelease2.services;

import com.twu.biblioteca.bibliotecharelease2.domain.Book;
import com.twu.biblioteca.bibliotecharelease2.domain.LibaryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class LibaryService {

    @Autowired
    @Qualifier("dummyLibary")
    private LibaryInterface libary;

    public ArrayList<Book> getAvailableBook() {
        return libary.getBooks();
    }

    public Book checkoutBook(String bookName) {
        Book checkedOutBook = null;

        for (Book book : libary.getBooks()) {
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

        for (Book book : libary.getBooks()) {
            if (book.getBookName().equals(bookName)) {
                returnedBook = book;
                book.setAvailable(true);
                break;
            }
        }

        return returnedBook;
    }

    public Integer getNumberOfBooks() {
        return libary.getBooks().toArray().length;
    }

    public Integer getNumberOfAvailableBooks() {
        return libary.getBooks().stream()
                .filter(book -> book.getAvailable())
                .collect(Collectors.toList())
                .toArray().length;
    }
}
