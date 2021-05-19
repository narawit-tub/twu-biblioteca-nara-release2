package com.twu.biblioteca.bibliotecharelease2.web;

import com.twu.biblioteca.bibliotecharelease2.domain.Book;
import com.twu.biblioteca.bibliotecharelease2.services.ErrorService;
import com.twu.biblioteca.bibliotecharelease2.services.LibaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/libary")
public class LibaryController {
    @Autowired
    private LibaryService libaryService;

    @GetMapping("/books")
    public ArrayList<Book> getAllBooks () {
        return libaryService.getAvailableBook();
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkoutBook (@RequestBody Book book) {
        Book checkedOutBook = libaryService.checkoutBook(book.getBookName());
        if (checkedOutBook == null) return ErrorService.getErrorMap("Not found book");
        return new ResponseEntity<Book>(checkedOutBook, HttpStatus.OK);
    }

    @PostMapping("/returnbook")
    public ResponseEntity<?> returnBook (@RequestBody Book book) {
        Book returnedBook = libaryService.returnBook(book.getBookName());
        if (returnedBook == null) return ErrorService.getErrorMap("You define a wrong name of book, please recheck");
        return new ResponseEntity<Book>(returnedBook, HttpStatus.OK);
    }
}