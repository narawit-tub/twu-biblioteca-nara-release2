package com.twu.biblioteca.bibliotecharelease2.services;

import com.twu.biblioteca.bibliotecharelease2.domain.Book;
import com.twu.biblioteca.bibliotecharelease2.domain.Libary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibaryService {

    @Autowired
    private Libary libary;

    public Integer getNumberOfBooks() {
        return libary.getBooks().toArray().length;
    }

    public Integer getNumberOfAvailableBooks() {
        return ((List)libary.getBooks().stream().filter((book) -> {
            return book.getAvailable();
        }).collect(Collectors.toList())).toArray().length;
    }

    public Book checkoutBook(String bookName) {
        Book checkedOutBook = null;
        Iterator var3 = libary.getBooks().iterator();

        while(var3.hasNext()) {
            Book book = (Book)var3.next();
            if (book.getBookName().equals(bookName)) {
                checkedOutBook = book;
                book.setAvailable(false);
                break;
            }
        }

        return checkedOutBook != null ? checkedOutBook : null;
    }

    public Book returnBook(String bookName) {
        Book returnedBook = null;
        Iterator var3 = libary.getBooks().iterator();

        while(var3.hasNext()) {
            Book book = (Book)var3.next();
            if (book.getBookName().equals(bookName)) {
                returnedBook = book;
                book.setAvailable(true);
                break;
            }
        }

        return returnedBook;
    }
}
